import java.awt.Color;

public class selnum implements Runnable {

	public static int index=0;
	public static volatile boolean bnum = true;
    public selnum()
    {
		//index stores the current highlighted button
    	index=0;
		// trigger for completing and destroying of thread
    	bnum = true;
    }
	public void run() {
		while(bnum)
		{
			//checking which button is pressed
			switch(index)
			{
				//highlighting the button
				case 0: Calculator.b0.setBackground(Color.yellow);
						break;
				case 1: Calculator.b1.setBackground(Color.yellow);
					break;
				case 2: Calculator.b2.setBackground(Color.yellow);
					break;
				case 3: Calculator.b3.setBackground(Color.yellow);
					break;
				case 4: Calculator.b4.setBackground(Color.yellow);
					break;
				case 5: Calculator.b5.setBackground(Color.yellow);
					break;
				case 6: Calculator.b6.setBackground(Color.yellow);
					break;
				case 7: Calculator.b7.setBackground(Color.yellow);
					break;
				case 8: Calculator.b8.setBackground(Color.yellow);
					break;
				case 9: Calculator.b9.setBackground(Color.yellow);
					break;
			}
			
			try {
				//giving user time to select
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(index)
			{
				//removing the highlight
				case 0: Calculator.b0.setBackground(null);
						break;
				case 1: Calculator.b1.setBackground(null);
					break;
				case 2: Calculator.b2.setBackground(null);
					break;
				case 3: Calculator.b3.setBackground(null);
					break;
				case 4: Calculator.b4.setBackground(null);
					break;
				case 5: Calculator.b5.setBackground(null);
					break;
				case 6: Calculator.b6.setBackground(null);
					break;
				case 7: Calculator.b7.setBackground(null);
					break;
				case 8: Calculator.b8.setBackground(null);
					break;
				case 9: Calculator.b9.setBackground(null);
					break;
			}
			index=(index+1)%10;//moving the thread to highlight another button
		}
	}

}
