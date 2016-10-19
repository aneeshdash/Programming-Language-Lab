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