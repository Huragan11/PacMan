package GhostLabel;

import javax.swing.*;
import java.awt.*;

public class Ghost extends JLabel {
    boolean isEdible;

    ImageIcon ghostIcon = new ImageIcon("Graphics/ghost.png");
    ImageIcon ghostIconEdible = new ImageIcon("Graphics/ghostEat.png");

    public Ghost() {

        this.isEdible = false;
        setIcon();
        setBackground(Color.BLACK);
        setOpaque(true);
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);

    }

    public void changeEdibleStatus() {
        isEdible = !isEdible;
        setIcon();
    }
    public boolean isEdible(){
        return isEdible;
    }

    private void setIcon() {
        if (isEdible) {
            setIcon(ghostIconEdible);
        } else {
            setIcon(ghostIcon);
        }
    }

}
