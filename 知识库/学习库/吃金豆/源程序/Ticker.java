import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 **实现线程
 */
public class Ticker extends Thread{

	ActionListener al;
	private boolean isTicking;
	Thread t;
	int delay;

	public Ticker(int i, ActionListener actionlistener){
		al = actionlistener;
		delay = i;
		t = new Thread(this);
		t.start();
		isTicking = false;
	}

	public Ticker(int i){
		delay = i;
		t = new Thread(this);
		t.start();
		isTicking = false;
	}

	public void addActionListener(ActionListener actionlistener){
		if(al == null)
			al = actionlistener;
		else
			System.out.println("WARNING: ActionListener already added to Ticker.");
	}

	public boolean isRunning(){
		return isTicking;
	}

	public void start(){
		isTicking = true;
	}

	private void fireActionPerformed(){
		if(al == null || !isTicking){
			return;
		} 
		else{
			ActionEvent actionevent = new ActionEvent(this, 0, null);
			al.actionPerformed(actionevent);
			return;
		}
	}

	public void run(){
		do{
			fireActionPerformed();
			try{
				Thread.sleep(delay);
			}
			catch(InterruptedException interruptedexception){
				System.out.println("WARNING: Ticker thread interrupted.");
			}
		} while(true);
	}
}
