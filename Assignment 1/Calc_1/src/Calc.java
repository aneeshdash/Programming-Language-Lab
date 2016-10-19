import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

//extends the base class Calculator containing the basic UI
//implements the event listeners
class Calc extends Calculator implements KeyListener
{
    Calc()
    {
        //giving focus to JFrame for capturing keystrokes
        frame.requestFocus();
        frame.addKeyListener(this); //adding key listener
    }
 

    public static void main(String...s)
    {
        Calc c = new Calc();
    }


	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()== KeyEvent.VK_ENTER && !start) {
            ////updating the display with selected number
            display.setText(display.getText().concat(Integer.toString(selnum.index)));
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE && !start) {
            //updating the display with selected operator
            display.setText(display.getText().concat(" " + selfunc.func + " "));
        }

        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            //starting and stopping the calculator
            if(start)
            {
                // starting the number highlighting thread
                Thread t1 = new Thread(new selnum());
                t1.start();

                //starting the fucnction highlighting thread
                Thread t2 = new Thread(new selfunc());
                t2.start();
                start = false;

                //if previous operation ends with error then clearing the label
                if(display.getText().contentEquals("error"))
                {
                    display.setText("");
                }

            }
            else
            {
                //stopping the threads
                selnum.bnum = false;
                selfunc.bfunc = false;
                start = true;

                //catching the thrown exception if any error occurs
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
        if(e.getKeyCode() == KeyEvent.VK_C)
        {
            //clearing the text
            display.setText("");
        }
		
	}
}