/**
 * jiaxix
 * 08600
 * 2014-10-03
 */

import java.awt.Color;

import javax.swing.*;

public class TimerThread extends Thread {
	private int counter;
	private JTextField timeField;
	private JButton startButton;
	private JButton []moleHoles;
	
	public TimerThread(JTextField timeField, JButton startButton, int count, JButton[] holes) {
		// TODO Auto-generated constructor stub
		this.counter = count;
		this.timeField = timeField;
		this.startButton = startButton;
		this.moleHoles = holes;
		
	}

	
	public void run() {
		String time = String.valueOf(counter);
		//timeField.setText(time);
		//System.out.println("Remaining time:" + time);
		while(counter>0){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter--;
			time = String.valueOf(counter);
			synchronized (timeField) {
				timeField.setText(time);
			}
			
			//System.out.println("Remaining time:" + time);
		}
		
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<16;i++){
			if(moleHoles[i].getBackground() != Color.GRAY){
				moleHoles[i].setBackground(Color.GRAY);
				moleHoles[i].setEnabled(false);
			}
		}
		startButton.setEnabled(true);
	}		
}// End of private static class imerThread



