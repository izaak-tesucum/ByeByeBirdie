import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/* this class as the name suggests creates and controls the gameplay as well as creates the layout of the game menu. */
public class GamePlay
{
	JLabel statusLabel;
	
	public static void main(String args[])
	{
		int sleepDuration =100;
		if (args.length > 0)
		{
			sleepDuration = Integer.parseInt(args[0]);
		}
		
		GameFrame frame = new GameFrame(sleepDuration);
		frame.setVisible(true);
	}
}

class GameFrame extends JFrame
{
	JLabel statusLabel;
	Scene panel;
	JButton New;
	JButton Load;
	JButton Instructions;
	JButton Exit;
	JPanel Panel;
	JPanel Panel2;
	JPanel Panel3;

	public GameFrame(int sleepDuration)
	{
		setTitle("BYE BYE BIRDIE");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		statusLabel = new JLabel("Welcome to a NEW experience");
		panel = new Scene(new ImageIcon ("src/images/background.gif").getImage(),statusLabel, sleepDuration);
		add(panel);
		
		// create the status bar panel and shove it down the bottom of the frame
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(getWidth(), 20));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		final JFrame StartFrame= new JFrame();
		StartFrame.setTitle("BYE BYE BIRDIE START MENU");
		StartFrame.setLayout(new BorderLayout());
		StartFrame.setSize(600, 400);
		StartFrame.setLocation(350,200);
		StartFrame.setAlwaysOnTop(true);
		StartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Font myFont = new Font("Times New Roman", Font.BOLD,60);
		
		//Adding Buttons
		Panel= new JPanel();
		New=new JButton("New Game");
		Load= new JButton("Load Game");
		Instructions= new JButton("Instructions");
		Exit=new JButton("Exit");
		New.setFont(myFont);
		Load.setFont(myFont);
		Instructions.setFont(myFont);
		Exit.setFont(myFont);
		New.setBackground(Color.gray);
		Load.setBackground(Color.gray);
		Instructions.setBackground(Color.gray);
		Exit.setBackground(Color.gray);
		New.setForeground(Color.cyan);
		Load.setForeground(Color.cyan);
		Instructions.setForeground(Color.cyan);
		Exit.setForeground(Color.cyan);
		
		StartFrame.setBackground(Color.darkGray);
		
		//Button Layout
		GridLayout Lay=new GridLayout(4,1,25,25);
		Panel.setLayout(Lay);
		Panel.add(New);
		Panel.add(Load);
		Panel.add(Instructions);
		Panel.add(Exit);
		Panel.setBackground(Color.darkGray);
		
		StartFrame.add(Panel, BorderLayout.CENTER);
		StartFrame.setVisible(true);
		
		New.addActionListener( //let's start a new game
		    new ActionListener()
		     {
		         @Override
		         public void actionPerformed(ActionEvent e)
		         {
		             panel.setRandomSeed(-5);
		             panel.reset();
		             panel.toggleBird();
		             panel.toggleHelicopter();
		             panel.Pause();
		             StartFrame.dispose();
		         }
		     }
		);
		
		Load.addActionListener(
		    new ActionListener()
		     {
		         @Override
		         public void actionPerformed(ActionEvent e)
		         {
		             panel.load(panel);
		             StartFrame.dispose();
		         }
		     }
		);
		
		Instructions.addActionListener(
		    new ActionListener()
		     {
		         @Override
		         public void actionPerformed(ActionEvent e)
		         {
		             String GameInfo= new String();
		
		             String Objective= "OBJECTIVE\n"
		                     + " Collect all the coins while dodging the helicopter, once all coins are collected you win! \n";
		
		             String Collisions= "COLLISIONS\n"
		                     + "Bird collides with insect, score increases\n"
		                     + "Bird collides with helicopter, BYE BYE BIRDIE :)\n";
		
		             String Info= "IMPORTANT INFO\n"
		            		 + "Press 'r' to TO START PLAY after Loading, or staring a New Game\n"
		                     + "The Bird Cannot go through the screen to the other side\n"
		                     + "When the Trophy Pop's up YOU WIN!! :) :)\n"
		                     + "The Bird CONTINUES MOVING in the same direction until it turns to another directs\n"
		                     + "The SAVE and LOAD are located under FILE on the menu bar\n"
		                     + "Number of insects collected appear in the bottom left hand corner HINT there are 30!!\n"
		                     + "If you die you have to go click FILE-> NEW GAME to play again\n";
		
		             String Controls= "BUTTON CONTROLS\n"
		                     + "'p' = PAUSE\n"
		                     + "'r' = RESUME\n"
		                     + "Arrow Keys = Control Bird Direction accordingly\n"
		                     + "Right Click = Also gives the option to PAUSE/RESUME\n";
		
		             GameInfo=Objective +"\n"+Collisions+"\n"+Info+"\n"+Controls+"\n";
		
		             JOptionPane.showMessageDialog(StartFrame, GameInfo,"Instructions",JOptionPane.PLAIN_MESSAGE);
		         }
		     }
		);
		
		Exit.addActionListener(
		  new ActionListener()
		  {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		         System.exit(0);
		     }
		  }
		);
		
		addKeyListener(new MyKeyListener());
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		JMenuItem newitem = new JMenuItem("New Game");
		newitem.setMnemonic('N');
		fileMenu.add(newitem);
		
		newitem.addActionListener(
		  new ActionListener()
		  {
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		      panel.Pause();
		      int n= JOptionPane.showConfirmDialog(GameFrame.this, "Would you like to start a NEW game?", "New Game", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		      if(n==JOptionPane.YES_OPTION)
		      {
		      	 panel.setRandomSeed(-5);
		      	 panel.reset();
		      }
		    }
		   }
		);
		
		JMenuItem loaditem = new JMenuItem("Load Game");
		loaditem.setMnemonic('L');
		fileMenu.add(loaditem);
		
		loaditem.addActionListener(
		  new ActionListener()
		  {
		    @Override
		    public void actionPerformed(ActionEvent e)
	 	    {
	        	panel.Pause();
	                int n= JOptionPane.showConfirmDialog(GameFrame.this, "Do you wish to Load your previous game?","Load Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	                if(n==JOptionPane.YES_OPTION)
	                {
	                   panel.load(panel);
	                }
	 	     }
		  }
		);
		
		JMenuItem saveitem = new JMenuItem("Save Game");
		saveitem.setMnemonic('S');
		fileMenu.add(saveitem);
		
		saveitem.addActionListener(
		  new ActionListener()
		  {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		         panel.Pause();
		         panel.save();
		     }
		   }
		);
		
		JMenuItem seitem = new JMenuItem("Save & Exit");
		seitem.setMnemonic('M');
		fileMenu.add(seitem);
		
		seitem.addActionListener(
		  new ActionListener()
		  {
		     @Override
		     public void actionPerformed(ActionEvent e)
		     {
		       panel.Pause();
		       int n=  JOptionPane.showConfirmDialog(GameFrame.this, "Do you wish to SAVE your progress before  you exit?","Save & Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		       if(n==JOptionPane.YES_OPTION)
		       {
		           panel.save();
		           System.exit(0);
		       }
		
		       if(n==JOptionPane.NO_OPTION)
		       {
		           System.exit(0);
		       }
		     }
		   }
		);
		
		JMenuItem exititem = new JMenuItem("Exit");
		exititem.setMnemonic('E');
		fileMenu.add(exititem);
		
		exititem.addActionListener(
	         new ActionListener()
	         {
	          	 @Override
	               public void actionPerformed(ActionEvent e)
	          	   {
	                  panel.Pause();
	                  int n= JOptionPane.showConfirmDialog(GameFrame.this, "Are you sure you want to Exit without Saving?","Exit", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	
	                  if(n==JOptionPane.YES_OPTION)
	                  {
	                      System.exit(0);
	                  }
	               }
	          }
		);
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
	
		JMenu instructions = new JMenu("Instructions");
		instructions.setMnemonic('I');
	
		JMenuItem instructionitem = new JMenuItem("Instructions");
		instructions.add(instructionitem);
		
		instructionitem.addActionListener(
		  new ActionListener()
		  {
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		      String GameInfo= new String();
		
		      panel.Pause();
		
		      String Objective= "OBJECTIVE\n"
		             + " Collect all the insects while dodging the helicopter, once all insects are collected you win! \n";
		
		
		      String Collisions= "COLLISIONS\n"
		             + "Bird collides with insect, score increases\n"
		             + "Bird collides with helicopter, BYE BYE BIRDIE :)\n";
		
		      String Info= "IMPORTANT INFO\n"
		    		 + "Press 'r' to TO START PLAY after Loading, or staring a New Game\n"
		             + "The Bird Cannot go through the screen to the other side\n"
		             + "The Bird CONTINUES MOVING in the same direction until it turns to another directs\n"
		             + "The SAVE and LOAD are located under FILE on the menu bar\n"
		             + "When the Trophy Pop's up YOU WIN!! :) :)\n"
		             + "Number of insects collected appear in the bottom left hand corner HINT there are 30!!\n"
		             + "If you die you have to go click FILE-> NEW GAME to play again\n";
		
		      String Controls= "BUTTON CONTROLS\n"
		             + "'p' = PAUSE\n"
		             + "'r' = RESUME\n"
		
		             + "Arrow Keys = Control Bird Direction accordingly\n"
		             + "Right Click = Also gives the option to PAUSE/RESUME\n";
		
		
		      GameInfo=Objective +"\n"+Collisions+"\n"+Info+"\n"+Controls+"\n";
		
		
		      JOptionPane.showMessageDialog(StartFrame, GameInfo,"Instructions",JOptionPane.PLAIN_MESSAGE);
		      panel.Resume();
		     }
		   }
		 );
		
		 bar.add(instructions);
	
		 final JMenuItem pauseitem = new JMenuItem("Pause Game");
		 final JMenuItem resumeitem = new JMenuItem("Resume Game");
		 final JPopupMenu popupMenu = new JPopupMenu();
		 popupMenu.add(pauseitem);
	
		 pauseitem.addActionListener(new ActionListener()
	 	 {
	         @Override
	          public void actionPerformed(ActionEvent e)
	          {
	            panel.Pause();
	            JOptionPane.showMessageDialog(GameFrame.this, "Game Paused.","Pause Game", JOptionPane.PLAIN_MESSAGE);
	          }
		  }
		 );
	
		 popupMenu.add(resumeitem);
		
		resumeitem.addActionListener(new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		      JOptionPane.showMessageDialog(GameFrame.this, "Game Resumed.","Resume Game", JOptionPane.PLAIN_MESSAGE);
		      panel.Resume();
		    }
		}
	     );
		
	     addMouseListener(
	       new MouseAdapter()
		{
		       @Override
		       public void mousePressed(MouseEvent e)
		       {
		    	 triggerevent(e);
		       }
	
	   		@Override
			public void mouseReleased(MouseEvent e)
			{
			  triggerevent(e);
			}
	
		        private void triggerevent(MouseEvent e)
		        {
		          if(e.isPopupTrigger())
			  {
		    	   popupMenu.show(e.getComponent(), e.getX(), e.getY());
		          }
		        }
	         }
	     );
	}

	/* this class provides the keys for the game commands */
	private class MyKeyListener extends KeyAdapter
	{
		@Override public void keyPressed(KeyEvent e)
		{
			if (e.getKeyChar() == 'g')
			{
				  panel.reset();
			}
			else if(e.getKeyChar()== 'p')
			{
			    panel.Pause();
			}
			else if(e.getKeyChar()=='r')
			{
			    panel.Resume();
			}
			else if ((e.getKeyCode()== KeyEvent.VK_DOWN) || (e.getKeyCode()== KeyEvent.VK_KP_DOWN))
			{
					panel.MoveBird("Down");
				}
			else if ((e.getKeyCode()== KeyEvent.VK_UP) || (e.getKeyCode()== KeyEvent.VK_KP_UP))
			{
					panel.MoveBird("UP");
				}
			else if ((e.getKeyCode()== KeyEvent.VK_RIGHT) || (e.getKeyCode()== KeyEvent.VK_KP_RIGHT))
			{
					panel.MoveBird("Right");
				}
			else if ((e.getKeyCode()== KeyEvent.VK_LEFT) || (e.getKeyCode()== KeyEvent.VK_KP_LEFT))
			{
					panel.MoveBird("Left");
			}
		}
	}
}
