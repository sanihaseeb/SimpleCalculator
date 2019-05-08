// Name: Sani Haseeb
 

/** This class implements the stack
 * it follows a data structure that follows
 * FIRST IN LAST OUT principle 
 * @author Sani
 *
 */

public class Stack {

	public listNode top = null;
	/**
	 * 
	 * @param myStr i.e the argument given to the push method-used to 
	 * push the new string on the top of the stack
	 	 */

	public void push(String myStr) {
		listNode node = new listNode();
		node.data = myStr;

		if (top != null) {
			node.next = top;
		}

		top = node;
	}
	/** 
	 * This method called the 'pop' removes the top value from the stack and returns it
	 * 
	 * @return RETURNS THE TOP STACK IF STACK IS NOT EMPTY 
	 * IF EMPTY, RETURNS NULL VALUE
	 */
	
	public String pop() {

		if (top == null)
			return null;

		String topValue = top.data;
		top = top.next;
		return topValue;

	}
}

