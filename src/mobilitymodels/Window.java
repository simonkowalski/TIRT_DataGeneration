package mobilitymodels;

import logic.MobilityModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author TC
 */
public class Window extends JFrame {

    public static final int RANDOM_WALK = 0;
    public static final int LEVY_WALK = 1;
    public static final int SLAW = 2;
    private DrawPanel drawPanel;
    private JPanel leftPanel;
    private JComboBox modelSelection;
    private JButton startStopButton;
    private JSlider speedSlider;
    private JLabel entitiesCounter;

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 657, 530);
        setVisible(true);
        setTitle("TIRT - mobility models visualisation");
        setResizable(false);
        setAlwaysOnTop(true);

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, getHeight()));

        drawPanel = new DrawPanel(this);

        String[] models = {"Random Walk", "Levy Walk", "SLAW"};
        modelSelection = new JComboBox(models);
        modelSelection.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        switch (modelSelection.getSelectedIndex()) {
                            case RANDOM_WALK:
                                drawPanel.setSelected(RANDOM_WALK);
                                break;
                            case LEVY_WALK:
                                drawPanel.setSelected(LEVY_WALK);
                                break;
                            case SLAW:
                                drawPanel.setSelected(SLAW);
                                break;
                            default:
                            // Do nothing...
                        }
                        entitiesCounter.setText(Integer.toString(getCurrentModel().getEntities().size()));
                        startStopButton.setText("Start");
                        repaint();
                    }
                });
            }
        });
        modelSelection.setSelectedIndex(0);

        leftPanel.add(modelSelection);

        leftPanel.add(new JLabel("      Speed      "));
        
        speedSlider = new JSlider(JSlider.HORIZONTAL, 5, 120, 40);
        speedSlider.setPreferredSize(new Dimension(100, 20));
        speedSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (drawPanel != null && drawPanel.getModels() != null) {
                    for (MobilityModel m : drawPanel.getModels()) {
                        m.setSpeed((int) Math.pow(2, ((double) speedSlider.getValue()) / 10));
                    }
                }
            }
        });

        leftPanel.add(speedSlider);

        leftPanel.add(new JLabel("         Agents Count         "));

        JButton minusButton = new JButton("-");
        minusButton.setPreferredSize(new Dimension(45, 20));
        minusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (drawPanel != null) {
                            MobilityModel m = getCurrentModel();
                            m.setEntitiesCount(m.getEntities().size() - 1);
                        }
                        entitiesCounter.setText(Integer.toString(getCurrentModel().getEntities().size()));
                        repaint();
                    }
                });
            }
        });

        leftPanel.add(minusButton);

        entitiesCounter = new JLabel(Integer.toString(getCurrentModel().getEntities().size()));

        leftPanel.add(entitiesCounter);

        JButton plusButton = new JButton("+");
        plusButton.setPreferredSize(new Dimension(45, 20));
        plusButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (drawPanel != null) {
                            MobilityModel m = getCurrentModel();
                            m.setEntitiesCount(m.getEntities().size() + 1);
                        }
                        entitiesCounter.setText(Integer.toString(getCurrentModel().getEntities().size()));
                        repaint();
                    }
                });
            }
        });

        leftPanel.add(plusButton);

        startStopButton = new JButton("Start");
        startStopButton.setPreferredSize(new Dimension(90, 20));
        startStopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if(drawPanel != null) {
                            drawPanel.pauseResume();
                            startStopButton.setText(getCurrentModel().isPaused() ? "Start" : "Stop");
                        }
                    }
                });
            }
        });

        leftPanel.add(startStopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(90, 20));
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (drawPanel != null) {
                            getCurrentModel().clear();
                        }
                        startStopButton.setText("Start");
                        entitiesCounter.setText(Integer.toString(getCurrentModel().getEntities().size()));
                        repaint();
                    }
                });
            }
        });

        leftPanel.add(resetButton);

        getContentPane().add(BorderLayout.WEST, leftPanel);

        getContentPane().add(drawPanel);

        for (MobilityModel m : drawPanel.getModels()) {
            m.setSpeed(speedSlider.getValue());
            m.start();
            m.pauseModel();
        }
    }

    private MobilityModel getCurrentModel() {
        return drawPanel.getModels()[modelSelection.getSelectedIndex()];
    }
}
