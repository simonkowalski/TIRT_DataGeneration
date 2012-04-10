package logic;

import java.util.Random;
import mobilitymodels.Window;

/**
 * @author Tomasz 'TC' Klimek
 */
public class RandomWalk extends MobilityModel {
    
    public RandomWalk(Window parent, int entitiesCount) {
        super(parent, entitiesCount);
    }
    
    @Override
    public void calculateNextStep(Entity e) {
        Random rand = new Random();
        
        e.setNextStep(new Step(time+rand.nextDouble(), rand.nextDouble()*100, rand.nextDouble()*100));
    }
}
