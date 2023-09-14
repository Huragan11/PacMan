package InfoPanel;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JLabel {

    int score = 0;
    int health = 1;

    String score_string = Integer.toString(score);
    String health_string = Integer.toString(health);
    String result = "Score: " + score_string + " Health: " + health_string ;

    Thread updater = new Thread(() -> {
        while(!Thread.currentThread().isInterrupted()) {
            score_string = Integer.toString(score);
            health_string = Integer.toString(health);
            result = "Score: " + score_string + " Health: " + health_string;
            setText(result);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });

    public int getHealth() {
        return health;
    }

    public void addHealth(int i){
        health += i;
    }
    public String getResult(){
        return result;
    }

    public void loseHealth() {
        this.health--;
    }

    public InfoPanel() {
        setSize(new Dimension(200,100));
        setText(result);
        setOpaque(true);
        setHorizontalAlignment(JTextField.LEFT);
        setBackground(Color.GRAY);
        updater.start();
    }

    public void addScore(int i){
        score += i;
    }
    public int getScore() {
        return score;
    }

}
