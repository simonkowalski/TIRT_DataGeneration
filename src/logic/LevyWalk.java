package logic;

import mobilitymodels.Window;
import org.apache.commons.math3.random.RandomDataImpl;

/**
 *
 * @author Kobier
 */
public class LevyWalk extends MobilityModel {
    
    private RandomDataImpl rand;
    
    public LevyWalk(Window window, int entitiesCount) {
        super(window, entitiesCount);
        rand = new RandomDataImpl();
    }

    @Override
    public void calculateNextStep(Entity e) {
        double distance = rand.nextCauchy(0, 0.20);
        int angle = rand.nextInt(0, 360);
        double currentX = e.getCurrentPosition().getX();
        double currentY = e.getCurrentPosition().getY();
       
        double theta = Math.toRadians(angle);
        double newX = currentX + distance*Math.cos(theta);
        double newY = currentY + distance*Math.sin(theta);
        
        if(newX > 100) newX = 100;
        if(newX < 0) newX = 0;
        if(newY > 100) newY = 100;
        if(newY < 0) newY = 0;
        e.setNextStep(new Step(time+rand.nextUniform(0, 1.0), newX, newY));
    }
} 