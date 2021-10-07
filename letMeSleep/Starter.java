package letMeSleep;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
public class Starter implements KeyListener {

	public static final int CENTER_SCREEN_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
	public static final int CENTER_SCREEN_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 150;

	public static final int KEY_ESCAPE = 27;

	private JFrame frame;
	private JButton buttonStart;

	private LetMeSleep letMeSleep;

	private ExecutorService threadPool = Executors.newFixedThreadPool(15);

	public static void main(String[] args) {
		Starter starter = new Starter();

		starter.initInterface();
		int left = CENTER_SCREEN_X - (PANEL_WIDTH / 2);
		int right = CENTER_SCREEN_X + (PANEL_WIDTH / 2);

		int top = CENTER_SCREEN_Y - (PANEL_HEIGHT / 2);
		int bottom = CENTER_SCREEN_Y + (PANEL_HEIGHT / 2);

		try {
			starter.letMeSleep = new LetMeSleep(left, right, bottom, top);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initInterface() {

		frame = new JFrame("Let me sleep!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(CENTER_SCREEN_X - PANEL_WIDTH / 2, CENTER_SCREEN_Y - PANEL_HEIGHT / 2, PANEL_WIDTH,
				PANEL_HEIGHT);
		frame.setLayout(null);
		frame.setVisible(true);

		buttonStart = new JButton();
		buttonStart.setText("START Moving Cursor");
		buttonStart.setBounds(PANEL_WIDTH / 4, PANEL_HEIGHT / 4, PANEL_WIDTH / 2, 40);
		buttonStart.addKeyListener(this);
		frame.add(buttonStart);

//		buttonStart.addKeyListener(this);
		buttonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(letMeSleep);
				thread.start();

			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("Typed");
//		System.out.println(e.getKeyCode());
//		System.out.println(e.getKeyChar());
//		System.out.println();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed");
		System.out.println(e.getKeyCode());
		System.out.println(e.getKeyChar());
		System.out.println();
		if (letMeSleep != null) {
			letMeSleep.stopSleep();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("Released");
//		System.out.println(e.getKeyCode());
//		System.out.println(e.getKeyChar());
//		System.out.println();

	}

}
