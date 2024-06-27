package view;

import javax.swing.JFrame;

public class TankFrame extends JFrame {

	public TankFrame() {
		setTitle("Tank Game");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(new TankPanel());
		setVisible(true);
	}
	

	public static void main(String[] args) {
		new TankFrame();
	}
}
