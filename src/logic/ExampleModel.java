package logic;

import java.util.Random;
import mobilitymodels.Window;

/**
 * @author Tomasz 'TC' Klimek
 */
public class ExampleModel extends MobilityModel {
    
    public ExampleModel(Window parent, int entitiesCount) {
        super(parent, entitiesCount);
    }
    
    @Override
    protected void calculateNextStep(Entity e) {
        Random rand = new Random();
        
        // in odd move it goes in X axis and in even it goes in Y axis...
        if(e.getWalkedPath().size()%2 == 0) {
            e.setNextStep(new Step(time+rand.nextDouble(), e.getCurrentPosition().getX()+1, e.getCurrentPosition().getY()));
        } else {
            e.setNextStep(new Step(time+rand.nextDouble(), e.getCurrentPosition().getX(), e.getCurrentPosition().getY()+1));
        }
    }
}
