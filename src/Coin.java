import java.io.IOException;
import java.nio.CharBuffer;

/* this class constructs a bee object and getter/setter to hold count value */
public class Coin extends GameItem implements Readable
{
  static int coin; // declared static to prevent user or any external source to alter coin value.

  public Coin(int x, int y)
  {
	  super("src/images/coin.gif", x, y, 25, 25);
  }

  public static void setCoinCount(int num)
  {
      coin=num;
  }

  public static int getCount()
  {
      return coin;
  }

  @Override
  public int read(CharBuffer cb) throws IOException
  {
		// TODO Auto-generated method stub
		return 0;
  }
}
