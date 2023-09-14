package PacmanLabel;

import javax.swing.*;
import java.awt.*;

public class Pacman extends JLabel {
    private boolean isMouthOpen = true;
    private boolean up;
    private boolean down;
    private boolean right = true;
    private boolean left;
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(10, 10);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 8;
        int height = 8;
        if (isMouthOpen) {
            if (right) {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, width, height, 45, 270);
                g.setColor(getBackground());
            }
            if (left) {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, width, height, 225, 270);
                g.setColor(getBackground());
            }
            if (up) {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, width, height, 135, 270);
                g.setColor(getBackground());
            }
            if (down) {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, width, height, 315, 270);
                g.setColor(getBackground());
            }
        }else {
            g.setColor(Color.YELLOW);
            g.fillOval(0, 0, width, height);
        }
    }
        public void changeMouth () {
            isMouthOpen = !isMouthOpen;
        }

        public void setUp () {
            this.up = true;
            this.down = false;
            this.right = false;
            this.left = false;

        }

        public void setDown () {
            this.down = true;
            this.left = false;
            this.right = false;
            this.up = false;
        }

        public void setRight () {
            this.right = true;
            this.down = false;
            this.left = false;
            this.up = false;

        }

        public void setLeft () {
            this.left = true;
            this.down = false;
            this.right = false;
            this.up = false;

        }
    }

