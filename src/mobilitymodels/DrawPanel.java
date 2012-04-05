package mobilitymodels;

import logic.MobilityModel;
import logic.Position;
import logic.ExampleModel;
import logic.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JPanel;

/**
 * @author Tomasz 'TC' Klimek
 */
public class DrawPanel extends JPanel {
    private Color[] colors;
    
    private Window parent;
    private MobilityModel[] models;
    private int selected;
    
    /**
     * Basic constructor.
     * 
     * @param parent Parent Window object
     */
    public DrawPanel(Window parent) {
        this.parent = parent;
        
        colors = new Color[8];
        colors[0] = new Color(0, 0, 0, 128);
        colors[1] = new Color(255, 0, 0, 128);
        colors[2] = new Color(0, 255, 0, 128);
        colors[3] = new Color(0, 0, 255, 128);
        colors[4] = new Color(255, 255, 0, 128);
        colors[5] = new Color(0, 255, 255, 128);
        colors[6] = new Color(255, 0, 255, 128);
        colors[7] = new Color(255, 255, 255, 128);
        
        models = new MobilityModel[3];
        models[Window.RANDOM_WALK] = new ExampleModel(parent, 3);
        models[Window.LEVY_WALK] = new ExampleModel(parent, 3);
        models[Window.TEN_TRZECI_WALK] = new ExampleModel(parent, 3);
        
        selected = Window.RANDOM_WALK;
        
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics gDC){
        super.paintComponent(gDC);

        if(getModels() != null && getModels().length > selected && getModels()[selected] != null) {
            ArrayList<Entity> entities = getModels()[selected].getEntities();
            for(int i=0; i<entities.size(); i++) {
                gDC.setColor(colors[i%colors.length]);
                
                LinkedList<Position> path = entities.get(i).getWalkedPath();
        //System.out.println("path " + path.size());
                ListIterator<Position> iter = path.listIterator();
                Position p1 = iter.next();
                gDC.drawRect((int) p1.getX()*5 - 1, (int) p1.getY()*5 - 1, 2, 2);
                Position p2;
                
                while(iter.hasNext()) {
                    p2 = iter.next();
                    
                    gDC.drawLine((int) p1.getX()*5, (int) p1.getY()*5, (int) p2.getX()*5, (int) p2.getY()*5);
                    gDC.drawRect((int) p2.getX()*5 - 1, (int) p2.getY()*5 - 1, 2, 2);
                    p1 = p2;
                }
            }
        }
    }
    
    public void pauseResume() {
        if(!getModels()[selected].isPaused()) {
            getModels()[selected].pauseModel();
        } else {
            getModels()[selected].resumeModel();
        }
    }
    
    public int getSelected() {
        return selected;
    }
    
    public void setSelected(int selected) {
        getModels()[this.selected].pauseModel();
        this.selected = selected;
    }

    /**
     * @return the models
     */
    public MobilityModel[] getModels() {
        return models;
    }
}
