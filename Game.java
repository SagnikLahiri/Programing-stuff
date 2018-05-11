import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
	private final int BOARD_WIDTH = 500;
    private final int BOARD_HEIGHT = 500;
    private final int DOT_SIZE = 10;
    private final int NO_OF_DOTS = 900;
    private final int RAND_POS = 29;
    private int SPEED = 140;
    private int score =0;
    
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
   
    private int snakes_dots;
    private Timer timer;
    private final int x[] = new int[NO_OF_DOTS];
    private final int y[] = new int[NO_OF_DOTS];
    private boolean inGame = true;
    
    private int xpos_food;
    private int ypos_food;
   
    Shape body;
    Shape head;
    Shape food;
    
    public Game() {

        addKeyListener(new Keys_Adapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
      
        initGame();
    }
    private void initGame() {

        snakes_dots = 3;

        for (int z = 0; z < snakes_dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        find_food();

        timer = new Timer(SPEED, this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
 private void doDrawing(Graphics g) {
        
        if (inGame) {
        	 Graphics2D gt=(Graphics2D) g;
          		food=new Ellipse2D.Float(xpos_food,ypos_food, 10.0f, 10.0f);
        		gt.setPaint(Color.MAGENTA);
        		gt.fill(food);
        		gt.draw(food);
        		
            for (int z = 0; z < snakes_dots; z++) {
                if (z == 0) {
                   	head=new Ellipse2D.Float(x[z],y[z], 10.0f, 10.0f);
                	gt.setPaint(Color.RED);
                	gt.fill(head);
                	gt.draw(head);
                	
                } else {
                   	body=new Ellipse2D.Float(x[z],y[z], 10.0f, 10.0f);
                	gt.setPaint(Color.green);
                	gt.fill(body);
                	gt.draw(body);
                	
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }
 private void gameOver(Graphics g) {
     
     String msg = "Game Over";
     Font small = new Font("Helvetica", Font.BOLD, 20);
     FontMetrics metrics = getFontMetrics(small);
     
     g.setColor(Color.white);
     g.setFont(small);
     g.drawString(msg, (BOARD_WIDTH - metrics.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);
     g.drawString("SCORE:- "+score, (BOARD_WIDTH/2)-50,(BOARD_HEIGHT/2)+80);
     
 }
 private void check_Food() {

     if ((x[0] == xpos_food) && (y[0] == ypos_food)) {

         snakes_dots++;
        // score+=(snakes_dots-3);
         score++;
         SPEED+=100;
         find_food();
     }
 }
 private void movement() {

     for (int z = snakes_dots; z > 0; z--) {
         x[z] = x[(z - 1)];
         y[z] = y[(z - 1)];
     }

     if (left) {
         x[0] -= DOT_SIZE;
     }

     if (right) {
         x[0] += DOT_SIZE;
     }

     if (up) {
         y[0] -= DOT_SIZE;
     }

     if (down) {
         y[0] += DOT_SIZE;
     }
 }
 private void Collision_Settings() {

     for (int z = snakes_dots; z > 0; z--) {

         if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
             inGame = false;
         }
     }

     if (y[0] >= BOARD_HEIGHT) {
         inGame = false;
     }

     if (y[0] < 0) {
         inGame = false;
     }

     if (x[0] >= BOARD_WIDTH) {
         inGame = false;
     }

     if (x[0] < 0) {
         inGame = false;
     }
     
     if(!inGame) {
         timer.stop();
     }
 }
 private void find_food() {

     int r = (int) (Math.random() * RAND_POS);
     xpos_food = ((r * DOT_SIZE));

     r = (int) (Math.random() * RAND_POS);
     ypos_food = ((r * DOT_SIZE));
 }
 public void actionPerformed(ActionEvent e) {

     if (inGame) {

         check_Food();
         Collision_Settings();
         movement();
     }

     repaint();
 }
 private class Keys_Adapter extends KeyAdapter {

     @Override
     public void keyPressed(KeyEvent e) {

         int key = e.getKeyCode();

         if ((key == KeyEvent.VK_LEFT) && (!right)) {
             left = true;
             up = false;
             down = false;
         }

         if ((key == KeyEvent.VK_RIGHT) && (!left)) {
             right = true;
             up = false;
             down = false;
         }

         if ((key == KeyEvent.VK_UP) && (!down)) {
             up = true;
             right = false;
             left = false;
         }

         if ((key == KeyEvent.VK_DOWN) && (!up)) {
             down = true;
             right = false;
             left = false;
         }
     }
 }
}
