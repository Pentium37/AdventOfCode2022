package solutions.day9;

/**
 * @author Yalesan Thayalan {@literal <yalesan2006@outlook.com>}
 */
public class Vector {
	int x;
	int y;

	public Vector() {
		this.x = 0;
		this.y = 0;
	}

	public Vector(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public void moveUp() {
		y++;
	}

	public void moveDown() {
		y--;
	}

	public void moveRight() {
		x++;
	}

	public void moveLeft() {
		x--;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}
