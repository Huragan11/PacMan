import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JButton START = new JButton("START");

    JButton HIGHSCORES = new JButton("HIGHSCORES");

    JButton EXIT = new JButton("EXIT");
    JPanel panel = new JPanel(new GridLayout(3,1,0,15));

    ImageIcon icon = new ImageIcon("Graphics/menu.png");
    JLabel background = new JLabel();

    public Menu() {
        setSize(512, 512);

        START.setBounds(getWidth()/2-100,getHeight()/2-75,200,50);
        START.setBorderPainted(true);
        START.setContentAreaFilled(false);
        START.setFocusPainted(true);
        START.setOpaque(false);
        START.addActionListener(this);
        START.setFont(new Font("Comic Sans",Font.BOLD,20));
        START.setForeground(Color.green);
        START.setBorder(BorderFactory.createLineBorder(Color.green,4));

        HIGHSCORES.setBounds(getWidth()/2 - 100,getHeight()/2,200,50);
        HIGHSCORES.setBorderPainted(true);
        HIGHSCORES.setContentAreaFilled(false);
        HIGHSCORES.setFocusPainted(true);
        HIGHSCORES.setOpaque(false);
        HIGHSCORES.addActionListener(this);
        HIGHSCORES.setFont(new Font("Comic Sans",Font.BOLD,20));
        HIGHSCORES.setForeground(Color.RED);
        HIGHSCORES.setBorder(BorderFactory.createLineBorder(Color.RED,4));


        EXIT.setBounds(getWidth()/2-100,getHeight()/2+75,200,50);
        EXIT.setBorderPainted(true);
        EXIT.setContentAreaFilled(false);
        EXIT.setFocusPainted(true);
        EXIT.setOpaque(false);
        EXIT.addActionListener(this);
        EXIT.setFont(new Font("Comic Sans",Font.BOLD,20));
        EXIT.setForeground(Color.BLACK);
        EXIT.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));

        panel.add(START);
        panel.add(HIGHSCORES);
        panel.add(EXIT);

        //setLayout(new BorderLayout());

        background.setIcon(icon);

        //add(Box.createRigidArea(new Dimension(65,0)),BorderLayout.WEST);
        //add(Box.createRigidArea(new Dimension(0,65)),BorderLayout.NORTH);

//        add(panel);
        add(START);
        add(HIGHSCORES);
        add(EXIT);
        add(background);
        //add(Box.createRigidArea(new Dimension(65,0)),BorderLayout.EAST);
        //add(Box.createRigidArea(new Dimension(0,65)),BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == START){
            dispose();
            new ChoosingGridSizeFrame();
        }
        if (e.getSource() == HIGHSCORES){
            new HighscoresFrame();
        }
        if (e.getSource() == EXIT){
            System.exit(0);
        }
    }
}