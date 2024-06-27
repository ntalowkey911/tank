package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Tank {
	private int x, y, dx, dy;
	private int upKey, downKey, leftKey, rightKey, fireKey;
	private boolean up, down, left, right, fire;
	private Color tankColor;
	private List<Bullet> bullets;

	public Tank(int x, int y, int upKey, int downKey, int leftKey, int rightKey, int fireKey) {
		this.x = x;
		this.y = y;
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.fireKey = fireKey;
		this.bullets = new ArrayList<>();
	}

//	public void draw(Graphics g, Color tankColor, String name) {
//		g.setColor(tankColor); // Sử dụng màu của tank
//		g.fillRect(x, y, 30, 30);
//
//		g.drawString(name, x, y - 5); // Vẽ tên ở trên tank
//
//		// Vẽ nòng súng
//		if (dx == 0 && dy == -1) { // Lên
//			g.fillRect(x + 12, y - 5, 6, 10);
//		} else if (dx == 0 && dy == 1) { // Xuống
//			g.fillRect(x + 12, y + 25, 6, 10);
//		} else if (dx == -1 && dy == 0) { // Trái
//			g.fillRect(x - 5, y + 12, 10, 6);
//		} else if (dx == 1 && dy == 0) { // Phải
//			g.fillRect(x + 25, y + 12, 10, 6);
//		}
//	}
//
//	public void drawBullets(Graphics g) {
//		for (Bullet bullet : bullets) {
//			bullet.draw(g);
//		}
//	}
	public void draw(Graphics g, Color tankColor, String name) {
		g.setColor(tankColor);
		g.fillRect(x, y, 30, 30);
		g.drawString(name, x, y - 5);

		// Vẽ nòng súng
		if (dx == 0 && dy == -1) { // Lên
			g.fillRect(x + 12, y - 5, 6, 10);
		} else if (dx == 0 && dy == 1) { // Xuống
			g.fillRect(x + 12, y + 25, 6, 10);
		} else if (dx == -1 && dy == 0) { // Trái
			g.fillRect(x - 5, y + 12, 10, 6);
		} else if (dx == 1 && dy == 0) { // Phải
			g.fillRect(x + 25, y + 12, 10, 6);
		}
	}

	public void drawBullets(Graphics g) {
		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}
	}

	public java.util.List<Bullet> getBullets() {
		return bullets;
	}

	public boolean isHit(int bulletX, int bulletY) {
		return Math.abs(bulletX - (x + 15)) < 10 && Math.abs(bulletY - (y + 15)) < 10;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == upKey) {
			dy = -1;
			up = true;
		}
		if (key == downKey) {
			dy = 1;
			down = true;
		}
		if (key == leftKey) {
			dx = -1;
			left = true;
		}
		if (key == rightKey) {
			dx = 1;
			right = true;
		}
		if (key == fireKey && !fire && (dx != 0 || dy != 0)) {
			fire = true;
			// Tạo viên đạn
			int bulletX = x + 15;
			int bulletY = y + 15;
			// Điều chỉnh vị trí ban đầu của viên đạn để nó bắn từ nòng súng
			if (dx == 0 && dy == -1) { // Lên
				bulletY -= 20;
			} else if (dx == 0 && dy == 1) { // Xuống
				bulletY += 20;
			} else if (dx == -1 && dy == 0) { // Trái
				bulletX -= 20;
			} else if (dx == 1 && dy == 0) { // Phải
				bulletX += 20;
			}
			// Bắn viên đạn
			Bullet bullet = new Bullet(bulletX, bulletY, dx, dy);
			bullets.add(bullet);
			new Thread(bullet).start();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == upKey) {
			up = false;
		}
		if (key == downKey) {
			down = false;
		}
		if (key == leftKey) {
			left = false;
		}
		if (key == rightKey) {
			right = false;
		}
		if (key == fireKey) {
			fire = false;
		}

		if (!up && !down) {
			dy = 0;
		}
		if (!left && !right) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {
		x += dx;
		y += dy;
	}

	public void reset(int initialX, int initialY) {
		this.x = initialX;
		this.y = initialY;
		this.dx = 0;
		this.dy = 0;
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
		this.fire = false;
		this.bullets.clear(); // Xóa danh sách đạn
	}

}