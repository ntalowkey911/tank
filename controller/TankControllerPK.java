package controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Bullet;
import model.Tank;
import view.TankFrame;
import view.TankPanel;

public class TankControllerPK extends JFrame{
    private Tank tank1, tank2;
    private boolean isRunning;
    private JMenuBar menuBar;
    private TankPanel tankPanel = new TankPanel();

    public TankControllerPK() {
        tank1 = new Tank(100, 100, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        tank2 = new Tank(400, 400, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_ENTER);
        isRunning = true;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tank1.keyPressed(e);
                tank2.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                tank1.keyReleased(e);
                tank2.keyReleased(e);
            }
        });

        new Thread(() -> {
            while (isRunning) {
                tank1.move();
                tank2.move();
                checkCollisions();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        setupMenu();

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setupMenu() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem maximizeItem = new JMenuItem("Maximize");
        JMenuItem minimizeItem = new JMenuItem("Minimize");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(maximizeItem);
        fileMenu.add(minimizeItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        maximizeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TankControllerPK.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        minimizeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TankControllerPK.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(TankControllerPK.this, "Bạn có muốn thoát?", "Quit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    TankFrame newTankFrame = new TankFrame();
                    newTankFrame.setVisible(true);
                }
            }
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public void paint(Graphics g) {
        super.paint(g);
        tank1.draw(g, Color.BLACK, "Robin");
        tank2.draw(g, Color.GREEN, "Meme");
        tank1.drawBullets(g);
        tank2.drawBullets(g);
        g.setColor(Color.BLACK);
    }

    public void checkCollisions() {
        for (Bullet bullet : tank1.getBullets()) {
            if (tank2.isHit(bullet.getX(), bullet.getY())) {
                gameOver();
            }
        }

        for (Bullet bullet : tank2.getBullets()) {
            if (tank1.isHit(bullet.getX(), bullet.getY())) {
                gameOver();
            }
        }
    }

    public void gameOver() {
        isRunning = false;
        JOptionPane.showMessageDialog(this, "Game over!");
        this.dispose();
        tank1.reset(100, 100); // Reset vị trí của tank
        tank2.reset(400, 400);
        tank1.getBullets().clear(); // Xóa danh sách đạn
        tank2.getBullets().clear();
        TankFrame newTankFrame = new TankFrame();
        newTankFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TankControllerPK();
    }
}
