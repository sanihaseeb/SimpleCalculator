// Name: Sani Haseeb  
/**
 * 
 * @author Sani
 * -This is a class that implements the queue:
 * a data structure which stores data and follows FIFO
 * principle (FIRST IN FIRST OUT)
 * 
 * 
 *-The class consists of Enqueue and Dequeue methods
 * 
 *
 */
public class Queue {

	public listNode front = null;
	public listNode back = null;
	
	/**-Enqueue method inserts string into the queue: (myStr) */
	

	public void Enqueue(String myStr)	
	{
		listNode node = new listNode();
		node.data = myStr;
		node.next = null;

		if (back == null) 
		{
			front = node;

		} 
		else
		
		{
			back.next = node;

		}
		back = node;

	}
/** Dequeue method removes string from the queue
 * 
 * @return RETURNS THE STRING AT THE END OF THE QUEUE
 */
	
	
	public String Dequeue()
	{
		listNode node = new listNode();

		if (front == null)
		{
			return null;
		}
		else
		{
			node = front;
			front = front.next;
			if (node == back)
				back = null;
		}

		return node.data;

	 }

}



