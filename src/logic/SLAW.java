/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import mobilitymodels.Window;
import java.util.Random;

/**
 *
 * @author Kraxi
 */
public class SLAW extends MobilityModel{

    Random rand;
    LinkedList<Position> wayPoints;
    private double alpha;
    public SLAW(Window window, int entitiesCount) {
        super(window, entitiesCount);
        entities = new ArrayList<>();
        rand = new Random();
        generateWaypoints();

        for(int i=0; i<entitiesCount; i++) {
            entities.add(createEntity());
        }
        alpha = 0.3;
    }

        /**
     * Resets the model.
     */
    @Override
    public void clear() {
        pauseModel();
        Random rand = new Random();
        time = 0;
        entities = new ArrayList<>();

        for(int i=0; i<entitiesCount; i++) {
            Entity e = createEntity();
            calculateNextStep(e);
            entities.add(e);
        }

    }

    @Override
    public void setEntitiesCount(int entitiesCount) {
        if(entitiesCount > entities.size()) {
            Random rand = new Random();
            int entitiesToAdd = entitiesCount - entities.size();

            for(int i=0; i<entitiesToAdd; i++) {
                entities.add(createEntity());
            }
        } else if(entitiesCount < entities.size()) {
            int entitiesToDel = entities.size() - entitiesCount;

            for(int i=0; i<entitiesToDel; i++) {
                entities.remove(entities.size()-1);
            }
        }
        this.entitiesCount = entitiesCount;
    }

    @Override
    public void calculateNextStep(Entity e) {
        //for your consideration :D
        //what to do with visited waypoints? O.o
        //Might previous vertex be visited again?

        ArrayList<Double> distances = new ArrayList<>();

        for(int i = 0; i < wayPoints.size(); i++) {
            distances.add(e.getCurrentPosition().distance(wayPoints.get(i)));
        }
        ArrayList<Double> probabilities = new ArrayList<>();
        int maxProbIndex = 0;
        for(int i = 0; i < wayPoints.size(); i++) {
            double p = -1;
            if(!e.getCurrentPosition().equals(wayPoints.get(i))) {
                p = Math.pow(1/distances.get(i),alpha);
                double sum = 0;
                for(int j = 0; j < wayPoints.size(); j++) {
                    if(distances.get(j) != 0) {
                        sum += Math.pow(1/distances.get(j),2);
                    }
                }
                p /= sum;
//                System.out.println("Prawdopodobieństwo p "+p);
            }
            probabilities.add(p);
            if(p > probabilities.get(maxProbIndex)) {
                maxProbIndex = i;
            }
        }
//        System.out.println("MaxProbIndex -> "+maxProbIndex);
        e.setNextStep(new Step(time+rand.nextDouble(), wayPoints.get(maxProbIndex).getX(), wayPoints.get(maxProbIndex).getY()));

    }

    private void generateWaypoints() {
        wayPoints = new LinkedList<>();
        LinkedList<Position> temp = new LinkedList<>();
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                temp.add(new Position(i, j));
            }
        }
        for(int i = 0; i < 100; i++) {
            int index = rand.nextInt(temp.size());
            wayPoints.add(temp.get(index));
            temp.remove(index);
        }
//        System.out.println("generate waypoints -> "+wayPoints);
    }

    private Entity createEntity() {
        int index = rand.nextInt(wayPoints.size());
//        System.out.println("create Entity -> "+index);
        return new Entity(wayPoints.get(index).getX(), wayPoints.get(index).getY());
    }

}
