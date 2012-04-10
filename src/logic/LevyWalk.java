package logic;

import java.util.Random;
import mobilitymodels.Window;

/**
 *
 * @author Kobier
 */
public class LevyWalk extends MobilityModel {
    
    private Random rand;
    
    public LevyWalk(Window window, int entitiesCount) {
        super(window, entitiesCount);
        rand = new Random();
    }

    @Override
    public void calculateNextStep(Entity e) {
        double next = rand.nextGaussian() * 5;
        int angle = rand.nextInt(360);
        double currentX = e.getCurrentPosition().getX();
        double currentY = e.getCurrentPosition().getY();
        
        double theta = Math.toRadians(angle);
        double newX = currentX + next*Math.cos(theta);
        double newY = currentY + next*Math.sin(theta);
        
        if(newX > 100) newX = 100;
        if(newX < 0) newX = 0;
        if(newY > 100) newY = 100;
        if(newY < 0) newY = 0;
        e.setNextStep(new Step(time+1, newX, newY));
    }
} 