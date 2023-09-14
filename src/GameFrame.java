import GhostLabel.Ghost;
import InfoPanel.InfoPanel;
import PacmanLabel.Pacman;
import PacmanTableModel.PacmanTableModel;
import SubmitPanel.SubmitPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener {

    private static final int PACMAN = 1;
    private static final int WALL = 2;
    private static final int DOT = 3;
    private static final int GHOST = 4;
    private static final int EMPTY = 5;

    /*
    6. dodać życie RED
    7. zatrzymanie ducha na 5 sek. PINK
    8. punkty sie liczą x2 WHITE
    9. dodać 2 życia GREEN
    10. możliwość zjedzenia ducha MAGENTA
    */


    private boolean goingRight = true;
    private boolean goingLeft = false;
    private boolean goingUp = false;
    private boolean goingDown = false;


    InfoPanel infoPanel = new InfoPanel();

    String runInfo;

    public String getRunInfo() {
        return runInfo;
    }

    volatile PacmanTableModel model;
    JTable table;
    Pacman pacman;
    Ghost ghost;

    int ghostX;
    int ghostY;

    int pacmanX;
    int pacmanY;


    Thread points = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            runInfo = infoPanel.getResult();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });

    int scoreValue = 1;
    Thread nextFrame = new Thread(() -> {
        int valueForPacman = DOT;
        int storedValue = DOT;

        int timeForUpgrade = 0;

        int ghostStun = 0;
        Random random = new Random();
        int upgrade = 6;
        double probability = 0.25;
        while (!Thread.currentThread().isInterrupted()) {

            boolean rightPossible = false;
            boolean leftPossible = false;
            boolean upPossible = false;
            boolean downPossible = false;

            if (pacmanX < model.getColumnCount() - 1 && (model.getValueAt(pacmanY, pacmanX + 1).equals(DOT) || model.getValueAt(pacmanY, pacmanX + 1).equals(EMPTY) || model.getValueAt(pacmanY, pacmanX + 1).equals(GHOST) || (int) model.getValueAt(pacmanY, pacmanX + 1) >= 6)) {
                rightPossible = true;
            }
            // Move left
            if (pacmanX > 0 && (model.getValueAt(pacmanY, pacmanX - 1).equals(DOT) || model.getValueAt(pacmanY, pacmanX - 1).equals(EMPTY) || model.getValueAt(pacmanY, pacmanX - 1).equals(GHOST) || (int) model.getValueAt(pacmanY, pacmanX - 1) >= 6)) {
                leftPossible = true;
            }
            // Move up
            if (pacmanY > 0 && (model.getValueAt(pacmanY - 1, pacmanX).equals(DOT) || model.getValueAt(pacmanY - 1, pacmanX).equals(EMPTY) || model.getValueAt(pacmanY - 1, pacmanX).equals(GHOST) || (int) model.getValueAt(pacmanY - 1, pacmanX) >= 6)) {
                upPossible = true;
            }
            // Move down
            if (pacmanY < model.getRowCount() - 1 && (model.getValueAt(pacmanY + 1, pacmanX).equals(DOT) || model.getValueAt(pacmanY + 1, pacmanX).equals(EMPTY) || model.getValueAt(pacmanY + 1, pacmanX).equals(GHOST) || (int) model.getValueAt(pacmanY + 1, pacmanX) >= 6)) {
                downPossible = true;
            }

            if (goingRight & rightPossible) {
                valueForPacman = (int) model.getValueAt(pacmanY, pacmanX + 1);
                model.setValueAt(PACMAN, pacmanY, pacmanX + 1);
                model.setValueAt(EMPTY, pacmanY, pacmanX);
                pacmanX++;
            }
            if (goingLeft & leftPossible) {
                valueForPacman = (int) model.getValueAt(pacmanY, pacmanX - 1);
                model.setValueAt(PACMAN, pacmanY, pacmanX - 1);
                model.setValueAt(EMPTY, pacmanY, pacmanX);
                pacmanX--;
            }
            if (goingUp & upPossible) {
                valueForPacman = (int) model.getValueAt(pacmanY - 1, pacmanX);
                model.setValueAt(PACMAN, pacmanY - 1, pacmanX);
                model.setValueAt(EMPTY, pacmanY, pacmanX);
                pacmanY--;
            }
            if (goingDown & downPossible) {
                valueForPacman = (int) model.getValueAt(pacmanY + 1, pacmanX);
                model.setValueAt(PACMAN, pacmanY + 1, pacmanX);
                model.setValueAt(EMPTY, pacmanY, pacmanX);
                pacmanY++;
            }
            if (valueForPacman == DOT) {
                infoPanel.addScore(scoreValue);
            }
            if (valueForPacman == 6) {
                infoPanel.addHealth(1);
            }
            if (valueForPacman == 7) {
                ghostStun = 25;
            }
            if (valueForPacman == 8) {
                scoreValue = 2;
            }
            if (valueForPacman == 9) {
                infoPanel.addHealth(2);
            }
            if (valueForPacman == 10) {
                ghost.changeEdibleStatus();
            }
            valueForPacman = EMPTY;


            pacman.changeMouth();
            pacman.repaint();

            if (ghostY == pacmanY & ghostX == pacmanX){
                if (ghost.isEdible()){
                    ghostY = model.getGhostStartY();
                    ghostX = model.getGhostStartY();
                }else{
                    infoPanel.loseHealth();
                }
            }

            rightPossible = false;
            leftPossible = false;
            upPossible = false;
            downPossible = false;
            if (ghostStun == 0) {
                // Move right
                if (ghostX < model.getColumnCount() - 1 && (model.getValueAt(ghostY, ghostX + 1).equals(DOT) || model.getValueAt(ghostY, ghostX + 1).equals(EMPTY) || (int) model.getValueAt(ghostY, ghostX + 1) >= 6)|| model.getValueAt(ghostY, ghostX + 1).equals(PACMAN)) {
                    rightPossible = true;
                }
                // Move left
                if (ghostX > 0 && (model.getValueAt(ghostY, ghostX - 1).equals(DOT) || model.getValueAt(ghostY, ghostX - 1).equals(EMPTY) || (int) model.getValueAt(ghostY, ghostX - 1) >= 6) || model.getValueAt(ghostY,ghostX -1).equals(PACMAN)) {
                    leftPossible = true;
                }
                // Move up
                if (ghostY > 0 && (model.getValueAt(ghostY - 1, ghostX).equals(DOT) || model.getValueAt(ghostY - 1, ghostX).equals(EMPTY) || (int) model.getValueAt(ghostY - 1, ghostX) >= 6)|| model.getValueAt(ghostY - 1, ghostX).equals(PACMAN)) {
                    upPossible = true;
                }
                // Move down
                if (ghostY < model.getRowCount() - 1 && (model.getValueAt(ghostY + 1, ghostX).equals(DOT) || model.getValueAt(ghostY + 1, ghostX).equals(EMPTY) || (int) model.getValueAt(ghostY + 1, ghostX) >= 6)|| model.getValueAt(ghostY + 1, ghostX).equals(PACMAN)) {
                    downPossible = true;
                }


                int decision = -1;
                while (decision == -1) {
                    decision = random.nextInt(4);
                    switch (decision) {
                        case 0:
                            if (!rightPossible) {
                                decision = -1;
                            }
                            break;
                        case 1:
                            if (!leftPossible) {
                                decision = -1;
                            }
                            break;
                        case 2:
                            if (!upPossible) {
                                decision = -1;
                            }
                            break;
                        case 3:
                            if (!downPossible) {
                                decision = -1;
                            }
                            break;
                    }
                }

                if (upgrade < 10) {
                    timeForUpgrade++;
                    if (timeForUpgrade == 10) {
                        if (random.nextDouble() < probability) {
                            model.setValueAt(upgrade, ghostY, ghostX);
                            upgrade++;
                        } else {
                            model.setValueAt(storedValue, ghostY, ghostX);
                        }
                        timeForUpgrade = 0;
                    } else {
                        model.setValueAt(storedValue, ghostY, ghostX);
                    }
                } else {
                    model.setValueAt(storedValue, ghostY, ghostX);
                }

                switch (decision) {
                    case 0 -> { //right
                        if (model.getValueAt(ghostY, ghostX + 1).equals(DOT) || model.getValueAt(ghostY, ghostX + 1).equals(EMPTY) || (int) model.getValueAt(ghostY, ghostX + 1) >= 6) {
                            storedValue = (int) model.getValueAt(ghostY, ghostX + 1);
                        }
                        model.setValueAt(GHOST, ghostY, ghostX + 1);
                        ghostX++;
                    }
                    case 1 -> { //left
                        if (model.getValueAt(ghostY, ghostX - 1).equals(DOT) || model.getValueAt(ghostY, ghostX - 1).equals(EMPTY) || (int) model.getValueAt(ghostY, ghostX - 1) >= 6) {
                            storedValue = (int) model.getValueAt(ghostY, ghostX - 1);
                        }
                        model.setValueAt(GHOST, ghostY, ghostX - 1);
                        ghostX--;
                    }
                    case 2 -> { //up
                        if (model.getValueAt(ghostY - 1, ghostX).equals(DOT) || model.getValueAt(ghostY - 1, ghostX).equals(EMPTY) || (int) model.getValueAt(ghostY - 1, ghostX) >= 6) {
                            storedValue = (int) model.getValueAt(ghostY - 1, ghostX);
                        }
                        model.setValueAt(GHOST, ghostY - 1, ghostX);
                        ghostY--;
                    }
                    case 3 -> { //down
                        if (model.getValueAt(ghostY + 1, ghostX).equals(DOT) || model.getValueAt(ghostY + 1, ghostX).equals(EMPTY) || (int) model.getValueAt(ghostY + 1, ghostX) >= 6) {
                            storedValue = (int) model.getValueAt(ghostY + 1, ghostX);
                        }
                        model.setValueAt(GHOST, ghostY + 1, ghostX);
                        ghostY++;
                    }
                }
            }else{
                ghostStun--;
            }
            if (ghostY == pacmanY & ghostX == pacmanX){
                if (ghost.isEdible()){
                    ghostY = model.getGhostStartY();
                    ghostX = model.getGhostStartY();
                    ghostStun = 25;
                }else{
                    infoPanel.loseHealth();

                }
            }
            if (infoPanel.getHealth() <= 0){
                dispose();
                Thread.currentThread().interrupt();
                points.interrupt();
                new Menu();

                new SubmitPanel(Integer.toString(infoPanel.getScore()));
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        table.repaint();
    });

    public GameFrame(int rows, int columns) {
        model = new PacmanTableModel(rows, columns);
        table = new JTable(model);

        ghostX = model.getGhostStartX();
        ghostY = model.getGhostStartY();

        pacmanX = 1;
        pacmanY = 1;

        table.setRowHeight(10);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(10);
        }

        pacman = new Pacman();
        ghost = new Ghost();

        TableCellRenderer labelRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String valueAsString = value.toString();
                int val = Integer.parseInt(valueAsString);
                JLabel label;
                if (val == DOT) {
                    label = new JLabel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            g.setColor(Color.YELLOW);
                            g.fillOval(6, 2, 3, 3);
                        }
                    };
                    label.setBackground(Color.BLACK);
                    label.setOpaque(true);
                    return label;
                }
                if (val >= 6) { //upgrades
                    label = new JLabel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            if (val == 6) g.setColor(Color.RED);
                            if (val == 7) g.setColor(Color.PINK);
                            if (val == 8) g.setColor(Color.WHITE);
                            if (val == 9) g.setColor(Color.GREEN);
                            if (val == 10) g.setColor(Color.MAGENTA);
                            g.fillOval(0, 0, 10, 10);
                        }
                    };
                    label.setBackground(Color.BLACK);
                    label.setOpaque(true);
                    return label;
                }
                if (val == WALL) {
                    label = new JLabel();
                    label.setBackground(Color.BLUE);
                    label.setOpaque(true);
                    return label;
                }
                if (val == PACMAN) {
                    label = pacman;
                    label.setOpaque(false);
                    return label;
                }
                if (val == GHOST) {
                    label = ghost;
                    label.setOpaque(false);
                    return label;
                }
                if (val == EMPTY) {
                    label = new JLabel();
                    label.setBackground(Color.BLACK);
                    label.setOpaque(true);
                    return label;
                }
                return null;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(labelRenderer);
        }
        table.setBackground(Color.BLACK);
        table.setShowGrid(false);

        nextFrame.start();
        points.start();
        setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel();
        JPanel infoJPanel = new JPanel();

        tablePanel.setLayout(new BorderLayout());
        infoJPanel.setLayout(new BorderLayout());

        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        infoJPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        infoJPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        infoPanel.setFont(new Font("Verdana", Font.PLAIN, 12));
        infoPanel.setForeground(Color.BLACK);

        tablePanel.add(table, BorderLayout.CENTER);
        infoJPanel.add(this.infoPanel, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.PAGE_START);
        add(infoJPanel, BorderLayout.PAGE_END);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        table.addKeyListener(this);

        table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), "FIN");
        table.getActionMap().put("FIN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                nextFrame.interrupt();
                points.interrupt();
                new Menu();
                new SubmitPanel(Integer.toString(infoPanel.getScore()));
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                goingLeft = true;
                goingRight = false;
                goingUp = false;
                goingDown = false;
                pacman.setLeft();
            }
            case KeyEvent.VK_RIGHT -> {
                goingLeft = false;
                goingRight = true;
                goingUp = false;
                goingDown = false;
                pacman.setRight();
            }
            case KeyEvent.VK_UP -> {
                goingLeft = false;
                goingRight = false;
                goingUp = true;
                goingDown = false;
                pacman.setUp();
            }
            case KeyEvent.VK_DOWN -> {
                goingLeft = false;
                goingRight = false;
                goingUp = false;
                goingDown = true;
                pacman.setDown();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}

