package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TankButton;

public class TankPanel extends JPanel {
	private JPanel title, button;
	private JMenuBar menuBar;
	private JButton playButton, tutorialButton, quitButton;
	private TankButton tb;

	public TankPanel() {
		this.tb = new TankButton();

		title = new TitlePanel();
		button = new ButtonPanel();

		setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(button, BorderLayout.CENTER);

	}

	class TitlePanel extends JPanel {
		public TitlePanel() {
			setPreferredSize(new Dimension(400, 100));
			JLabel t = new JLabel("TANK GAME");
			t.setFont(new Font(null, Font.BOLD, 65));
			t.setForeground(Color.BLUE);
			t.setBackground(Color.GREEN);
			setBorder(new EmptyBorder(10, 10, 10, 10));
			add(t);
		}
	}

	class ButtonPanel extends JPanel {

		public ButtonPanel() {
			setLayout(new GridLayout(3, 1, 0, 20));

			JButton playButton = new JButton("Play");
			JButton tutorialButton = new JButton("Tutorial");
			JButton quitButton = new JButton("Quit");

			Font buttonFont = new Font("Arial", Font.BOLD, 16);
			playButton.setFont(buttonFont);
			tutorialButton.setFont(buttonFont);
			quitButton.setFont(buttonFont);

			playButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tb.showPlayOption();
				}

			});
			quitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tb.showQuit();
				}

			});
			tutorialButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					tb.showTutorial();
				}
			});

			add(playButton);
			add(tutorialButton);
			add(quitButton);

			Dimension buttonSize = new Dimension(150, 50);
			playButton.setPreferredSize(buttonSize);
			tutorialButton.setPreferredSize(buttonSize);
			quitButton.setPreferredSize(buttonSize);
			setBorder(new EmptyBorder(30, 80, 50, 80));
		}
		

	}

}
