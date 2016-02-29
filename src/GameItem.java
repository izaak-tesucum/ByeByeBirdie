import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* SceneItem is the base class for all objects which will be added to a scene. */
public class GameItem
{
	private Image img;
	protected int xCoord;
	protected int yCoord;
	protected int width;
	protected int height;
	Thread th;
	JLabel lbl;

	public GameItem(String path, int x, int y, int w, int h)
	{
		xCoord = x;
		yCoord = y;
		setImage(path, w, h);
	}

	public void setImage(String path, int w, int h)
	{
		width = w;
		height = h;

		try
		{
			img=new ImageIcon(path).getImage();
		}

		catch (Exception e)
		{
			System.out.println("Can't load image file '" + path + "'");
		}
	}

	public void update(int width, int height, String character)
	{
		//update() is an empty function which will need to be overridden by the MovingSceneItem class, but for basic items nothing needs to be done.
	}

	/* method uses 'final' to prevent this method from being overridden*/
	public final void draw(Graphics g)
	{
		if (getImage() != null)
		{
			g.drawImage(new ImageIcon(img).getImage(), getXCoord(), getYCoord(), getWidth(), getHeight(), null);
		}
	}

	/*overlaps() takes another sceneItem as a parameter and return true if the current object overlaps with it. */
	public boolean overlaps(GameItem gi)
	{
		int iStartX = getXCoord();
		int iEndX = iStartX + getWidth();
		int iStartY = getYCoord();
		int iEndY = iStartY + getHeight();

		int jStartX = gi.getXCoord();
		int jEndX = jStartX + gi.getWidth();
		int jStartY = gi.getYCoord();
		int jEndY = jStartY + gi.getHeight();

		if (((iStartX <= jStartX && jStartX <= iEndX) || (iStartX <= jEndX && jEndX <= iEndX)) && ((iStartY <= jStartY && jStartY <= iEndY) || (iStartY <= jEndY+5 && jEndY <= iEndY)))
		{
			return true;
		}

		return false;
	}

	public Image getImage()
	{
		return img;
	}

	public int getXCoord()
	{
		return xCoord;
	}

	public int getYCoord()
	{
		return yCoord;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setWidth(int w)
	{
	    width=w;
	}
	
	public void setHeight(int h)
	{
	    height=h;
	}
	
	public void setXCoord(int num)
	{
	    xCoord=num;
	}
	
	public void setYCoord(int num)
	{
	    yCoord=num;
	}
}
