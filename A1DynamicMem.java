// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the dictionary class implementation are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
         Dictionary temp = this.freeBlk.Find(blockSize,false);
        if(this.freeBlk.sanity() == true && blockSize > 0){
        if(temp == null){
            return -1;
        }
        else{
            if(temp.size == blockSize){
                this.freeBlk.Delete(temp);
                this.allocBlk.Insert(temp.address,temp.size,temp.key);
                return temp.address;
            }
            else{
                this.freeBlk.Delete(temp);
                this.freeBlk.Insert((temp.address+blockSize), (temp.size - blockSize), (temp.size - blockSize));
                this.allocBlk.Insert(temp.address,blockSize,blockSize);
                return temp.address;
            }
        }}
        else{
            return -1;
        }
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
        Dictionary temp = this.allocBlk.getFirst();
        if(temp == null){
            return -1;
        }
        while(temp.getNext() != null)
        {
            if(temp.address == startAddr)
            {
                break;
            }
            else
            {
                temp = temp.getNext();
            }
        }
        if(temp.address == startAddr)
            {
                this.allocBlk.Delete(temp);
                this.freeBlk.Insert(temp.address,temp.size,temp.key);
                return 0;
            }
        else{
            return -1;
        }
    }
}