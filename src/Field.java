import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Field extends JPanel implements ActionListener {
    private final int size = 300;
    private final int particleSize = 15;
    private final int allParticles = 400;
    private Image particle;
    private Image apple;
    private int appleXcoord, appleYcoord;
    private int[] x = new int[allParticles];
    private int[] y = new int[allParticles];
    private int pixel;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean play = true;


    public Field() {
        setBackground(Color.DARK_GRAY);
        Images();
        Init();
        addKeyListener(new KeyReader());
        setFocusable(true);


    }

    public void Images() {
        ImageIcon img = new ImageIcon("apple.png");
        apple = img.getImage();
        ImageIcon img2 = new ImageIcon("dot.png");
        particle = img2.getImage();
    }

    public void Init() {
        pixel = 3;
        for (int i = 0; i < pixel; i++) {
            x[i] = 45 - i * particleSize;
            y[i] = 45;

        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }


    public void createApple() {
        appleXcoord = new Random().nextInt(20) * particleSize;
        appleYcoord = new Random().nextInt(20) * particleSize;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (play) {
            g.drawImage(apple, appleXcoord, appleYcoord, this);
            for (int i = 0; i < pixel; i++) {
                g.drawImage(particle, x[i], y[i], this);
            }
        }
        else{
            String str = "Game over";
                    g.setColor(Color.WHITE);
                    g.drawString(str,125,size/2);

        }
    }

    public void move() {
        for (int i = pixel; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];


        }
        if (left) {
            x[0] -= particleSize;
        }
        if (right) {
            x[0] += particleSize;
        }
        if (up) {
            y[0] += particleSize;
        }
        if (down) {
            y[0] -= particleSize;
        }
    }

    public void checkApple() {
        if (x[0] == appleXcoord && y[0] == appleYcoord) {
            pixel++;
            createApple();
        }
    }

    public void checkBounds() {
        for (int i = pixel; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                play = false;
            }
        }
        if (x[0] > size) {
            play = false;
        }
        if (x[0] < 0) {
            play = false;
        }
        if (y[0] > size) {
            play = false;
        }
        if (y[0] < 0) {
            play = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            checkApple();
            checkBounds();
            move();
        }
        repaint();
    }

    class KeyReader extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;

            }
            if (key == KeyEvent.VK_DOWN && !up) {
                up = true;
                right = false;
                left = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}








