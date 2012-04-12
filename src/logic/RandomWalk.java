package logic;

import java.util.Random;
import mobilitymodels.Window;

/**
 * @author Tomasz 'TC' Klimek
 */
public class RandomWalk extends MobilityModel {
    
    private Random rand;
    
    public RandomWalk(Window parent, int entitiesCount) {
        super(parent, entitiesCount);
        rand = new Random();
    }

    @Override
    public void calculateNextStep(Entity e) {
        double distance = rand.nextDouble()*10;
        
        double angle = rand.nextInt(360);
        
        double currentX = e.getCurrentPosition().getX();
        double currentY = e.getCurrentPosition().getY();
       
        double theta = Math.toRadians(angle);
        double direction = chooseDirection();
        double newX = currentX + distance*Math.cos(theta) * direction;
        direction = chooseDirection();
        double newY = currentY + distance*Math.sin(theta) * direction;
        
        if(newX > 100) newX = 100;
        if(newX < 0) newX = 0;
        if(newY > 100) newY = 100;
        if(newY < 0) newY = 0;
        e.setNextStep(new Step(time+rand.nextDouble(), newX, newY));
    }

    private int chooseDirection() {
        return rand.nextInt(2) == 0 ? -1 : 1;
    }
}
