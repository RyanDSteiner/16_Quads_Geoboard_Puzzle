package com.rsteiner.pegboardpuzzle;

import java.util.HashSet;
import java.util.Set;

public class QuadSet {
    private class Quadrilateral {
        protected Set<String> solutions;
        protected boolean found = false;
    }

    private Quadrilateral[] quads;

    public QuadSet() {
        quads = new Quadrilateral[16];
        quads[0].solutions = Set.of("1254", "2365", "4587", "5698");
    }

    public int checkSolution(String answer) {
        for (int i = 0; i < 16; i++) {
            if (quads[i].solutions.contains(answer)) {
                return i+1;
            }
    }
}
