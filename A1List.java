// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List new_node = new A1List(address,size,key);
        new_node.next = this.next;
        new_node.prev = this;
        this.next.prev = new_node;
        this.next = new_node;
     
        return new_node;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List temp = this;

        while(temp.next != null)
        {
            if(temp.key == d.key && temp.size == d.size && temp.address == d.address)
            {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                return true;
            }
            temp = temp.next;
        }
        temp = this;
        while(temp.prev != null)
        {

            if(temp.key == d.key && temp.size == d.size && temp.address == d.address)
            {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                return true;
            }
            temp = temp.prev;
        }


        return false;
    
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List temp = this;
        if(exact == true)
        {
        	while(temp.next != null)
        	{
            	if(temp.key == k){
                	return temp;
            	}
            	temp = temp.next;
        	}
        	temp = this;
        	while(temp.prev != null)
        	{
            	if(temp.key == k)
            	{
                	return temp;
            	}
            	temp = temp.prev;
        	}
        	return null;    
        }
        else
        {
            while(temp.next != null)
        	{
            	if(temp.key >= k){
                	return temp;
            	}
            	temp = temp.next;
        	}
        	temp = this;
        	while(temp.prev != null)
        	{
            	if(temp.key >= k)
            	{
                	return temp;
            	}
            	temp = temp.prev;
        	}
        	return null;
        }
    }

    public A1List getFirst()
    {
        A1List temp = this;
        while(temp.prev != null)
        {
            temp = temp.prev;
            
        }
        return temp.next;
	}    
    public A1List getNext() 
    {
        return this.next;
    }

    public boolean sanity()
    {
        A1List T_slow = this;
        A1List T_fast = this;
        
        // Floyd Algorithm -- Referenced from GeekForGeeks
        // checking ahead
        while(T_slow != null && T_fast != null && T_fast.next != null)
        {
            T_slow = T_slow.next;
            T_fast = T_fast.next.next;
            if(T_slow == T_fast)
            {
                return false;
            }
            
        }
        T_slow = this;
        T_fast = this;
        // checking loop behind
        while(T_fast != null && T_slow != null && T_fast.prev != null)
        {
            T_slow = T_slow.prev;
            T_fast = T_fast.prev.prev;
            if(T_slow == T_fast)
            {
                return false;
            }
        }

        // header sentinel check
        A1List temp = this;
        while(temp.prev != null) //will terminate as there is no loop as checked above
        {
            temp = temp.prev;
        }
        if(temp.key != -1 || temp.size != -1 || temp.address != -1)
        {
            return false;
        }
        // Since temp is header right now, we can transverse the list forward to check for anomalies 

        while(temp.next != null)
        {
            if(temp.next.prev != temp){
                return false;
            }
            temp = temp.next;
        }        
        
        // temp is now Tail Sentinel, hence we can do Tail Sentinel check
        if(temp.key != -1 || temp.size != -1 || temp.address != -1)
        {
            return false;
        }


        // Transverse the list backward to check for anomalies
        while(temp.prev != null)
        {
            if(temp.prev.next != temp){
                return false;
            }
            temp = temp.prev;
        }        
        
        return true;
    }
}




