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

    public boolean isFilledIn(Coordinate p) {
        ArrayList<Coordinate> selected = new ArrayList<>(selectedPoints);
        if (selected.size() == 3) {
            Coordinate pA = selected.get(0);
            Coordinate pB = selected.get(1);
            Coordinate pC = selected.get(2);


            if (isInSameHalfPlane(pA, pB, pC, p)
                    && isInSameHalfPlane(pB, pC, pA, p)
                    && isInSameHalfPlane(pC, pA, pB, p))
                return true;
        }

        return false;
    }

    public boolean isInSameHalfPlane(Coordinate pA, Coordinate pB, Coordinate pC, Coordinate p) {
        if (pA.getX() == pB.getX()) { //vertical
            return (pC.getX() > pA.getX() && p.getX() > pA.getX()) || (pC.getX() < pA.getX() && p.getX() < pA.getX());
        } else { //otherwise
            double ax = pA.getX(), ay = pA.getY();
            double bx = pB.getX(), by = pB.getY();

            double a = (ay - by) / (ax - bx);
            double b = ay - (a * ax);

            return ((pC.getY() > a * pC.getX() + b) && (p.getY() > a * p.getX() + b)) || ((pC.getY() < a * pC.getX() + b) && (p.getY() < a * p.getX() + b));
        }
    }
}
