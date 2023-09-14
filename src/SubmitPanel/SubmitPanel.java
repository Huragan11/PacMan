package SubmitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class SubmitPanel extends JFrame implements ActionListener {
    private static final String HIGHSCORES_FILE_PATH = "Highscores.ser";

    private JButton submitButton = new JButton("Submit this run!");
    private JTextField name = new JTextField();
    private String returnedName;
    private String score;

    public SubmitPanel(String score) {
        this.score = score;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        name.setPreferredSize(new Dimension(250, 40));
        submitButton.addActionListener(this);
        add(submitButton);
        add(name);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public String getReturnedName() {
        return returnedName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            returnedName = name.getText();
            Highscore highscore = new Highscore(getReturnedName(), score);

            //zczytywanie
            List<Highscore> highscores = null;
            try (FileInputStream fis = new FileInputStream(HIGHSCORES_FILE_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                highscores = (List<Highscore>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            if (highscores == null) {
                highscores = new ArrayList<>();
            }
            highscores.add(highscore);

            //zapisywanie
            try (FileOutputStream fos = new FileOutputStream(HIGHSCORES_FILE_PATH);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(highscores);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dispose();
        }
    }
}
