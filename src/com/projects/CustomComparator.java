package com.projects;

import java.util.Comparator;

public class CustomComparator implements Comparator<FootBallClub> {
    @Override
    public int compare(FootBallClub fc1, FootBallClub fc2) {
        if (fc1.getPoints() > fc2.getPoints()) {
            return -1;
        } else
            if (fc1.getPoints() < fc2.getPoints()) {
                return 1;
            }
            else {
                int goalDifference1 = fc1.getScoredGoalsCount() - fc2.getScoredGoalsCount();
                int goalDifference2 = fc2.getScoredGoalsCount() - fc1.getScoredGoalsCount();
                if (goalDifference1 > goalDifference2) {
                    return -1;
                } else {
                    if (goalDifference1 < goalDifference2) {
                        return 1;
                    }
                    else return 0;
                }
            }
    }
}
