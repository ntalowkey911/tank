package controller;

public class SinglePlayerMode implements GameMode {
    @Override
    public void start() {
        new TankControllerPE();  // Sử dụng màu mặc định hoặc có thể thêm tham số màu nếu cần
    }
}
