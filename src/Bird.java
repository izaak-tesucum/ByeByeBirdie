import java.io.IOException;
import java.nio.CharBuffer;

/* this class creates a bird object */
public class Bird extends MovingSceneItem implements Readable
{
	public Bird(int x, int y, int xstep, int ystep)
	{
		super("src/images/bird.gif", x, y, 80, 60, xstep, ystep);
	}

	@Override
	public int read(CharBuffer cb) throws IOException
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
