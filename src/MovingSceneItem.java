//this subclass provides the functionality for how game items should behave when moving.
public class MovingSceneItem extends GameItem
{
	private int xStep;
	private int yStep;

	public MovingSceneItem(String path, int x, int y, int w, int h, int xs, int ys)
	{
		super(path, x, y, w, h);
		xStep = xs;
		yStep = ys;
	}

	public void setMotion(int xs, int ys)
	{
		xStep = xs;
		yStep = ys;
	}

	public void setUPStep()
	{
	    xStep=0;
	    yStep=-1;
	}
	
	public void setDWStep()
	{
	    yStep=1;
	    xStep=0;
	}
	
	public void setLeftStep()
	{
	    xStep=-1;
	    yStep=0;
	}
	
	public void setRightStep()
	{
	    xStep=1;
	    yStep=0;
	}

	public int getXStep()
	{
		return xStep;
	}

	public int getYStep()
	{
		return yStep;
	}

	@Override public void update(int screenWidth, int screenHeight, String character)
	{
		if(!character.equals("Bird"))
		{
			xCoord += xStep;
			if (xCoord > screenWidth)
			{
				xCoord = 0;
			}
			else if (xCoord < 0)
			{
				xCoord = screenWidth;
			}

			yCoord += yStep;
			if (yCoord > screenHeight)
			{
				yCoord = 0;
			}
			else if (yCoord < 0)
			{
				yCoord = screenHeight;
			}
		}

	    if(character.equals("Bird"))
	    {
	        xCoord += xStep;
	        if(xCoord > screenWidth-100)
	        {
	            xCoord--;
	        }
	        else if (xCoord<0)
	        {
	            xCoord++;
	        }
	
	        yCoord+= yStep;
	        if(yCoord > screenHeight-60)
	        {
	            yCoord--;
	        }
	        else if(yCoord<0)
	        {
	            yCoord++;
	        }
	    }
	}
}
