// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree new_node = new AVLTree(address,size,key);
    	if(this.parent == null){
    		if(this.right == null){
    			this.height = this.height + 1;
    			this.right = new_node;
    			new_node.parent = this;
    			return new_node;
    		}
    		else{
    			return this.right.Insert(address,size,key);
    		}
    	}
    	else{
    		if(this.key > key || (this.key == key && this.address > address)){
    			if(this.left == null){
    				this.left = new_node;
    				new_node.parent = this;
			    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
			    	AVLTree n = new_node;
			    	AVLTree p = n.parent;
			    	AVLTree gp = p.parent;
			    	while(gp.parent != null && Math.abs(gp.getHeight(gp.left) - gp.getHeight(gp.right)) <= 1){
			    		gp.height = 1 + Math.max(gp.getHeight(gp.left),gp.getHeight(gp.right));
			    		n = p;
			    		p = gp;
			    		gp = gp.parent;
			    	}
			    	if(Math.abs(gp.getHeight(gp.left) - gp.getHeight(gp.right)) > 1 && gp.parent != null){
			    		if(p == gp.left && n == p.left){
			    			gp = gp.SingleLeftRotate();
			    		}
			    		else if(p == gp.right && n == p.right){
			    			gp = gp.SingleRightRotate();
			    		}
			    		else if(p == gp.left && n == p.right){
			    			gp = gp.DoubleLeftRotate();
			    		}
			    		else if(p == gp.right && n == p.left){
			    			gp = gp.DoubleRightRotate();
			    		}
			    	}
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
			    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
			    	AVLTree n = new_node;
			    	AVLTree p = n.parent;
			    	AVLTree gp = p.parent;
			    	while(gp.parent != null && Math.abs(gp.getHeight(gp.left) - gp.getHeight(gp.right)) <= 1){
			    		gp.height = 1 + Math.max(gp.getHeight(gp.left),gp.getHeight(gp.right));
			    		n = p;
			    		p = gp;
			    		gp = gp.parent;
			    	}
			    	if(Math.abs(gp.getHeight(gp.left) - gp.getHeight(gp.right)) > 1 && gp.parent != null){
			    		if(p == gp.left && n == p.left){
			    			gp = gp.SingleLeftRotate();
			    		}
			    		else if(p == gp.right && n == p.right){
			    			gp = gp.SingleRightRotate();
			    		}
			    		else if(p == gp.left && n == p.right){
			    			gp = gp.DoubleLeftRotate();
			    		}
			    		else if(p == gp.right && n == p.left){
			    			gp = gp.DoubleRightRotate();
			    		}
			    		// System.out.println("did a rotate");
			    		// System.out.println(gp.left.key);
			    		// System.out.println(gp.right.key);
			    	}
    				return new_node;
    			}
    			else{
    				return this.right.Insert(address,size,key);
    			}
    		}
    	}

    }

    	//	using left rotate in this case 
   		//    z
   		//   /
   		//  /
   		// y


    private AVLTree SingleLeftRotate(){
    	AVLTree temp = this.parent;
    	AVLTree y = this.left;
    	AVLTree T2 = y.right;
    	y.right = this;
    	y.parent = temp;
    	this.parent = y;
    	this.left = T2;
    	if(T2 != null){
    		T2.parent = this;
    	}
    	if(this == temp.left){
    		temp.left = y;
    	}
    	else{
    		temp.right = y;
    	}
    	

    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
    	y.height = 1 + Math.max(y.getHeight(y.left),y.getHeight(y.right));

    	return y;

    	   	
    }
   

    	// using right rotate in this case
    	//    z
    	//     \
    	//      \
    	//       y

    private AVLTree SingleRightRotate(){
    	AVLTree temp = this.parent;
    	AVLTree y = this.right;
    	AVLTree T2 = y.left;
    	y.left = this;
    	y.parent = temp;
    	this.parent = y;
    	this.right = T2;
    	if(T2 != null){
    		T2.parent = this;
    	}
    	if(this == temp.left){
    		temp.left = y;
    	}
    	else{
    		temp.right = y;
    	}
    	

    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
    	y.height = 1 + Math.max(y.getHeight(y.left),y.getHeight(y.right));

    	return y;
    	}

    	//     z
    	//    /
    	//   /
    	//  y
    	//   \
    	//    \
    	//     x

    private AVLTree DoubleLeftRotate(){
    	AVLTree temp = this.parent;
    	AVLTree y = this.left;
    	AVLTree x = y.right;
    	AVLTree T1 = x.left;
    	AVLTree T2 = x.right;
    	x.left = y;
    	x.right = this;
    	x.parent =temp;
    	y.parent = x;
    	this.parent = x;
    	y.right = T1;
    	if(T1 != null){
    		T1.parent = y;	
    	}
    	

    	this.left = T2;
    	if(T2 != null){
    		T2.parent = this;
    	}
    	
    	if(this == temp.left){
    		temp.left = x;
    	}
    	else{
    		temp.right = x;
    	}
    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
    	y.height = 1 + Math.max(y.getHeight(y.left),y.getHeight(y.right));
    	x.height = 1 + Math.max(x.getHeight(x.left),x.getHeight(x.right));

    	return x;
    }

    //      z
    //       \
    //        \
    //         y
    //        /
    //       /
    //      x


    private AVLTree DoubleRightRotate(){
    	AVLTree temp = this.parent;
    	AVLTree y = this.right;
    	AVLTree x = y.left;
    	AVLTree T1 = x.left;
    	AVLTree T2 = x.right;
    	x.right = y;
    	x.left = this;
    	x.parent = temp;
    	y.parent = x;
    	this.parent = x;
    	y.left = T2;
    	if(T2 != null){
    		T2.parent = y;
    	}
    	
    	this.right = T1;
    	if(T1 != null){
    		T1.parent = this;
    	}
    	
    	if(this == temp.left){
    		temp.left = x;
    	}
    	else{
    		temp.right = x;
    	}
    	this.height = 1 + Math.max(this.getHeight(this.left),this.getHeight(this.right));
    	y.height = 1 + Math.max(y.getHeight(y.left),y.getHeight(y.right));
    	x.height = 1 + Math.max(x.getHeight(x.left),x.getHeight(x.right));

    	return x;

    }

   


    	// height on null node is -1
    	// using a function to get rid of cannot get height cause this.left/ this.right is null
    
    private int getHeight(AVLTree N){
    	if(N == null){
    		return -1;
    	}
    	else{
    		return N.height;
    	}
    }

    // public void inorder(){
    // 	if(this.left != null){
    // 		this.left.inorder();
    // 	}
    // 	System.out.print(this.key);
    // 	if(this.right != null){
    // 		this.right.inorder();
    // 	}
    // 	return;
    // }


    public boolean Delete(Dictionary e)
    {
    	if(this.key == e.key && this.address == e.address && this.size == e.size)
        {

            AVLTree gp = this.parent;
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
                    AVLTree temp = this.right;
                    while(temp.left != null){
                    	temp = temp.left;
                    }
                    // System.out.println("deleting successor start");
                    this.Delete(temp);
                    // System.out.println("deleting successor stop");
                    temp.left = this.left;
                    temp.right =  this.right;
                    temp.parent = this.parent;
                    temp.height = 1 + Math.max(temp.getHeight(temp.left),temp.getHeight(temp.right));
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


    		
    		while( gp.parent != null){
        		gp.height = 1 + Math.max(gp.getHeight(gp.left),gp.getHeight(gp.right));
        		
        		
    			
    			if(Math.abs(gp.getHeight(gp.left) - gp.getHeight(gp.right)) > 1){
    				
    				// System.out.println("doing a delete rotate");
    				AVLTree n = new AVLTree();
    				AVLTree p = new AVLTree();
    				if(gp.getHeight(gp.left) > gp.getHeight(gp.right)){
    					 p = gp.left;
    				}
    				else{
    					 p = gp.right;
    				}
    				if(p.getHeight(p.left) >= p.getHeight(p.right)){
    					 n = p.left;
    				}
    				else{
    					 n = p.right;
    				}
    				if(p == gp.left && n == p.left){
        				gp = gp.SingleLeftRotate();
       				}
   					else if(p == gp.right && n == p.right){
  						gp = gp.SingleRightRotate();
  						
        			}
        			else if(p == gp.left && n == p.right){
        				gp = gp.DoubleLeftRotate();
       				}
        			else if(p == gp.right && n == p.left){
        				gp = gp.DoubleRightRotate();
        			}
        // 			System.out.println(gp.left.key);
		    		// System.out.println(gp.right.key);
    			}
    			gp = gp.parent;
    			


    		}
    		this.left = null;
    		this.right = null;
    		this.parent = null;
    		return true;
    	}
    	else if(this.key > e.key || (this.key == e.key && this.address > e.address)){		// node might be in left subtree
    		if(this.left == null){
    			return false;
    		}
    		else{
    			return this.left.Delete(e);
    		}
    	}
    	else{																				// node might be in right subtree
    		if(this.right == null){
    			return false;
    		}
    		else{

    			return this.right.Delete(e);
    		}
    	}
    }

        public AVLTree Find(int key, boolean exact)
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
            	if(this.key >= key){
            		if(this.left == null){
            			return this;
            		}
            		else{
            			if(this.left.key < key){
            				return this;
            			}
            			else{
            				return this.left.Find(key,exact);
            			}
            		}
            	}
            	else{
            		if(this.right == null){
            			return null;
            		}
            		else{
            			return this.right.Find(key,exact);
            		}
            	}
            }
    }

    public AVLTree getFirst()
    { 
        AVLTree temp = this;
        
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


        
   
    public AVLTree getNext()
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
        	
            AVLTree temp = this.right;
            while(temp.left != null){
                temp = temp.left;
            }
            
            return temp;
        }
        else{
            AVLTree temp = this;
            AVLTree temp2 = this.parent;
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
    	AVLTree T_slow = this;
        AVLTree T_fast = this;
        while(T_slow != null && T_fast != null && T_fast.parent != null){
            T_slow = T_slow.parent;
            T_fast = T_fast.parent.parent;
            if(T_slow == T_fast){
                return false;
            }
        }
        AVLTree temp = this;
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
        temp = this.getFirst();
        while(temp.getNext() != null){
        	if(Math.abs(temp.getHeight(temp.left) - temp.getHeight(temp.right)) <= 1){
        		temp = temp.getNext();
        	}
        	else{
        		return false;
        	}

        }

        return true;
    }
}


