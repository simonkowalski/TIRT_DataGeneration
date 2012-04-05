/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilitymodels;

import logic.MobilityModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author TC
 */
public class Window extends JFrame {
    public static final int RANDOM_WALK = 0;
    public static final int LEVY_WALK = 1;
    public static final int TEN_TRZECI_WALK = 2;
    
    private DrawPanel drawPanel;
    private JPanel bottomPanel;
    private JComboBox modelSelection;
    private JButton startStopButton;
    private JSlider speedSlider;

    public Window() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(50, 50, 507, 563);
	setVisible(true);
	setTitle("TIRT - mobility models visualisation");
        setResizable(false);
        setAlwaysOnTop(true);
        
        bottomPanel = new JPanel();
        
        String[] models = {"RandomWalk", "Levy Walk", "Ten trzeci walk..."};
        modelSelection = new JComboBox(models);
        modelSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        switch(modelSelection.getSelectedIndex()) {
                            case RANDOM_WALK:
                                drawPanel.setSelected(RANDOM_WALK);
                                break;
                            case LEVY_WALK:
                                drawPanel.setSelected(LEVY_WALK);
                                break;
                            case TEN_TRZECI_WALK:
                                drawPanel.setSelected(TEN_TRZECI_WALK);
                                break;
                            default:
                                // Do nothing...
                        }
                        startStopButton.setText("Start");
                        repaint();
                    }
                });
            }
        });
        modelSelection.setSelectedIndex(0);
        
        bottomPanel.add(modelSelection);
        
        startStopButton = new JButton("Start");
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if(drawPanel != null) {
                            drawPanel.pauseResume();
                            startStopButton.setText(drawPanel.getModels()[drawPanel.getSelected()].isPaused() ? "Start" : "Stop");
                        }
                    }
                });
            }
        });
        
        bottomPanel.add(startStopButton);
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if(drawPanel != null) {
                            drawPanel.getModels()[drawPanel.getSelected()].clear();
                        }
                        repaint();
                    }
                });
            }
        });
        
        bottomPanel.add(resetButton);
        
        getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        
        drawPanel = new DrawPanel(this);
        for(MobilityModel m : drawPanel.getModels()) {
            m.start();
        }
        
        getContentPane().add(drawPanel);
    }
}
