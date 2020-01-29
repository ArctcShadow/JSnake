import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Snake");
        setBounds(400, 400, 300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new Field());
        setVisible(true);


    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
    }
}