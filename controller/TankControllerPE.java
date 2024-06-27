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
public class TankControllerPE extends JFrame{
    private Tank tank1, tank2;
    private boolean isRunning;
    private JMenuBar menuBar;
    private TankPanel tankPanel = new TankPanel();
    private Color color;

    public TankControllerPE() {
        this(Color.BLACK); // Mặc định sử dụng màu đen
    }

    public TankControllerPE(Color color) {
        this.color = color;

        tank1 = new Tank(100, 100, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        isRunning = true;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tank1.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                tank1.keyReleased(e);
            }
        });

        new Thread(() -> {
            while (isRunning) {
                tank1.move();
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
                TankControllerPE.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        minimizeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TankControllerPE.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(TankControllerPE.this, "Bạn có muốn thoát?", "Quit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    TankFrame newTankFrame = new TankFrame();
                    newTankFrame.setVisible(true);
                    TankControllerPE.this.dispose(); // Giải phóng tài nguyên của TankControllerPE
                }
            }
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public void paint(Graphics g) {
        super.paint(g);
        tank1.draw(g, color, "Robin");
        tank1.drawBullets(g);
        g.setColor(color);
    }
}
