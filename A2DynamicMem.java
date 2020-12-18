// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public int Allocate(int blockSize) {
         Dictionary temp = this.freeBlk.Find(blockSize,false);
        if(this.freeBlk.sanity() == true && blockSize > 0){
        if(temp == null){
            return -1;
        }
        else{
            if(temp.size == blockSize){
                this.freeBlk.Delete(temp);
                this.allocBlk.Insert(temp.address,temp.size,temp.address);
                return temp.address;
            }
            else{
                this.freeBlk.Delete(temp);
                this.freeBlk.Insert((temp.address+blockSize), (temp.size - blockSize), (temp.size - blockSize));
                this.allocBlk.Insert(temp.address,blockSize,temp.address);
                return temp.address;
            }
        }}
        else{
            return -1;
        }
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary temp = this.allocBlk.Find(startAddr,true);
        if(temp == null){
            return -1;
        }
        else{

            this.allocBlk.Delete(temp);
            this.freeBlk.Insert(temp.address,temp.size,temp.size);
            return 0;
        }
    }


    public void Defragment() {
        AVLTree addrTree = new AVLTree();

        Dictionary temp = this.freeBlk.getFirst();

        if(temp == null){
            return;
        }
        while(temp.getNext() != null){
            addrTree.Insert(temp.address,temp.size,temp.address);
            temp = temp.getNext();
        }
        addrTree.Insert(temp.address,temp.size,temp.address);
              
        
        
        AVLTree temp2 = addrTree.getFirst();
        AVLTree temp3 = temp2.getNext();
        while(temp3 != null){

            

            if((temp3.address - temp2.address) == temp2.size){
                
                addrTree.Delete(temp2);
                addrTree.Delete(temp3);
                addrTree.Insert(temp2.address, temp2.size + temp3.size, temp2.address);

                

                temp2 = addrTree.getFirst();
                temp3 = temp2.getNext();
            

            }
            else{
                
                temp2 = temp3;
                temp3 = temp3.getNext();
                
            }

            

        }
            
            temp = this.freeBlk.getFirst();
            while(temp.getNext() != null){
                Dictionary temp5 = temp.getNext();
                this.freeBlk.Delete(temp);
                temp = temp5;
            }
            this.freeBlk.Delete(temp);
            temp2 = addrTree.getFirst();
            while(temp2.getNext() != null){
                this.freeBlk.Insert(temp2.address,temp2.size,temp2.size);
                temp2 = temp2.getNext();
            }
            this.freeBlk.Insert(temp2.address,temp2.size,temp2.size);
            temp = this.freeBlk.getFirst();
           

            return;
    }

    
}
