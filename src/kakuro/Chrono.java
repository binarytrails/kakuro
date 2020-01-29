package kakuro;
//@author Audrey St-Louis
//@brief Timer class 

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Chrono
{
		private  int hours=0;
		private int minutes=0;
		private int seconds=0;
		private int delay=1000;
		JLabel timer_label;
		JButton pause_button;
		JButton play_button;
		JButton submit_button;
		JButton newGame_button;
		JButton save_button;
		JPanel mainPanel;
		ActionListener timer_listener;

	/* Constructor : Creates all the UI components and add them on the panel. */
		public Chrono(JFrame appFrame, int x, int y)
		{
    		timer_label = new JLabel("0:"+hours+":"+minutes+":"+seconds); 
    		pause_button = new JButton("Pause");
    		play_button = new JButton("Play");
    		submit_button = new JButton("Submit");
    		newGame_button = new JButton("New Game");
    		save_button = new JButton("Save");
    		mainPanel = new JPanel();

    		// Set up
    		mainPanel.add(play_button);
    		mainPanel.add(pause_button);
    		mainPanel.add(submit_button);
    		mainPanel.add(newGame_button);
    		mainPanel.add(save_button);

    		appFrame.getContentPane().add(timer_label, BorderLayout.BEFORE_FIRST_LINE);
    		appFrame.getContentPane().add(mainPanel, BorderLayout.AFTER_LAST_LINE);
    		appFrame.pack();
    		appFrame.setSize(x,y); 
    		appFrame.setVisible(true);
		}
		
	
		
		/* Checks if the user have clicked on a button. If he clicks on pause, the timer will stop. If he clicks on start, the timer will start. If 
		 * he clicks on new game, the timer will restart and a new grid will appear on the screen. 
		 * The timer is set to start automatically when the application is launched since a game will already be ready to play.
		 * Need to implement : Save button
		 */
		public void timing() {

		
		timer_listener= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{	
				seconds++;
				if(seconds==60)
				{
					seconds=0;
					minutes++;
				}
				if(minutes==60)
				{
					minutes=0;
					hours++;
				}
				timer_label.setText("0:"+hours+":"+minutes+":"+seconds);
				
			}
		};
	
		Timer time = new Timer(delay,timer_listener);
		time.start();


	/* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will stop the timer. */	
		pause_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			time.stop();
			}
		});
	/* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will start the timer. */
		play_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				time.start();
			}
		});
		
	/* With the use of an Action Listener to know if the user has clicked on the button, this part of the loop method will restart the timer. */
		newGame_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			
					hours=0;
					minutes=0;
					seconds=0;
					timer_label.setText("0:"+hours+":"+minutes+":"+seconds);
					time.start();
				
			}
		});
		
		/* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will perform the validation. */
		submit_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			// Call validation method
			}
		});
		
		/* With the use of an Action Listener to know if the user has clicked on the button, this part of the method will save the current state of
		 * the game.
		 *  */
		save_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			// Call save method
			}
		});

		
	}
}

