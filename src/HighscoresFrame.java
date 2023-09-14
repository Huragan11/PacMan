import SubmitPanel.Highscore;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class HighscoresFrame extends JFrame {
    private static final String HIGHSCORES_FILE_PATH = "Highscores.ser";

    public HighscoresFrame() {
        DefaultListModel<String> prelist = new DefaultListModel<>();
        //zczytywanie
        try {
            FileInputStream read = new FileInputStream(HIGHSCORES_FILE_PATH);
            ObjectInputStream inputStream = new ObjectInputStream(read);

            List<Highscore> highscores = (List<Highscore>) inputStream.readObject();
            for (Highscore highscore : highscores) {
                prelist.addElement("Name: " + highscore.getName() + " || Score: " + highscore.getScore());
            }
            inputStream.close();
            read.close();
        } catch (IOException | ClassNotFoundException e ){
            e.printStackTrace();
        }

        JList<String> scores = new JList<>(prelist);
        scores.setForeground(Color.blue);
        scores.setFont(new Font("Consolas",Font.BOLD, 20));

        add(new JScrollPane(scores));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(550,400);
        setSize(600, 150);
        setVisible(true);
        setResizable(false);
    }
}
