/**
 * jiaxix
 * 08600
 * 2014-10-03
 */


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;



//you can add a ref to the timerThread, to get the remaining time
public class MoleThread extends Thread implements ActionListener,MouseListener {
	
	private JButton moleHole;
	private JTextField timeField;
	private JTextField scoreField;
	private JPanel holePanel;
	private Random random = new Random();
	
	Toolkit tk=Toolkit.getDefaultToolkit();  
	//java.net.URL imgURL = this.getClass().getResource("./resource/hammer.png");
	Image img=tk.getImage("./resource/hammer.png"); 
	Cursor cu=tk.createCustomCursor(img,new Point(10,10),"stick"); 
	//java.net.URL imgURL1 = this.getClass().getResource("./resource/hammer1.png");
	Image img2=tk.getImage("./resource/hammer1.png"); 
	Cursor cu2=tk.createCustomCursor(img2,new Point(10,10),"stick"); 
	
	public MoleThread(JButton hole, JTextField timeField, JTextField scoreField, JPanel holePanel) {
		// TODO Auto-generated constructor stub
		this.moleHole = hole;
		this.timeField = timeField;
		this.scoreField = scoreField;
		this.holePanel=holePanel;
		
	}
	
	public void run() {

		System.out.println("Start one molehole for:" + Thread.currentThread().getName());
		moleHole.addActionListener(this);
		moleHole.addMouseListener(this);
		
		long randomStartTime;
		long randomUpTime;
		long randomDownTime;
		long remainingTime;
		
		randomStartTime = (long)random.nextInt(4000);
		try {
			Thread.sleep(randomStartTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// attention for the up-status of mole. if the ramdon up time is 3s but the game will stop in 1s, choose the min of the two
		while(!timeField.getText().equals("0")){
			// UP~~
			
			if(timeField.getText().equals(""))
				continue;
			
			moleHole.setBackground(Color.PINK);
			moleHole.setIcon(new ImageIcon("./resource/up.png"));
			
			randomUpTime = (long)random.nextInt(3500);
			randomUpTime +=500;
			remainingTime = Long.parseLong(timeField.getText())*1000;
			if(remainingTime == 0)
				break;
			
			if(randomUpTime>remainingTime){
				try {
					Thread.sleep(remainingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//moleHole.setText(" ");
				break;
			}
			
			else{
				try {
					Thread.sleep(randomUpTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			// DOWN~~
			moleHole.setBackground(Color.GREEN);
			//moleHole.setText(" ");
			moleHole.setIcon(new ImageIcon("./resource/down.png"));
			
			randomDownTime = (long)random.nextInt(2000);
			randomDownTime += 2000;
			remainingTime = Long.parseLong(timeField.getText())*1000;
			if(remainingTime == 0)
				break;
			
			if(randomDownTime > remainingTime){
				try {
					Thread.sleep(remainingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			else{
				try {
					Thread.sleep(randomDownTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			
		}


		//System.out.println("End game for:" + Thread.currentThread().getName());		
		moleHole.setBackground(Color.GRAY);
		moleHole.setIcon(new ImageIcon("./resource/down.png"));
		moleHole.setEnabled(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		//System.out.println("Clicked button:"+Thread.currentThread().getName());
		int score;
		if((moleHole.getBackground() == Color.PINK)&&(!timeField.getText().equals("0"))){
			System.out.println("HHHHHIT");
			score = Integer.parseInt(scoreField.getText());
			score++;
			synchronized (scoreField) {
				scoreField.setText(String.valueOf(score));
			}
			System.out.println("Score is:" + scoreField.getText());
			moleHole.setBackground(Color.ORANGE);
			moleHole.setIcon(new ImageIcon("./resource/hit.png"));
		}
		
		if(timeField.getText().equals("0")){
			moleHole.setBackground(Color.GRAY);
			moleHole.setIcon(new ImageIcon("./resource/down.png"));
			moleHole.setEnabled(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		holePanel.setCursor(cu2);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		holePanel.setCursor(cu);
	}
}

