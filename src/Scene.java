import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import java.lang.Runnable;

/*this class paints the scene of our hero 'birdie', our foes 'deadly helicopters', and the beautiful skies for birdie to fly.*/
public class Scene extends JPanel
{
  protected ArrayList<GameItem> gameCharacters;
  private JLabel statusLabel;
  private long randomSeed;
  private Random random;
  private String statusBarText;
  private Image img;
  private Boolean BirdMoving;
  private Boolean HelicopterMoving;
  private Iterator<GameItem> iterator;
  private TUpdate b,h;
  private ExecutorService application;
  private Boolean BirdRight;
  int SX;
  int SY;
  int SXS;
  int SYS;
  int EX;
  int EY;
  int EXS;
  int EYS;
  int CX;
  int CY;

   public Scene(Image img, JLabel sl, int sleepDuration)
   {
      this.img= img;
      Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); // set the height and weight of the scene (image).
      setPreferredSize(size);
      setMinimumSize(size);
      setMaximumSize(size);
      setSize(size);
      setLayout(null);
      gameCharacters = new ArrayList<GameItem>();
      statusLabel = sl;
      random = new Random();
      randomSeed = 101;
      BirdMoving=false; // start the bird off as stationery.
      HelicopterMoving=false; // start the helicopter off as stationery.
      BirdRight=true; // let the bird be on the right of the screen.
      application= Executors.newCachedThreadPool();
      b= new TUpdate("Bird", this, sleepDuration);
      h= new TUpdate("Helicopter",this, 105);
      application.execute(b);
      application.execute(h);
      application.shutdown();
   }

   public Scene(String img, JLabel sl, int sleepDuration)
   {
      this(new ImageIcon(img).getImage(), sl, sleepDuration);
   }

	public void setRandomSeed(long rs)
	{
		randomSeed = rs;
	}

	public void save() // save the game.
	{
		CreateTextFile s = new CreateTextFile();
		s.openFile();
		s.addObjects(gameCharacters);
		s.closeFile();
	}

	public boolean load(Scene s) // load the game
	{
		gameCharacters.clear();
		Coin.setCoinCount(0);
		ReadTextFile l = new ReadTextFile();
		l.openFile();
		l.readObjects(gameCharacters,s);
		l.closeFile();
		updateStatusBar();
		return true;
	}

	private synchronized void addScene() //Add Items to the Screen
	{
	    addSceneItems(1, "Bird");
	    addSceneItems(7, "Helicopter");
	    addSceneItems(30, "Coin");
	    
		repaint();
	}

	protected synchronized void addScene2(String s, String type, int x, int y,int coincount)
	{
		addSceneItems(type, x, y);
		Coin.setCoinCount(coincount);
		repaint();
	}

	protected void addSceneItems(String type, int x, int y)
	{
		Dimension dim = getSize();
		dim.setSize(dim.getWidth()-30, dim.getHeight()-30);

		if (type.equals("Helicopter"))
		{
			int xs = -1;
			int ys = 0;

			gameCharacters.add(new Helicopter(x, y, xs,ys));
		}
		else if (type.equals("Bird"))
		{
			int xs = 0;
			int ys = 0;

			gameCharacters.add(new Bird(x, y, xs,ys));
		}
		else if (type.equals("Coin"))
		{
			gameCharacters.add(new Coin(x, y));
		}
	}

	private void addSceneItems(int num, String type) // called by addScene() to place Items on the scene
	{
		Dimension dim = getSize();
		dim.setSize(dim.getWidth()-30, dim.getHeight()-30);

		for (int i=0; i<num; i++)
		{
         if (type.equals("Bird"))
         {
	          int x = 5;
	          int y = (dim.height/2);
	          int xs = 0;
	          int ys = 0;
	          SXS=xs;
						SYS=ys;
						SX=x;
						SY=y;

            gameCharacters.add(new Bird(x, y, xs, ys));
         }

         if(type.equals("Helicopter"))
         {
             int x = random.nextInt(dim.width);
             int y = random.nextInt(dim.height);

             if((x<=40) || (y==dim.height/2 || y==(dim.height/2)+5 || y==(dim.height/2)-5 ))
             {
                 x=x+60;
                 y=y+30;
             }

             int xs = -1;
             int ys = 0;
             EX=x;
					   EY=y;
					   EXS=xs;
					   EYS=ys;

             gameCharacters.add(new Helicopter(x, y, xs,ys));
         }

         if(type.equals("Coin"))
         {
             int x= random.nextInt(dim.width)+10;
             int y = random.nextInt(dim.height)-5;
             gameCharacters.add(new Coin(x,y));
             CX=x;
             CY=y;
         }
		}
}

  public synchronized void MoveBird(String Type) // based on the key pushed birdie's image and direction will change.
  {
      Image img= new ImageIcon("src/images/blood.png").getImage();
      Image img2= new ImageIcon("src/images/trophy.gif").getImage();
      for (GameItem gi: gameCharacters)
      {
         repaint();
          if(Type.equals("UP"))
          {
            if("Bird".equals(gi.getClass().getName()))
            {
                if(gi.getImage()!= img && gi.getImage()!=img2)
                {
	                gi.setImage("src/images/bird3.gif", 80, 60);
	                ((MovingSceneItem)gi).setUPStep();
                }
            }
          }

          if(Type.equals("Down"))
          {
            if("Bird".equals(gi.getClass().getName()))
            {
                if(gi.getImage()!= img && gi.getImage()!=img2)
                {
	                gi.setImage("src/images/bird4.gif", 80, 60);
	                ((MovingSceneItem)gi).setDWStep();
                }
            }
          }

          if(Type.equals("Right"))
          {
            if("Bird".equals(gi.getClass().getName()))
            {
                if(gi.getImage()!= img && gi.getImage()!=img2)
                {
                  gi.setImage("src/images/bird.gif", 80, 60);
                  ((MovingSceneItem)gi).setRightStep();
                }
            }
          }

         if(Type.equals("Left"))
          {
            if("Bird".equals(gi.getClass().getName()))
            {
	            if(gi.getImage()!= img && gi.getImage()!=img2)
	            {
		            gi.setImage("src/images/bird2.gif", 80, 60);
		            ((MovingSceneItem)gi).setLeftStep();
	            }
            }
          }
      }
  }

//  resets the scene
  public void reset()
  {
	Coin.setCoinCount(0);
	gameCharacters.clear();

	if (randomSeed < 0)
	{
		randomSeed = System.currentTimeMillis();
	}

	random.setSeed(randomSeed);
	addScene();
	updateStatusBar();
  }

  public void updateScene()
  {
    updateScene(null);
  }
	//upadates the scene

  public synchronized void updateScene(String ID)
  {
  	if(ID!=null)
    {
        for(int i=0; i<gameCharacters.size(); i++)
        {
            GameItem si=gameCharacters.get(i);
            Dimension dim=getSize();
            iterator=gameCharacters.iterator();
            repaint();

	         for(GameItem gi: gameCharacters)
	         {
	            if(ID.equals(gi.getClass().getName()))
	            {
	             	gi.update(dim.width, dim.height, gi.getClass().getName());
	            }
	          }

      		 si.update(dim.width, dim.height, si.getClass().getName());

	          for( GameItem compare: gameCharacters)
	          {
		          	int count=0;
		            repaint();

	              if("Bird".equals(si.getClass().getName()))
	              {
	                if(("Helicopter".equals(compare.getClass().getName()) && si.overlaps(compare))) // if birdie overlaps with helicopter, birdie dies.
	                 {
	                    	si.setImage("src/images/blood.png", 400, 400);
	                    	((MovingSceneItem)si).setMotion(0,0);
	                 }
	                 else if(("Coin".equals(compare.getClass().getName()) && si.overlaps(compare))) // if birdie overlaps with bee, increase coin count and delete hit coin
	                 {
	                      Coin.coin++;
	                      ((GameItem)compare).setXCoord(dim.height + 10000);
	                      ((GameItem)compare).setYCoord(dim.width + 10000);
	                      updateStatusBar();
	                      repaint();
	                      if(Coin.getCount()==30)
	                      {
	                          Pause();
	                          si.setXCoord(610);
	                          si.setYCoord(300);
	                          si.setImage("src/images/trophy.gif", 200, 200);
	                      }
	                  }
	               }
	            }
          }
      }
  }

	//  paintComponent() is the function used by the windowing system to draw the contents
	//  of the window and is called by the system itself when some portion of the window
	//  needs to be drawn or re-drawn
	public synchronized void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		for (GameItem gi : gameCharacters)
		{
			gi.draw(g);
		}
	}

	protected String getStatusBarText()
	{
		return statusBarText;
	}

	public void updateStatusBar()
	{
		statusBarText = "COIN COUNT "+Coin.getCount();

		if(Coin.getCount()==30)
		{
			statusBarText=" CONGRATULATIONS YOU WIN!!!!! :) :) :)";
		}

		statusLabel.setText(getStatusBarText());
	}

	public void toggleHelicopter()
	{
	  HelicopterMoving = !HelicopterMoving;
	  updateStatusBar();
	}

	public void toggleBird()
	{
	    BirdMoving= !BirdMoving;
	    updateStatusBar();
	}
   
	public boolean getHelicopterMoving()
	{
	    return HelicopterMoving;
	}

	public boolean getBirdMoving()
	{
	    return BirdMoving;
	}
	
	public void Pause()
	{
	    BirdMoving=false;
	    HelicopterMoving=false;
	}
	
	public void Resume()
	{
	    BirdMoving=true;
	    HelicopterMoving=true;
	}
}

class TUpdate implements Runnable
{
    String Identifier;
    Scene Scene2;
    int ST;

    public  TUpdate(String Choice, Scene scene,int sleeptime)
    {
        Scene2=scene;
        Identifier=Choice;
        ST=sleeptime;
    }

	@Override
	public void run()
	{
      while(true)
      {
          if(Identifier.equals("Bird")&& Scene2.getBirdMoving())
          {
              try
              {
                 Scene2.updateScene("Bird");
                 Thread.sleep(ST);
              }
              catch(Exception Sleep)
              {

              }
          }

          if(Identifier.equals("Helicopter") && Scene2.getHelicopterMoving())
          {
              try
              {
                 Scene2.updateScene("Helicopter");
                 Thread.sleep(ST);
              }
              catch(Exception Sleep)
              {

              }
          }
      }
	}
}
