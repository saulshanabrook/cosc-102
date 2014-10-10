/*
 Collision.java
 
 Source code for the Elastic Collision simulation program.
 COSC 102, Colgate University
 (c) 2014 Vijay Ramachandran
 Sept 2014 Modified by Elodie Fourquet 
 
 DO NOT MODIFY THIS FILE.
 
 This is the main file to run for the simulation,
 however, you will be editing another file (Ball.java).
 */


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class CollisionApp extends JPanel implements ActionListener
{
  private static Random random = new Random();
  
  private JTextField radius = new JTextField("10", 3);
  private JTextField speed = new JTextField("3", 3);
  private JTextField angle = new JTextField("30", 3);
  private JButton newball = new JButton("New Ball");
  private BallPanel bp = new BallPanel();
  
  public CollisionApp()
  {
    setLayout(new BorderLayout());
    
    // Toolbar
    JPanel toolbar = new JPanel(new FlowLayout());
    
    // Radius
    toolbar.add(new JLabel("Radius:"));
    toolbar.add(radius);
    
    // Speed
    toolbar.add(new JLabel("Speed:"));
    toolbar.add(speed);
    
    // Angle
    toolbar.add(new JLabel("Angle:"));
    toolbar.add(angle);
    
    // New ball button
    newball.addActionListener(this);
    toolbar.add(newball);
    
    // add toolbar to the layout
    add(toolbar, BorderLayout.PAGE_START);
    
    // graphics pane
    add(bp, BorderLayout.CENTER);    
    
  }
  
  public void actionPerformed(ActionEvent e)
  {
    try {
      
      double radius = Double.parseDouble(this.radius.getText());
      double speed = Double.parseDouble(this.speed.getText());
      double angle = Double.parseDouble(this.angle.getText());
      
      bp.addBall(radius, speed, angle);
      
    } catch (NumberFormatException nfe) {
      
      JOptionPane.showMessageDialog(this, "Invalid input." +  
                                    " Parameters must be double values.",
                                    "New Ball Error", JOptionPane.ERROR_MESSAGE);
      
    }
    
  }
  
  // Constant colors in Java are not nice
  // Harmonized palette from
  //http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
  public static Color generateRandomColor(Color mix) {

    int red = random.nextInt(256);
    int green = random.nextInt(256);
    int blue = random.nextInt(256);
    
    // mix the color
    if (mix != null) {
      red = (red + mix.getRed()) / 2;
      green = (green + mix.getGreen()) / 2;
      blue = (blue + mix.getBlue()) / 2;
    }
    
    Color color = new Color(red, green, blue);
    return color;
  }
  
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable() {
      
      public void run() {
        JFrame frame = new JFrame("Elastic Collision");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(new CollisionApp());
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.toFront();
      }
    });
  } // end main
}


// class BallPanel

class BallPanel extends JPanel implements ActionListener
{
  private int delay = 10;
  private Timer timer;
  
  private ArrayList<Ball> balls = new ArrayList<Ball>();
  
  public void addBall(double radius, double speed, double angle)
  {
    balls.add(new Ball(radius, speed, angle, CollisionApp.generateRandomColor(Color.WHITE))); 
  }
  
  public BallPanel()
  {
    timer = new Timer(delay, this);
    timer.start();  // start the timer
    setBackground(Color.WHITE);
  }
  
  public void actionPerformed(ActionEvent e)
  // will run when the timer fires
  {
    int width = getWidth() / 2;
    int height = getHeight() / 2;
    
    // update all positions
    for (Ball ball : balls)
      ball.updatePosition(width, height);
    
    // perform collisions
    for (int i = 0; i < balls.size(); i++)
      for (int j = i+1; j < balls.size(); j++)
         if (balls.get(i).isCollidingWith(balls.get(j)))
            balls.get(i).collide(balls.get(j));
    
    repaint();
  }
  
  public void paintComponent( Graphics g )
  {
    super.paintComponent( g );
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    int width = getWidth() / 2;
    int height = getHeight() / 2;
    
    g2d.translate(width, height);
    g2d.scale(1, -1);
    
    for (int i = 0; i < balls.size(); i++)
    {
      Ball ball = balls.get(i);
      
      double x = ball.getX();
      double y = ball.getY();
      double r = ball.getRadius();
      
      g2d.setColor(balls.get(i).getColor());
      g2d.fillOval((int) (x - r), (int) (y - r), (int) r*2, (int) r*2);
    }
  }
  
}
