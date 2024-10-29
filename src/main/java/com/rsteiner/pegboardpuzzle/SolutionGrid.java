package com.rsteiner.pegboardpuzzle;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class SolutionGrid extends GridPane {
    private Map<String, Integer> solutionSet;
    private final SolutionPane[] solutionPanes;


    public SolutionGrid(AnchorPane[] panes) {
        buildSolutionSet();
        solutionPanes = new SolutionPane[16];
        for(int i = 0; i < panes.length; i++) {
            solutionPanes[i] = new SolutionPane(panes[i]);
        }

    }

    public int checkSolution(String answer) {
        int solution = solutionSet.getOrDefault(answer, 0);
        if (solution == 0) {
            solution = solutionSet.getOrDefault(reverseAnswer(answer), 0);
        }
        return solution;
    }

    String reverseAnswer (String answer) {
        if (answer.length() != 4) { return answer; }
        else {
            return "" + answer.charAt(0) + answer.charAt(3) + answer.charAt(2) + answer.charAt(1);
        }
    }

    void buildSolutionSet() {
        solutionSet = new HashMap<>();
        solutionSet.put("1254", 1); solutionSet.put("2365", 1); solutionSet.put("4587", 1); solutionSet.put("5698", 1);

        solutionSet.put("1793", 2);

        solutionSet.put("2684", 3);

        solutionSet.put("1287", 4); solutionSet.put("2398", 4); solutionSet.put("4697", 4); solutionSet.put("1463", 4);

        solutionSet.put("3974", 5); solutionSet.put("1697", 5); solutionSet.put("1398", 5); solutionSet.put("2397", 5);
        solutionSet.put("1394", 5); solutionSet.put("1367", 5); solutionSet.put("1297", 5); solutionSet.put("1387", 5);

        solutionSet.put("2463", 6); solutionSet.put("5796", 6); solutionSet.put("1365", 6); solutionSet.put("4896", 6);
        solutionSet.put("1462", 6); solutionSet.put("4597", 6); solutionSet.put("1453", 6); solutionSet.put("4786", 6);
        solutionSet.put("2874", 6); solutionSet.put("3589", 6); solutionSet.put("1587", 6); solutionSet.put("2698", 6);
        solutionSet.put("1257", 6); solutionSet.put("2863", 6); solutionSet.put("1482", 6); solutionSet.put("2593", 6);

        solutionSet.put("2374", 7); solutionSet.put("1269", 7); solutionSet.put("1489", 7); solutionSet.put("3786", 7);

        solutionSet.put("2387", 8); solutionSet.put("1298", 8); solutionSet.put("3674", 8); solutionSet.put("1496", 8);

        solutionSet.put("2354", 9); solutionSet.put("1265", 9); solutionSet.put("4598", 9); solutionSet.put("5687", 9);
        solutionSet.put("2574", 9); solutionSet.put("1485", 9); solutionSet.put("3685", 9); solutionSet.put("2596", 9);

        solutionSet.put("2957", 10); solutionSet.put("1835", 10); solutionSet.put("1576", 10); solutionSet.put("3594", 10);

        solutionSet.put("3854", 11); solutionSet.put("1658", 11); solutionSet.put("2765", 11); solutionSet.put("2945", 11);

        solutionSet.put("1384", 12); solutionSet.put("1368", 12); solutionSet.put("2796", 12); solutionSet.put("2974", 12);
        solutionSet.put("3984", 12); solutionSet.put("2394", 12); solutionSet.put("1267", 12); solutionSet.put("1786", 12);

        solutionSet.put("1896", 13); solutionSet.put("3874", 13); solutionSet.put("1294", 13); solutionSet.put("2763", 13);

        solutionSet.put("2384", 14); solutionSet.put("1268", 14); solutionSet.put("2786", 14); solutionSet.put("2489", 14);
        solutionSet.put("1486", 14); solutionSet.put("2476", 14); solutionSet.put("2496", 14); solutionSet.put("3684", 14);

        solutionSet.put("2597", 15); solutionSet.put("2975", 15); solutionSet.put("1358", 15); solutionSet.put("1583", 15);
        solutionSet.put("1567", 15); solutionSet.put("1756", 15); solutionSet.put("3945", 15); solutionSet.put("3954", 15);

        solutionSet.put("2657", 16); solutionSet.put("2576", 16); solutionSet.put("2594", 16); solutionSet.put("2954", 16);
        solutionSet.put("3845", 16); solutionSet.put("3485", 16); solutionSet.put("1685", 16); solutionSet.put("1568", 16);
    }

    void displaySolution(int n, int[] solution) {
        if (solution.length == 4) {
            SolutionPane s = solutionPanes[n - 1];
            s.displayBoard();
            s.connectCircles(solution);
        }
    }
}
