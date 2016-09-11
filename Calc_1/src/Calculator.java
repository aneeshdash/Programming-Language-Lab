import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by anirudh on 12/9/16.
 */

// base class for question3 part 1 and part 2
public class Calculator {
    public boolean start=true;
    JFrame frame;
    JLabel display;
    static JButton b1;
    static JButton b2;
    static JButton b3;
    static JButton b4;
    static JButton b5;
    static JButton b6;
    static JButton b7;
    static JButton b8;
    static JButton b9;
    static JButton b0;
    static JButton bdiv;
    static JButton bmul;
    static JButton bsub;
    static JButton badd;


    public Calculator() {

        frame=new JFrame("Calculator");
        display=new JLabel();

        //settinf background color and visibilty
        display.setBackground(Color.lightGray);
        display.setOpaque(true);

        //assigning labels to the button
        b1=new JButton("1");
        b2=new JButton("2");
        b3=new JButton("3");
        b4=new JButton("4");
        b5=new JButton("5");
        b6=new JButton("6");
        b7=new JButton("7");
        b8=new JButton("8");
        b9=new JButton("9");
        b0=new JButton("0");
        bdiv=new JButton("/");
        bmul=new JButton("*");
        bsub=new JButton("-");
        badd=new JButton("+");


        //setting display bounds
        display.setBounds(40,40,200,30);
        b0.setBounds(40,100,50,40);
        b1.setBounds(90,100,50,40);
        b2.setBounds(140,100,50,40);
        b3.setBounds(190,100,50,40);

        b4.setBounds(40,140,50,40);
        b5.setBounds(90,140,50,40);
        b6.setBounds(140,140,50,40);
        b7.setBounds(190,140,50,40);

        b8.setBounds(40,180,50,40);
        b9.setBounds(90,180,50,40);

        badd.setBounds(40,220,50,40);
        bsub.setBounds(90,220,50,40);
        bmul.setBounds(140,220,50,40);
        bdiv.setBounds(190,220,50,40);

        //making buttons unfocusable so that all keystrokes goes to frame
        b0.setFocusable(false);
        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);
        b7.setFocusable(false);
        b8.setFocusable(false);
        b9.setFocusable(false);
        badd.setFocusable(false);
        bsub.setFocusable(false);
        bmul.setFocusable(false);
        bdiv.setFocusable(false);


        //adding buttons and display
        frame.add(display);
        frame.add(b7);
        frame.add(b8);
        frame.add(b9);
        frame.add(bdiv);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
        frame.add(bmul);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(bsub);
        frame.add(b0);
        frame.add(badd);


        frame.setLayout(null);
        frame.setSize(280,300); //setting the frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //activating the close button
        frame.setResizable(false); //standard size of calculator
        frame.setVisible(true); //making it visible
        display.setText(""); //clearing the label

    }
    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();
        int len = expression.length();

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

}
