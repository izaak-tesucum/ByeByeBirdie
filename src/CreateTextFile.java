import java.lang.SecurityException;
import java.util.*;

/* this class writes to a file when user saves his/her game */
public class CreateTextFile
{
	private Formatter output;

	public void openFile()
	{
		try
		{
			output = new Formatter("mygame.txt");
		}

		catch(SecurityException securityException)
		{
			System.err.println("You do not have write access to this file. ");
			System.exit(1);
		}

		catch(FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening or creating file. ");
			System.exit(1);
		}
	}

	public synchronized void addObjects(ArrayList<GameItem> gameCharacters)
	{
		for(GameItem g: gameCharacters)
		{
			try
			{
				if (g.getClass().getName().equals("Bird"))
				{
					output.format("%s %d %d %d\n","Bird",g.getXCoord(),g.getYCoord(),0);
				}
				else if (g.getClass().getName().equals("Helicopter"))
				{
					output.format("%s %d %d %d\n","Helicopter",g.getXCoord(),g.getYCoord(),0);
				}
				else if (g.getClass().getName().equals("Coin"))
				{
					output.format("%s %d %d %d\n","Coin",g.getXCoord(),g.getYCoord(),Coin.getCount());
				}
			}

			catch(FormatterClosedException formatterClosedException)
			{
				System.err.println("An error occurred while saving. Please try again. ");
			}
		}
	}


	public void closeFile()
	{
		if(output != null)
		{
			output.close();
		}
	}

}
