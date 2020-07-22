import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private int moves =0;

    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;

    private Timer timer;
    private int delay = 100;

    private int[] enemyXPos = {25, 50, 75, 100, 125, 150, 175, 200,
            225, 250, 275, 300, 325, 350, 375, 400,
            425, 450, 475, 500, 525, 550, 575, 600,
            625, 650, 675, 700, 725, 750, 775, 800,
            825, 850};
    private int[] enemyYPos = {75, 100, 125, 150, 175, 200,
            225, 250, 275, 300, 325, 350, 375, 400,
            425, 450, 475, 500, 525, 550, 575, 600,
            625};

    private ImageIcon enemyImage;
    private Random random = new Random();

    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    private int score = 0;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private ImageIcon snakeImage;
    private ImageIcon titleImage;

    private int lengthOfSnake =3;

    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this );
        timer.start();
    }
    public void paint(Graphics g){
        if(moves == 0){
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }
        //draw title border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        //draw title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25,11);

        //draw border for gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        //draw background for gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        // draw score
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: " + score, 780, 30);

        //draw length of snake
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: " + lengthOfSnake, 780, 50);

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        for(int a=0; a<lengthOfSnake; a++){
            if(a == 0 && right){
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && left){
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && up){
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a == 0 && down){
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a != 0){
                snakeImage = new ImageIcon("snakeImage.png");
                snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
        }

        enemyImage = new ImageIcon("enemy.png");

        if(enemyXPos[xPos] == snakeXLength[0] && enemyYPos[yPos] == snakeYLength[0]){
            score++;
            lengthOfSnake ++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
        }
        enemyImage.paintIcon(this, g, enemyXPos[xPos], enemyYPos[yPos]);
         for(int b=1; b < lengthOfSnake; b++){
             if(snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0]){
                 // no operation can be allowed
                 right = false;
                 up = false;
                 down = false;
                 left = false;

                 // display game over msg

                 g.setColor(Color.white);
                 g.setFont(new Font("arial", Font.BOLD, 50));
                 g.drawString("GAME OVER :(  ", 300 , 300);

                 //display to restart

                 g.setFont(new Font("arial", Font.BOLD, 20));
                 g.drawString("SPACE to restart ", 380 , 340);

             }
         }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r = lengthOfSnake-1; r >= 0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake; r >= 0; r--){
                if(r == 0){
                    snakeXLength[r] = snakeXLength[r] + 25;
                }else{
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r]  > 850){
                    snakeXLength[r] = 25;
                }
            }
            repaint();
        }
        if(left){
            for(int r = lengthOfSnake-1; r >= 0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake; r >= 0; r--){
                if(r == 0){
                    snakeXLength[r] = snakeXLength[r] - 25;
                }else{
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if(snakeXLength[r]  < 25){
                    snakeXLength[r] = 850;
                }
            }
            repaint();
        }
        if(up){
            for(int r = lengthOfSnake-1; r >= 0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }
            for(int r = lengthOfSnake; r >= 0; r--){
                if(r == 0){
                    snakeYLength[r] = snakeYLength[r] - 25;
                }else{
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r]  < 75){
                    snakeYLength[r] = 625;
                }
            }
            repaint();

        }
        if(down){
            for(int r = lengthOfSnake-1; r >= 0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }
            for(int r = lengthOfSnake; r >= 0; r--){
                if(r == 0){
                    snakeYLength[r] = snakeYLength[r] + 25;
                }else{
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if(snakeYLength[r]  > 625){
                    snakeYLength[r] = 75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() ==KeyEvent.VK_SPACE){
            moves =0;
            score =0;
            lengthOfSnake =3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves ++;
            right = true;
            if(!left){
                right = true;
            }else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves ++;
            left = true;
            if(!right){
                left = true;
            }else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves ++;
            up = true;
            if(!down){
                up = true;
            }else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves ++;
            down = true;
            if(!up){
                down = true;
            }else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
