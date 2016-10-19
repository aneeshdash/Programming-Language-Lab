import java.awt.Color;

public class selfunc implements Runnable{

	private static int index=0;
	public static String func = "+";
	public static volatile boolean bfunc = true; 
    public selfunc()
    {
		//index stores the current highlighted button
    	index=0;
		// trigger gor completing and destroying of thread
    	bfunc = true;
    }
	@Override
	public void run() {
		while(bfunc)
		{
			//checking which button is pressed
			switch(index)
			{
				//highlighting the button
				case 0: Calculator.badd.setBackground(Color.red);
					func = "+";	
					break;
				case 1: Calculator.bsub.setBackground(Color.red);
					func = "-";
					break;
				case 2: Calculator.bmul.setBackground(Color.red);
					func = "*";
					break;
				case 3: Calculator.bdiv.setBackground(Color.red);
					func = "/";
					break;
			}
			
			try {
                //gicing user time to select
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(index)
			{
                //removing the highlight
				case 0: Calculator.badd.setBackground(null);
						break;
				case 1: Calculator.bsub.setBackground(null);
					break;
				case 2: Calculator.bmul.setBackground(null);
					break;
				case 3: Calculator.bdiv.setBackground(null);
					break;
			}
			index=(index+1)%4; //moving the thread to highlight another button
		}	
	}

}
