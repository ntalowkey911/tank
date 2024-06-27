package model;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Runnable {
	private int x, y, dx, dy;
	private static final int SPEED = 5; // Tốc độ của viên đạn

	public Bullet(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx * SPEED; // Nhân với tốc độ
		this.dy = dy * SPEED; // Nhân với tốc độ
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 5, 5);
	}

	public void run() {

		while (x >= 0 && x <= 600 && y >= 0 && y <= 600) {
			x += dx;
			y += dy;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}