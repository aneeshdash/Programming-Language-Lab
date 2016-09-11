import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.*;

import static java.awt.event.KeyEvent.*;

//extends the base class Calculator containing the basic UI
//implements the event listeners
class Calc1 extends Calculator implements KeyListener
{
    //Toggling between operator and number
	static boolean  toggle=true;

	Calc1()
    {
		//giving focus to JFrame for capturing keystrokes
		frame.requestFocus();
        frame.addKeyListener(this);  //adding key listener
	}


	//evaluate function to calculate the final string
	public static int evaluate(String expression)
	{
		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Integer> values = new Stack<Integer>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++)
		{
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

			// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9')
			{
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Integer.parseInt(sbuf.toString()));
			}

			// Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(')
				ops.push(tokens[i]);

				// Closing brace encountered, solve entire brace
			else if (tokens[i] == ')')
			{
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			}

			// Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' ||
					tokens[i] == '*' || tokens[i] == '/')
			{
				// While top of 'ops' has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

				// Push current token to 'ops'.
				ops.push(tokens[i]);
			}
		}

		// Entire expression has been parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty())
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));

		// Top of 'values' contains result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2)
	{
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static int applyOp(char op, int b, int a)
	{
		switch (op)
		{
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0)
					throw new
							UnsupportedOperationException("Cannot divide by zero");
				return a / b;
		}
		return 0;
	}


    public static void main(String...s)
    {
        Calc1 c = new Calc1(); //creating a new instance
    }


	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {

		
	}


	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode()==VK_ENTER && !start )
		{
			if(toggle)
			{
                //updating the selected number
				display.setText(display.getText().concat(Integer.toString(selnum.index)));

				//ending the number selecting thread
                selnum.bnum = false;
				toggle = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				//creating a new thread for selecting function
				Thread t2 = new Thread(new selfunc());
				t2.start();
			}
			else
			{
                //updating the selected function
				display.setText(display.getText().concat(" " + selfunc.func + " "));

                //destroying the thread
                selfunc.bfunc = false;
                toggle = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//creating new thread for selecting number
				Thread t1 = new Thread(new selnum());
				t1.start();
			}

		}
		if(e.getKeyCode() == VK_S)
		{
            //checking if we have to start the caluculator or end it
			if(start)
			{
                //starting the number selecting thread
				Thread t1 = new Thread(new selnum());
				t1.start();
				toggle = true;
				start = false;
                //resetting the display
				display.setText("");
			}
			else
			{
                //stopping the threads
				selnum.bnum = false;
				selfunc.bfunc = false;

				start = true;
                //catching the thrown exception if any error occurs
                toggle=true;
                try {
                    //evaluating the string and displaying
                    display.setText(Integer.toString(evaluate(display.getText())));
                }
                catch (UnsupportedOperationException excep)
                {
                    //setting the displayed content error if error occurs
                    display.setText("error");
                }catch (EmptyStackException ex)
                {
                    //setting the displayed content error if error occurs
                    display.setText("error");
                }catch (Exception excep2)
                {
                    //for catching rest of exceptions
                    display.setText("error");
                }

			}
		}
		if(e.getKeyCode() == VK_C)
		{
            //resetting the display
			display.setText("");
		}
	}
}