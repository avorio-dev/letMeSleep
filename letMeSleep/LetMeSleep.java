package letMeSleep;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Date;

public class LetMeSleep implements Runnable {

	public static boolean running = false;
	public static final long MAX_DURATION = 3500;

	private int leftCorner;
	private int rightCorner;
	private int bottomCorner;
	private int topCorner;

	private int next_x;
	private int next_y;

	public LetMeSleep() {
		leftCorner = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		rightCorner = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

		topCorner = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		bottomCorner = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	}

	public LetMeSleep(int leftCorner, int rightCorner, int bottomCorner, int topCorner) {
		this.leftCorner = leftCorner;
		this.rightCorner = rightCorner;

		this.topCorner = topCorner;
		this.bottomCorner = bottomCorner;
	}

	private void startSleep() {
		running = true;
	}

	public void stopSleep() {
		running = false;
	}

	public void sleeping() {
		
		long startTime = new Date().getTime();
		long currTime = 0;
		long diffTime = 0;

		while (running) {
			try {
				dream();

				currTime = new Date().getTime();
				diffTime = currTime - startTime;

//				if (diffTime > MAX_DURATION)
//					running = false;

			} catch (AWTException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void dream() throws AWTException, InterruptedException {

		final int speed = 1;

		Robot robot = new Robot();

		next_x = leftCorner;
		next_y = topCorner;

//		robot.mouseMove(next_x, next_y);

		while (next_x < rightCorner) {
			next_x++;
			robot.mouseMove(next_x, next_y);
			if(!running) return;
			Thread.sleep(speed);
		}

		while (next_y < bottomCorner) {
			next_y++;
			robot.mouseMove(next_x, next_y);
			if(!running) return;
			Thread.sleep(speed);
		}

		while (next_x > leftCorner) {
			next_x--;
			robot.mouseMove(next_x, next_y);
			if(!running) return;
			Thread.sleep(speed);
		}

		while (next_y > topCorner) {
			next_y--;
			robot.mouseMove(next_x, next_y);
			if(!running) return;
			Thread.sleep(speed);
		}

	}

	@Override
	public void run() {
		startSleep();
		sleeping();
	}

}
