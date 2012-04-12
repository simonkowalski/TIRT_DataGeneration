package mobilitymodels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import logic.*;

/**
 * @author Tomasz 'TC' Klimek
 */
public class DrawPanel extends JPanel {
    private Color[] colors;
    
    private MobilityModel[] models;
    private int selected;
    
    /**
     * Basic constructor.
     * 
     * @param parent Parent Window object
     */
    public DrawPanel(Window parent) {
        
        colors = new Color[8];
        colors[0] = new Color(0, 0, 0);
        colors[1] = new Color(255, 0, 0);
        colors[2] = new Color(0, 255, 0);
        colors[3] = new Color(0, 0, 255);
        colors[4] = new Color(255, 255, 0);
        colors[5] = new Color(0, 255, 255);
        colors[6] = new Color(255, 0, 255);
        colors[7] = new Color(255, 255, 255);
        
        models = new MobilityModel[3];
        models[Window.RANDOM_WALK] = new RandomWalk(parent, 8);
        models[Window.LEVY_WALK] = new LevyWalk(parent, 8);
        models[Window.SLAW] = new SLAW(parent, 8);
        
        selected = Window.RANDOM_WALK;
        
        setBackground(Color.LIGHT_GRAY);
    }
    
    @Override
    public void paintComponent(Graphics gDC){
        super.paintComponent(gDC);
        
        Graphics2D g2D = (Graphics2D)gDC;
        g2D.drawRect(0, 0, 500, 500);
        g2D.setStroke(new BasicStroke(2));
        
        if(getModels() != null && getModels().length > selected && getCurrentModel() != null) {
            boolean paused = getCurrentModel().isPaused();
            if(!paused) {
                getCurrentModel().pauseModel();
            }
        
            double time = getCurrentModel().getTime();
            List<Entity> entities = getCurrentModel().getEntities();
            for(int i=0; i<entities.size(); i++) {
                g2D.setColor(colors[i%colors.length]);
                
                LinkedList<Position> path = entities.get(i).getWalkedPath();
                ListIterator<Position> iter = path.listIterator();
                Position p1 = iter.next();
                
                g2D.drawRect((int) p1.getX()*5 - 1, (int) p1.getY()*5 - 1, 2, 2);
                g2D.fillRect((int) p1.getX()*5 - 1, (int) p1.getY()*5 - 1, 2, 2);
                
                while(iter.hasNext()) {
                    Position p2 = iter.next();
                    
                    if(getCurrentModel().needLines())
                        g2D.drawLine((int) p1.getX()*5, (int) p1.getY()*5, (int) p2.getX()*5, (int) p2.getY()*5);
                    g2D.drawRect((int) p2.getX()*5 - 1, (int) p2.getY()*5 - 1, 2, 2);
                    p1 = p2;
                }
            }
            g2D.setColor(new Color(0, 0, 0, 96));
            g2D.fillRect(3, 3, 42, 15);
            g2D.setColor(Color.WHITE);
            String t = Double.toString(time);
            g2D.drawString(t.substring(0, Math.min(t.length(), 6)), 5, 15);
            
            if(!paused) {
                getCurrentModel().resumeModel();
            }
        }
    }
    
    public void pauseResume() {
        if(getCurrentModel().isPaused()) {
            getCurrentModel().resumeModel();
        } else {
            getCurrentModel().pauseModel();
        }
    }

    private MobilityModel getCurrentModel() {
        return models[selected];
    }
    
    public int getSelected() {
        return selected;
    }
    
    public void setSelected(int selected) {
        getCurrentModel().pauseModel();
        this.selected = selected;
    }

    /**
     * @return the models
     */
    public MobilityModel[] getModels() {
        return models;
    }
}
