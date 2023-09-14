import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosingGridSizeFrame extends JFrame implements ActionListener {

    private JTextField gridSizeFieldRows = new JTextField(3);
    private JTextField gridSizeFieldColumns = new JTextField(3);
    private Button startButton = new Button("Play!");
    private int gridSizeRows;
    private int gridSizeColumns;

    public ChoosingGridSizeFrame(){

        startButton.addActionListener(this);

        JPanel panel = new JPanel();

        JLabel gridSizeLabel = new JLabel("Grid size: ");
        JLabel x = new JLabel("x");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        int borderWidth = 0;
        int borderHeight = 50;
        panel.setBorder(BorderFactory.createEmptyBorder(borderHeight, borderWidth, borderHeight, borderWidth));

        panel.add(gridSizeLabel);
        panel.add(gridSizeFieldRows);
        panel.add(x);
        panel.add(gridSizeFieldColumns);
        panel.add(startButton);

        contentPane.add(panel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(200, 200);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton){
            gridSizeRows = Integer.parseInt(gridSizeFieldRows.getText());
            gridSizeColumns = Integer.parseInt(gridSizeFieldColumns.getText());
            if (gridSizeColumns < 10 | gridSizeRows < 10){
                throw new IllegalArgumentException("too small input");
            }
            if (gridSizeColumns > 100 | gridSizeRows > 100){
                throw new IllegalArgumentException("too big input");
            }
            dispose();
            new GameFrame(gridSizeRows,gridSizeColumns);
        }
    }
}
