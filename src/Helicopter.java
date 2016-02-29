import java.io.IOException;
import java.nio.CharBuffer;

/* this class constructs helicopter object. */
public class Helicopter extends MovingSceneItem implements Readable
{
	public Helicopter(int x, int y, int xstep, int ystep)
	{
		super("src/images/helicopter2.gif", x, y, 100, 80, xstep, ystep);
	}

	@Override
	public int read(CharBuffer arg0) throws IOException
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
