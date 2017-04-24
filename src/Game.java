import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, MouseListener, KeyListener {

  static final int HEIGHT = 600;
  static final int WIDTH = 800;
  int x = 0;
  int y = 0;

  Player player;

  int r = 50;
  int speedPlayer = 15;
  Enemy[] enemyPool;

  public Game(int enemyCount) {
    JFrame frame = new JFrame("DorSoBakGo");
    Timer timer = new Timer(10, this);

    player = new Player("jekk", 0, 0, 0, 0, speedPlayer);

    frame.add(this);
    frame.setSize(WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addKeyListener(this);

    enemyPool = new Enemy[enemyCount];
    int enemyPosX = HEIGHT/2;
    for (int i=0;i<enemyCount;i++) {
      enemyPool[i] = new Enemy("Enemy1",30,100,10,enemyPosX,HEIGHT/2);
      enemyPosX += (r+100);
    }
    timer.start();

//    WindowListener exitListener = new WindowAdapter() {
//
//      @Override
//      public void windowClosing(WindowEvent e) {
//        if (Player.) {
//          System.exit(0);
//        }
//      }
//    };
//    frame.addWindowListener(exitListener);

  }

//  private void moveBall(int code) {
//    if (code == 1) player.setPosX(player.getPosX()-speedPlayer);
//    else if (code == 2) player.setPosY(player.getPosY()+speedPlayer);
//    else if (code == 3) player.setPosX(player.getPosX()+speedPlayer);
//    else if (code == 4) player.setPosY(player.getPosY()-speedPlayer);
//  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(new Color(52, 152, 219));
    g2d.fillRect(0,0,100, HEIGHT);
    g2d.setColor(new Color(231, 76, 60));
    g2d.fillRect(WIDTH - 100,0,100, HEIGHT);
    g2d.setColor(new Color(0));
    g2d.fillOval(player.getPosX(), player.getPosY(), r, r);
    for (int i=0;i<enemyPool.length;i++) {
      g2d.fillRect(enemyPool[i].getPosX(), enemyPool[i].getPosY(), enemyPool[i].getWidth(), enemyPool[i].getHeight());
    }
  }

  public static void main(String[] args) {
    Game game = new Game(2);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    repaint();
    //moveEnemy();
    for (int i=0;i<enemyPool.length;i++) {
      enemyPool[i].move(0);
    }
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  Set<Integer> pressed = new TreeSet<>();

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    int code = keyEvent.getKeyCode();
    pressed.add(code);

    if (pressed.size() > 1 ) {
      Integer [] array = pressed.toArray(new Integer[] {});
      if (array[0] == keyEvent.VK_LEFT && array[1] == keyEvent.VK_DOWN) {
        player.move(1);
        player.move(2);
      } else if (array[0] == keyEvent.VK_LEFT && array[1] == keyEvent.VK_UP) {
        player.move(4);
        player.move(1);
      } else if (array[0] == keyEvent.VK_UP && array[1] == keyEvent.VK_RIGHT) {
        player.move(3);
        player.move(4);
      } else if (array[0] == keyEvent.VK_RIGHT && array[1] == keyEvent.VK_DOWN) {
        player.move(2);
        player.move(3);
      }
    } else {
      if (code == keyEvent.VK_LEFT) {
        player.move(1);
        System.out.println("player move " + Integer.toString(code));
        System.out.println("player loc " + player.getPosX());
      } else if (code == keyEvent.VK_DOWN) {
        player.move(2);
        System.out.println("player move " + Integer.toString(code));
        System.out.println("player loc " + player.getPosY());
      } else if (code == keyEvent.VK_RIGHT) {
        player.move(3);
        System.out.println("player move " + Integer.toString(code));
        System.out.println("player loc " + player.getPosY());
      } else if (code == keyEvent.VK_UP) {
        player.move(4);
        System.out.println("player move " + Integer.toString(code));
        System.out.println("player loc " + player.getPosX());
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    pressed.remove(keyEvent.getKeyCode());
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {

  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseReleased(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseEntered(MouseEvent mouseEvent) {

  }

  @Override
  public void mouseExited(MouseEvent mouseEvent) {

  }

}