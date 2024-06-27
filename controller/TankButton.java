package controller;

import view.TankPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import controller.GameMode;
import controller.SinglePlayerMode;
import controller.MultiplayerMode;
import javax.swing.*;

public class TankButton extends JFrame {
    private TankPanel tankPanel;
    private GameMode gameMode;

    public void showQuit() {
        int choice = JOptionPane.showConfirmDialog(tankPanel, "Bạn có muốn thoát?", "Quit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void showTutorial() {
        String message = "Hướng dẫn:\n" + "Nhấn W để di chuyển lên trên\n" + "Nhấn A để di chuyển sang trái\n"
                + "Nhấn S để di chuyển xuống dưới\n" + "Nhấn D để di chuyển sang phải\n" + "Nhấn Space để bắn";

        JOptionPane.showMessageDialog(this, message, "Hướng dẫn", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showPlayOption() {
        String[] options = { "Chơi đơn", "PK (Người chơi vs Người chơi)" };

        int choice = JOptionPane.showOptionDialog(this, "Chọn chế độ chơi:", "Chọn chế độ", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0: // Chơi đơn
                gameMode = new SinglePlayerMode();
                break;
            case 1: // PK (Người chơi vs Người chơi)
                gameMode = new MultiplayerMode();
                break;
            default:
                break;
        }

        if (gameMode != null) {
            gameMode.start();
        }
    }
}
