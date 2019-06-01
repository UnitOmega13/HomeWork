package src;

public class Robot {
	private int x;
	private int y;
	Direction direction;

	public Robot(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void turnToLeft() {
		if (direction == Direction.UP) {
			direction = Direction.LEFT;
		} else if (direction == Direction.DOWN) {
			direction = Direction.RIGHT;
		} else if (direction == Direction.LEFT) {
			direction = Direction.DOWN;
		} else if (direction == Direction.RIGHT) {
			direction = Direction.UP;
		}
	}

	public void turnToRight() {
		if (direction == Direction.UP) {
			direction = Direction.RIGHT;
		} else if (direction == Direction.DOWN) {
			direction = Direction.LEFT;
		} else if (direction == Direction.LEFT) {
			direction = Direction.UP;
		} else if (direction == Direction.RIGHT) {
			direction = Direction.DOWN;
		}
	}

	public void stepForward() {
		if (direction == Direction.UP) {
			y++;
		}
		if (direction == Direction.DOWN) {
			y--;
		}
		if (direction == Direction.LEFT) {
			x--;
		}
		if (direction == Direction.RIGHT) {
			x++;
		}
	}
}
