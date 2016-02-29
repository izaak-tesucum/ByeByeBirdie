import java.io.*;
import java.lang.IllegalStateException;
import java.util.*;

/* this class loads a game from previously saved one. */
public class ReadTextFile
{
	private Scanner input;

	public synchronized void openFile()
	{
		try
		{
			input= new Scanner(new File("mygame.txt"));
		}

		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file. ");
			System.exit(1);
		}
	}

	public synchronized void readObjects(ArrayList<GameItem> gameCharacters, Scene s)
	{
		Scene sc=s;
		int x=-100000,y=-100000,xs=-100000,ys=-100000;
		String name,st="hello";
		Bird b = new Bird(x,y,xs,ys); // create bird object with default coordinates above.

		try
		{
			while(input.hasNext())
			{
				name=input.next();
				b.setXCoord(input.nextInt()); // set x coordinate to x coordinate read from file.
				b.setYCoord(input.nextInt()); // set y coordinate to y coordinate read from file.
				Coin.setCoinCount(input.nextInt()); // set coin count to the count when game was last saved.
				sc.addScene2("%s %d %d %d\n",name,b.getXCoord(),b.getYCoord(),Coin.getCount());	// start the game with these values.
			}
		}

	    catch(FormatterClosedException formatterClosedException) // if formatter closed unexpectedly throw error.
	    {
		   System.err.println("Error occurred while loading. Please try again. ");
	    }

		catch (NoSuchElementException elementException) // if element being requested does not exist throw error.
		{
			System.err.println("file improperly formed");
			System.exit(1);
		}

		catch (IllegalStateException stateException) // if method is invoked, and application isn't in appropriate state throw error.
		{
			System.err.println("Error reading from file");
			System.exit(1);
		}
	}

	public synchronized void closeFile()
	{
		if(input != null)
		{
			input.close();
		}
	}
}
