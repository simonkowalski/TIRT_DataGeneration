package logic;

import logic.Entity;
import java.util.ArrayList;
import java.util.Random;
import mobilitymodels.Window;

/**
 * @author Tomasz 'TC' Klimek
 */
public abstract class MobilityModel extends Thread {
    protected Window parent;
    protected ArrayList<Entity> entities;
    protected double time;
    private int dt;
    protected boolean paused;
    
    /**
     * Basic constructor.
     * 
     * @param parent the parent Window object
     */
    public MobilityModel(Window parent, int entitiesCount) {
        this.parent = parent;
        time = 0;
        dt = 100;
        
        entities = new ArrayList<>();
        Random rand = new Random();
        
        for(int i=0; i<entitiesCount; i++) {
            entities.add(new Entity(rand.nextDouble()*100, rand.nextDouble()*100));
        }
    }
    
    @Override
    public void run() {
        try {
            while(true) {
                sleep(dt);
                if(!isPaused()) {
                    makeStep();

                    parent.repaint();
                }
            }
        } catch (InterruptedException ex) {
            // TODO: handle exceptions 
        }
    }
    
    /**
     * Calculates the next steps for all entities.
     */
    private void makeStep() {
        for(Entity e : entities) {
            if(e.getNextStep() == null) {
                calculateNextStep(e);
            }
            e.walk(time);
        }
        time += 0.05;
    }

    /**
     * Makes the decision about next step for given entity.
     * 
     * @param e the Entity object
     */
    protected void calculateNextStep(Entity e) {
        // to implement
    }
    
    /**
     * Resets the model.
     */
    public void clear() {
        Random rand = new Random();
        
        for(Entity e : entities) {
            e = new Entity(rand.nextDouble()*100, rand.nextDouble()*100);
        }
    }
    
    /**
     * Pauses the model.
     */
    public void pauseModel() {
        paused = true;
    }
    
    /**
     * Resumes the paused model.
     */
    public void resumeModel() {
        paused = false;
    }
    
    /**
     * Checks if model is paused.
     * 
     * @return true if model is paused and false otherwise
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Gets all of entities in the model.
     * 
     * @return the ArrayList of Entity objects
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(int dt) {
        this.dt = dt;
    }
}