package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.TankControllerPE;

public class TankSkin extends JFrame {
	private static final int TANK_SIZE = 30;
	private static final int GUN_WIDTH = 6;
	private static final int GUN_HEIGHT = 6;
	private JRadioButton red, blue, green;
	private JPanel selectPanel, skinPanel, buttonPanel;
	private JButton play, cancel;
	private TankControllerPE tankPE;

	public TankSkin() {
		red = new JRadioButton("Red");
		blue = new JRadioButton("Blue");
		green = new JRadioButton("Green");

		play = new JButton("Play");
		cancel = new JButton("Cancel");

		selectPanel = new JPanel();
		skinPanel = new JPanel();
		buttonPanel = new JPanel();

		setLayout(new GridLayout(3, 1));
		final JLabel msg = new JLabel("", JLabel.CENTER);
		skinPanel.add(msg);

		selectPanel.setBorder(BorderFactory.createTitledBorder("Select your skin"));
		skinPanel.setBorder(BorderFactory.createTitledBorder("Skin"));

		selectPanel.add(red);
		selectPanel.add(blue);
		selectPanel.add(green);

		buttonPanel.add(play);
		buttonPanel.add(cancel);
		buttonPanel.setLayout(new FlowLayout());
		// Group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(red);
		group.add(blue);
		group.add(green);

		red.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				draw(Color.RED);
			}
		});

		blue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				draw(Color.BLUE);
			}
		});

		green.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				draw(Color.GREEN);
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TankSkin.this.dispose();
			}
		});
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (red.isSelected()) {
					new TankControllerPE(Color.RED);
				} else if (blue.isSelected()) {
					new TankControllerPE(Color.BLUE);
				} else if (green.isSelected()) {
					new TankControllerPE(Color.GREEN);
				}
				TankSkin.this.dispose();

			}
		});
		add(selectPanel);
		add(skinPanel);
		add(buttonPanel);

		setTitle("Select your skin");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void draw(Color tankColor) {
		Graphics g = skinPanel.getGraphics();
		Dimension skinSize = skinPanel.getSize();
		int x = (int) ((skinSize.getWidth() - TANK_SIZE) / 2); // Tính toán tọa độ x cho việc vẽ skin ở giữa
		int y = (int) ((skinSize.getHeight() - TANK_SIZE + 12) / 2);
		g.setColor(tankColor);
		g.fillRect(x, y, TANK_SIZE, TANK_SIZE);
		g.fillRect(x + (TANK_SIZE - GUN_WIDTH) / 2, y - GUN_HEIGHT, GUN_WIDTH, GUN_HEIGHT);
	}

	public static void main(String[] args) {
		new TankSkin();

	}

}
