package logic;

import java.util.LinkedList;

/**
 * @author Tomasz 'TC' Klimek
 */
public class Entity {
    private Step nextStep;
    // additional field that I don't use but I don't know how other models work
    private LinkedList<Step> previousSteps;
    private LinkedList<Position> walkedPath;
    private Position currentPosition;
    
    /**
     * Basic constructor.
     * 
     * @param startX x coordinate of starting position
     * @param startY y coordinate of starting position
     */
    public Entity(double startX, double startY) {
        currentPosition = new Position(startX, startY);
        walkedPath = new LinkedList<>();
        walkedPath.add(currentPosition);
        previousSteps = new LinkedList<>();
        previousSteps.add(new Step(0, startX, startY));
    }
    
    /**
     * Simulates the path for entity up to time t.
     * 
     * @param t the time to stop walking
     */
    public void walk(double t) {
        if(nextStep != null) {
            if(nextStep.getT() <= t) {
                currentPosition = new Position(nextStep.getX(), nextStep.getY());
                walkedPath.add(getCurrentPosition());
                getPreviousSteps().add(new Step(0, nextStep.getX(), nextStep.getY()));
                nextStep = null;
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    /**
     * Gets path that entity walked so far.
     *
     * @return LinkedList with previous Positions
     */
    public LinkedList<Position> getWalkedPath() {
        return walkedPath;
    }
    
    /**
     * Sets next step for entity.
     *
     * @param step the next step to do
     */
    public void setNextStep(Step step) {
        nextStep = step;
    }
    
    /**
     * Gets the next step of entity.
     *
     * @return the Step object
     */
    public Step getNextStep() {
        return nextStep;
    }
    
    /**
     * @return the previousSteps
     */
    public LinkedList<Step> getPreviousSteps() {
        return previousSteps;
    }
    
    /**
     * @return the currentPosition
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }
    //</editor-fold>
}