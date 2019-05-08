import acm.program.*;
import acm.gui.TableLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.text.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
//NAME: SANI HASEEB

/**
 
 * @author Sani
 
	 * This class first sets up the graphical layout for 
	 * the calculator then assigns each JButton a value(character)
	 * then inputs the series of tokens in the input text field using
	 * those buttons and then as soon as the input token is an "="
	 * calculates the input expression and the result is then
	 * placed in the output text field.
	 *  
	 
 */


@SuppressWarnings("serial")
public class JCalcGUI extends Program  implements ChangeListener, ActionListener
{
	
	
	
	JTextField precision_field = new JTextField(""); //Text field for the precision slider value
	JSlider precision; //The actual precision slider
	
     double ans;
	
	public void init() {
	/** Setting a table layout for the calculator with 8 rows, 4 columns.
	 * and dimensions of 600, 600
	 */
	setSize(600, 600);
	setLayout(new TableLayout(8,4));
	
	/**Input, output fields where the expression and result will
	 * be entered respectively
	 */
	
	input = new JTextField ();
	output = new JTextField();
	input.setBackground(Color.WHITE);
	input.setEditable(true);
    output.setEditable(false);
	
	output.setBackground(Color.WHITE);
	add(input, "gridwidth=5 height=30");
	add(output, "gridwidth=5 height=30");
	
/** For loop and array used for the JButtons to be created
 * 
 */

	String[] ButtonLabels = {"C","(", ")","/", "7" , "8", "9","+",
			                 "4", "5", "6", "-" ,"1", "2", "3","x", "0", ".", "0.0", "="};
	for (int i = 0; i < ButtonLabels.length; i++)
	{
		JButton myButton = new JButton(ButtonLabels[i]);
		myButton.setBackground(Color.ORANGE);
		myButton.setBorderPainted(true);
		
		add(myButton, "width=100 height=70");
		addActionListeners(); 
		/**Action listener added to respond to the mouse click on each button*/
		
	}

				
	/** -Adding a label for the precision
	 * -Initializing with default value of 6 to the precision value
	 */
	
	add(new JLabel("Precision"));
	int default_value = 6;
	String default_value_str = default_value + "";

	precision = new JSlider(0,10,default_value); 
	/** Min, max, default values arguments for precision slide*/
	
	precision.addChangeListener(this);
	/**Change listener added, so as soon as it is slider, it responses*/
	
	add(precision,"gridwidth = 2");
	
	precision_field.setText(default_value_str);
	precision_field.setEditable(false);
	precision_field.setBackground(Color.WHITE);
	add(precision_field);

	  }
	
	public void stateChanged(ChangeEvent arg0)
{
		/**
		 * Method to input the precision value on the slider
		 * on to the text field beside the label
		 */
		int precision_value = precision.getValue();
		precision_field.setText(precision_value+"");
		
		/** In case the input field or output field has nothing in it
		 * the precision slider will not work and no errors.
		 */
	 if (input.getText().equals(null)|| input.getText().equals("")
	 ||output.getText().equals(null)|| output.getText().equals(""))
	 {
		 output.setText("No input/output");
	 }
	 
	 else {
		 /** The method of rounding off is called here so that 
		  * each time the precision slider is slided to a new value
		  * it is not necessary to press the equals sign, it will
		  * round off the current output text field result
		  */
		double scale = Math.pow(10, precision.getValue());
		double doubleResult = Double.parseDouble(myResult);
		Double rounded = Math.round (doubleResult*scale)/scale;
		output.setText(Double.toString(rounded));
	      }
}
		
	/**
	 * Method for listening to the mouse clicks on each button
	 * and control statements to set the input text field with tokens
	 * according to the buttons being pressed
	 */
	public void actionPerformed (ActionEvent e) 
	{
		
		if (e.getActionCommand().equals("C"))
		{
			input.setText("");
			output.setText("");
		}
		
		String InputText;
		
		if (input.getText().equals(null)) 
		{
			InputText = "0";
		}
		else 
		{
			InputText = (input.getText());
		}
			
		for (int i = -1; i<=9; i++) 
    {
		if (e.getActionCommand().equals(Integer.toString(i)))
		{
			input.setText(InputText + Integer.toString(i));
		}
			
	}
		if (e.getActionCommand().equals("+")) 
		{
			input.setText(InputText + "+");
		}
		if (e.getActionCommand().equals("-"))
		{
			input.setText(InputText + "-");
		}
		if (e.getActionCommand().equals("x")) 
		{
			input.setText(InputText + "x");
		}
		if (e.getActionCommand().equals("/")) 
		{
			input.setText(InputText + "/");
		}
            if (e.getActionCommand().equals("."))
        {
			input.setText(InputText + ".");
		}
           
        if (e.getActionCommand().equals("("))
        {
    		input.setText(InputText + "(");
        }
        if (e.getActionCommand().equals(")"))
        {
    	   input.setText(InputText + ")");
        }

        if (e.getActionCommand().equals("0.0")) 
        {
    	   input.setText(InputText + "0.0");
        }

		/** Here is the main control statement that evaluates the result
		 * as soon as the equals button will be pressed, the expression in the
		 * input text field will be evaluated
		 */
		if (e.getActionCommand().equals("=")) 
  {
		  Stack opstack = new Stack();
			/** Creating the input and output queues*/
					Queue inputQueue = new Queue();
					Queue outputQueue = new Queue();
					String exp;
				
					String str = input.getText();
					/**Dividing string into tokens
					 if the token is a number it will go to outputQueue
					 if the token is operator it will go into the stack according to
					 precedence */
					
					StringTokenizer st = new StringTokenizer(str, "+-*x/()", true);
					while (st.hasMoreTokens()) 
					{
						inputQueue.Enqueue(st.nextToken());
					}

					while (inputQueue.front != null) 
						
						/** A loop structured to continue until the input Queue is empty */
			  {
						String store = inputQueue.Dequeue();
						
		/** If the token is an operand, it will go straight
		 * in the output Queue
		 */
						
						if   (!(store.contains("+") || 
								store.contains("-") ||
								store.contains("*") ||
								store.contains("x") ||
								store.contains("(") ||
								store.contains(")") ||
								store.contains("/")))
						{
							outputQueue.Enqueue(store);
						}
						
						/** If the token is an operator (and not a bracket)
						 *  the precedence of the operator will be checked
						 *  (and also made sure that stack is not empty)
						 *  in case of the precedence of the current token being
						 *  less than the one previously stored in the stack(most recent), the
						 *  operator at the top of the stack will be popped.
						 *  
						 *  The operator with less precedence will be pushed
						 *  in the stack.
						 * 
						 *  
						 */
						
						else if       ((store.contains("+") || 
										store.contains("-") ||
										store.contains("x")||
										store.contains("*")||
										store.contains("/")))
						
					     {
						     while (opstack.top != null && (priority(opstack.top.data)) >= priority(store)) 
							{
								outputQueue.Enqueue(opstack.pop());
							}
							     opstack.push(store);

					     }
						/** In case of brackets, if the token is an opening 
						 * bracket it will be pushed in the stack right away
						 * 
						 * After that, whatever tokens come (in order to prioritize them)
						 * they will be entered in the output Queue by popping
						 */
						
						      else if (store.equals("(")) 
						      {
							    opstack.push(store);
						      }
						     
						      else  
						 {
						    	  while(!(opstack.top.data.equals("(")))
						    	  {
						    		   
							          outputQueue.Enqueue(opstack.pop());	    
						    	  }
						    	     opstack.pop(); /** Discarding the opening bracket*/
											
						  }  

			    }
						    			    		 
				              /** A loop to ensure all the tokens exit the stack
				               * 
				               */
			 
					while (opstack.top != null) 
					{
						outputQueue.Enqueue(opstack.pop());
						
					}
				/** Here a new stack is created for storing the answers
				 * or temporary answers (depending on the case)
				 */
		            Stack myStack = new Stack();	           
			 
				/** Here first it is made sure if the output queue is empty
				 * then the content(token inside) of output Queue is stored in a variable
				 * called "exp" (short for expression)
				 * 
				 * Then I make use of control statements by considering the
				 * two possibilities of the token i.e operator or operand
				 * 
				 * if it is an operand, it will just be stored in the new stack
				 * if it is an operator, it will evaluate the values stored in the
				 * new stack (by popping)
				 * 
				 * I have also used the Double.parseDouble function to
				 * parse the Stack content as it is set for string data type
				 */ 
				     while (outputQueue.front !=null)
				   {
					  exp = outputQueue.Dequeue();
				 
					   if (!(exp.equals("+") || exp.equals("-") ||
							   exp.equals ("*") || exp.equals("/") || exp.equals("x")))
					   {
						     myStack.push(exp);
					   }
					   
					   else 
			{
						   
						   double n2 = Double.parseDouble(myStack.pop());
						   double n1 = Double.parseDouble(myStack.pop());
						   String op = exp;
						   
						  /** Here cases are considered for operators
						   * and evaluated as I have declared n2 and assigned
						   * its value to the value of the FIRST pop therefore
						   * when dividing and subtracting it should be second in order.
						   * 
						   */
						   
						   switch (op) 
						   {
						   case "+":
							   ans = n1+n2;
							   break;
						   case "-":
							   ans = n1-n2;
							   
							   break;
						   case "*":
							   ans = n1*n2;
							   break;
						   case "x":
							   ans = n1*n2;
							   break;
							   
						   case "/" :
							   ans = n1/n2;
							 	  
							   break;
							   default:
								 print ("Enter valid operator");
								   break;	   
						   }
						   
						   /** The answer is pushed inside the stack so 
						    * the data at the top will be the answer so that as 
						    * the expression continues it will keep evaluating
						    * as it will store n2 as the first pop value (which will be the 
						    * temporary answer)
						    * 
						    * 
						    * 
						    * 
						    * Also to make sure if I enter just a single digit
						    *  it would print out the token as it is. 
						    */
						   
						  myStack.push(Double.toString(ans)); 
						  
			}	   
				      }
			      myResult = myStack.pop();
				/** The round method is called here and given the argument
				 * (decimalplaces) of the value of precision slider
				 */
				    output.setText(Double.toString(round(Double.parseDouble(myResult), precision.getValue())));
				    		 
	}
			} 
	
			 
				/**
				 * Method of setting priority, if the string consists
				 * + or - it will come later (compared to * and /)
				 * @param a is the token
				 * @return RETURNS A VALUE OF 0 IF + OR - IS THE TOKEN OR ElSE -1 if it is an 
				 * opening bracket or ELSE 1 (FOR * AND / ) 
				 * This method is used in the while loop above for ordering the tokens 
				 * according to precedence. 
				 * 
				 * -1 for the bracket as it should not be enqueued it should
				 * only be a recognizing token for terminating the while loop
				 * for evaluating all the tokens within the (---) part
				 */

				public static int priority(String a)
			 {
					if (a.equals("+") || a.equals("-"))
					{
						
						return 0;
					}
					else if (a.equals("(")||a.equals(")")) 
			    {
						return -1;                           
				}
					else  
					{
						return 1;
					} 
			}
				/**Method to round off the result, it is called each time when
				 * when the output JTextfield is set
				 * @param value This is the value to be rounded, in this case
				 * it is the answer the calculator produces.
				 * 
				 * @param d 
				 * This is the number of places to which the answer(value)
				 * is to be rounded off to i.e the value of the precision slider
				 * 
				 * @return This method returns the rounded off double result by a math
				 * method
				 */

public static double round(double value, int decimalplaces)
               {
				    double scale = Math.pow(10, decimalplaces);
				    return Math.round (value*scale)/scale;
               }
		
		
	public JTextField input;
	public JTextField output;
	String myResult;
	
		} 