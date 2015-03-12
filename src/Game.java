/**
 * jiaxix
 * 08600
 * 2014-10-03
 */

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Game implements ActionListener{
	
	private static final int HOLE_NUM = 16;
	//private static final String HOLE = " ";
	private static final Color HOLE_COLOR = Color.GREEN;
	//private static final String MOLE = "M";
	private static final Color MOLE_COLOR = Color.RED;
	//private static final String HIT = "HH";
	private static final Color HIT_COLOR = Color.PINK;
	
	private static final int COUNT = 20;

	private JButton startButton;
	private JTextField timeField;
	private JTextField scoreField;
	private JButton [] holes;
	private JPanel holePanel;
	
	
	private Font buttonFont = new Font("Helvetica", Font.BOLD, 12);
	private Font labelFont = new Font("TimesRoman", Font.BOLD, 16);
	private Font textFont = new Font("Courier New", Font.BOLD, 12);
	
	private MoleThread [] moleThreads;
	
	public Game(){
	
		Toolkit tk=Toolkit.getDefaultToolkit();   
		Image img=tk.getImage("./resource/hammer.png"); 
		Cursor cu=tk.createCustomCursor(img,new Point(10,10),"stick"); 
		
		
		JFrame gameFrame = new JFrame("Whack-a-Mole");
		gameFrame.setSize(470, 500);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel table = new JPanel(new BorderLayout());
		
		//Control Panel
		JPanel controlPanel = new JPanel();
		startButton = new JButton("Start Game");
		startButton.setFont(buttonFont);
		startButton.addActionListener(this);
		JLabel timeLabel = new JLabel("Time left:");
		timeLabel.setFont(labelFont);
		timeField = new JTextField(8);
		timeField.setFont(textFont);
		timeField.setEditable(false);
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(labelFont);
		scoreField = new JTextField(8);
		scoreField.setFont(textFont);
		scoreField.setEditable(false);
		
		controlPanel.add(startButton);
		controlPanel.add(timeLabel);
		controlPanel.add(timeField);
		controlPanel.add(scoreLabel);
		controlPanel.add(scoreField);
		
		//Hole Panel
		holePanel = new JPanel(new GridLayout(4, 4));
		holePanel.setCursor(cu);
		holes = new JButton[HOLE_NUM];
		for(int i=0; i<HOLE_NUM; i++){
			holes[i] = new JButton();
			holes[i].setIcon(new ImageIcon("./resource/down.png"));
			holes[i].setEnabled(false);
			holePanel.add(holes[i]);
		}
		
		
		table.add(controlPanel, BorderLayout.PAGE_START);
		table.add(holePanel, BorderLayout.CENTER);
		
		gameFrame.setContentPane(table);
		gameFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game n = new Game();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("You've clicked start!");
		Thread myThread = Thread.currentThread();
		myThread.setName("main game thread");
		System.out.println("In the main thread: "+myThread.getName());
		startButton.setEnabled(false);
		
		scoreField.setText("0");
		timeField.setText("20");
		
		TimerThread timer = new TimerThread(timeField,startButton,COUNT, holes);
		timer.start();
		
		moleThreads = new MoleThread[HOLE_NUM];
		for(int i=0; i<HOLE_NUM;i++){
			holes[i].setEnabled(true);
			holes[i].setFont(buttonFont);
			holes[i].setOpaque(false);
			
			holes[i].setBackground(Color.GREEN);
			moleThreads[i] = new MoleThread(holes[i], timeField, scoreField, holePanel);
			moleThreads[i].setName("molehole"+i);
			moleThreads[i].start();
		}

		
		
	}
	


}
