package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public boolean isFilledIn(Coordinate coordinate) { // TODO: Implement this
        ArrayList<Coordinate> coordinatesOfSelectedPoints = new ArrayList<>(selectedPoints);
        if (coordinatesOfSelectedPoints.size() == 3) {
            double coordinateAX = coordinatesOfSelectedPoints.get(0).getX();
            double coordinateAY = coordinatesOfSelectedPoints.get(0).getY();
            double coordinateBX = coordinatesOfSelectedPoints.get(1).getX();
            double coordinateBY = coordinatesOfSelectedPoints.get(1).getY();
            double coordinateCX = coordinatesOfSelectedPoints.get(2).getX();
            double coordinateCY = coordinatesOfSelectedPoints.get(2).getY();

            double a1 = (coordinateAY - coordinateBY) / (coordinateAX - coordinateBX);
            double a2 = (coordinateBY - coordinateCY) / (coordinateBX - coordinateCX);
            double a3 = (coordinateCY - coordinateAY) / (coordinateCX - coordinateAX);

            double b1 = coordinateAY - (a1 * coordinateAX);
            double b2 = coordinateBY - (a2 * coordinateBX);
            double b3 = coordinateCY - (a3 * coordinateCX);


            System.out.println("a1=" + a1 + " b1=" + b1 );
            System.out.println("a2=" + a2 + " b2=" + b2 );
            System.out.println("a3=" + a3 + " b3=" + b3 );
            boolean first = false;
            boolean second = false;
            boolean third = false;

            if (coordinateCY > a1 * coordinateCX + b1) {
                if (coordinate.getY() > a1 * coordinate.getX() + b1)
                    first = true;
                else
                    first = false;
            }

            if (coordinateCY < a1 * coordinateCX + b1) {
                if (coordinate.getY() < a1 * coordinate.getX() + b1)
                    first = true;
                else
                    first = false;
            }


            if (coordinateAY > a2 * coordinateAX + b2) {
                if (coordinate.getY() > a2 * coordinate.getX() + b2)
                    second = true;
                else
                    second = false;
            }

            if (coordinateAY < a2 * coordinateAX + b2) {
                if (coordinate.getY() < a2 * coordinate.getX() + b2)
                    second = true;
                else
                    second = false;
            }


            if (coordinateBY > a3 * coordinateBX + b3) {
                if (coordinate.getY() > a3 * coordinate.getX() + b3)
                    third = true;
                else
                    third = false;
            }

            if (coordinateBY < a3 * coordinateBX + b3) {
                if (coordinate.getY() < a3 * coordinate.getX() + b3)
                    third = true;
                else
                    third = false;
            }
            if (first && second && third)
                return true;
        }

        return false;
    }
}
