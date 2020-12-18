// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree new_node = new BSTree(address,size,key);
        if(this.parent == null){
            if(this.right == null){
                this.right = new_node;
                new_node.parent = this;
                return new_node;
            }
            else{
                return this.right.Insert(address,size,key);
            }
        }
        else{
            if(this.key == key){
                if(this.address > address){
                    if(this.left == null){
                        this.left = new_node;
                        new_node.parent = this;
                        return new_node;
                    }
                    else{
                        return this.left.Insert(address,size,key);
                    }
                }
                else{
                    if(this.right == null){
                        this.right = new_node;
                        new_node.parent = this;
                        return new_node;
                    }
                    else{
                        return this.right.Insert(address,size,key);
                    }
                }
            }
            else if(this.key > key){
                if(this.left == null){
                        this.left = new_node;
                        new_node.parent = this;
                        return new_node;
                    }
                else{
                        return this.left.Insert(address,size,key);
                    }
            }
            else{
                if(this.right == null){
                        this.right = new_node;
                        new_node.parent = this;
                        return new_node;
                    }
                    else{
                        return this.right.Insert(address,size,key);
                    }
            }
        }
    }

    public boolean Delete(Dictionary e)
    { 
        if(this.key == e.key && this.address == e.address && this.size == e.size)
        {
            if(this.left == null && this.right == null){    // case of leaf node
                if(this.key > this.parent.key || (this.key == this.parent.key && this.address > this.parent.address)){
                    this.parent.right = null;      
                }
                else{
                    this.parent.left = null;
                }
            }
            else if(this.right == null){            // only left child
                if(this.key > this.parent.key || (this.key == this.parent.key && this.address > this.parent.address)){
                    this.parent.right = this.left;
                    this.left.parent = this.parent;      
                }
                else{
                    this.parent.left = this.left;
                    this.left.parent = this.parent;
                }    
            }
            else if(this.left == null){            // only right child
                if(this.key > this.parent.key || (this.key == this.parent.key && this.address > this.parent.address)){
                    this.parent.right = this.right;
                    this.right.parent = this.parent;      
                }
                else{
                    this.parent.left = this.right;
                    this.right.parent = this.parent;
                }    
            }
            else{                                  // both children
                    BSTree temp = this.getNext();
                    this.right.Delete(temp);
                    temp.left = this.left;
                    temp.right =  this.right;
                    temp.parent = this.parent;
                    if(this.left != null){
                        this.left.parent = temp;
                    }
                    if(this.right != null){
                        this.right.parent = temp;    
                    }
                    
                    if(this.key > this.parent.key || (this.key == this.parent.key && this.address > this.parent.address)){
                        this.parent.right = temp;
                          
                    }
                    else{
                        this.parent.left = temp;
                    }
            }
            this.left = null;
            this.right = null;
            this.parent = null;
            return true;
        }
        else if(this.key < e.key){                 
                if(this.right == null){
                    return false;
                }
                else{
                    return this.right.Delete(e);
                }
            
        }
        else if(this.key > e.key){
            if(this.left == null){
                    return false;
                }
                else{
                    return this.left.Delete(e);
                } 
        }
        else{
            if(this.address >= e.address){
                if(this.left == null){
                    return false;
                }
                else{
                    return this.left.Delete(e);
                }
            }
            else{
                if(this.right == null){
                    return false;
                }
                else{
                    return this.right.Delete(e);
                }
            }        
        }
    }    
    public BSTree Find(int key, boolean exact)
    { 
        if(exact == true){
            if(this.key == key)
            {
                return this;
            }
            else if(this.key > key && this.left != null){
                return this.left.Find(key,exact);
            }
            else if(this.key < key && this.right != null){
                return this.right.Find(key,exact);
            }
            else{
                return null;
            }    
        }
        else{
            if(this.key >= key && (this.left == null || this.left.key < key))
            {
                return this;
            }
            else if(this.key >= key && this.left != null && this.left.key >= key)
            {
                return this.left.Find(key,exact);
            }
            else if(this.key < key && this.right != null)
            {
                return this.right.Find(key,exact);
            }
            else{
                return null;
            }
            }
    }

    public BSTree getFirst()
    { 
        BSTree temp = this;
        
            while(temp.parent != null){
                temp = temp.parent;
            }
            if(temp.right == null){
                return null;
            }
            else{
            temp = temp.right;
            while(temp.left != null){
                temp = temp.left;
            }
            return temp;
        }
    }

    public BSTree getNext()
    { 
        if(this.parent == null){
            if(this.right != null){
            return this.right;
            }
            else{
                return null;
            }
        }   
        else if(this.right !=  null){
            BSTree temp = this.right;
            while(temp.left != null){
                temp = temp.left;
            }
            return temp;
        }
        else{
            BSTree temp = this;
            BSTree temp2 = this.parent;
            while(temp2.key != -1 && temp == temp2.right){
                temp = temp2;
                temp2 = temp2.parent;
            }
            if(temp.parent.key == -1){
                return null;
            }
            else{
                return temp.parent;
            }
        }
    }

    public boolean sanity()
    { 
        BSTree T_slow = this;
        BSTree T_fast = this;
        while(T_slow != null && T_fast != null && T_fast.parent != null){
            T_slow = T_slow.parent;
            T_fast = T_fast.parent.parent;
            if(T_slow == T_fast){
                return false;
            }
        }
        BSTree temp = this;
        while(temp.parent != null){
            temp = temp.parent;
        }
        if(temp.key != -1 || temp.address != -1 || temp.size != -1){
            return false;
        }
        temp = this.getFirst();
        if(temp == null){
            return true;
        }
        while(temp.getNext() != null){
            if(temp.left != null){
                if(temp.left.parent != temp){
                    return false;
                }
            }
            if(temp.right != null){
                if(temp.right.parent != temp){
                    return false;
                }
            }
            temp = temp.getNext();
        }
        return true;
    }

}


 


