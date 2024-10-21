package com.rsteiner.pegboardpuzzle;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class SolutionGrid extends GridPane {
    private Map<String, Integer> solutionSet;
    SolutionPane[] solutionPanes;


    public SolutionGrid(AnchorPane[] panes) {
        buildSolutionSet();
        solutionPanes = new SolutionPane[16];
        for(int i = 0; i < panes.length; i++) {
            solutionPanes[i] = new SolutionPane(panes[i], i + 1);
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
            String rAnswer = "" + answer.charAt(0) + answer.charAt(3) + answer.charAt(2) + answer.charAt(1);
            return rAnswer;
        }
    }

    void buildSolutionSet() {
        solutionSet = new HashMap<>();
        solutionSet.put("1254", 1); solutionSet.put("2365", 1); solutionSet.put("4587", 1); solutionSet.put("5698", 1);

        solutionSet.put("2354", 2); solutionSet.put("1265", 2); solutionSet.put("4598", 2); solutionSet.put("5687", 2);
        solutionSet.put("2574", 2); solutionSet.put("1485", 2); solutionSet.put("3685", 2); solutionSet.put("2596", 2);

        solutionSet.put("2684", 3);

        solutionSet.put("1793", 4);

        solutionSet.put("3974", 5); solutionSet.put("1697", 5); solutionSet.put("1398", 5); solutionSet.put("2397", 5);
        solutionSet.put("1394", 5); solutionSet.put("1367", 5); solutionSet.put("1297", 5); solutionSet.put("1387", 5);

        solutionSet.put("2463", 6); solutionSet.put("5796", 6); solutionSet.put("1365", 6); solutionSet.put("4896", 6);
        solutionSet.put("1462", 6); solutionSet.put("4597", 6); solutionSet.put("1453", 6); solutionSet.put("4786", 6);
        solutionSet.put("2874", 6); solutionSet.put("3589", 6); solutionSet.put("1587", 6); solutionSet.put("2698", 6);
        solutionSet.put("1257", 6); solutionSet.put("2863", 6); solutionSet.put("1482", 6); solutionSet.put("2593", 6);

        solutionSet.put("3854", 7); solutionSet.put("1658", 7); solutionSet.put("2765", 7); solutionSet.put("2945", 7);

        solutionSet.put("1896", 8); solutionSet.put("3874", 8); solutionSet.put("1294", 8); solutionSet.put("2763", 8);

        solutionSet.put("2597", 9); solutionSet.put("2975", 9); solutionSet.put("1358", 9); solutionSet.put("1583", 9);
        solutionSet.put("1567", 9); solutionSet.put("1756", 9); solutionSet.put("3945", 9); solutionSet.put("3954", 9);

        solutionSet.put("2957", 10); solutionSet.put("1835", 10); solutionSet.put("1576", 10); solutionSet.put("3594", 10);

        solutionSet.put("1384", 11); solutionSet.put("1368", 11); solutionSet.put("2796", 11); solutionSet.put("2974", 11);
        solutionSet.put("3984", 11); solutionSet.put("2394", 11); solutionSet.put("1267", 11); solutionSet.put("1786", 11);

        solutionSet.put("2387", 12); solutionSet.put("1298", 12); solutionSet.put("3674", 12); solutionSet.put("1496", 12);

        solutionSet.put("1287", 13); solutionSet.put("2398", 13); solutionSet.put("4697", 13); solutionSet.put("1463", 13);

        solutionSet.put("2384", 14); solutionSet.put("1268", 14); solutionSet.put("2786", 14); solutionSet.put("2489", 14);
        solutionSet.put("1486", 14); solutionSet.put("2476", 14); solutionSet.put("2496", 14); solutionSet.put("3684", 14);

        solutionSet.put("2374", 15); solutionSet.put("1269", 15); solutionSet.put("1489", 15); solutionSet.put("3786", 15);

        solutionSet.put("2657", 16); solutionSet.put("2576", 16); solutionSet.put("2594", 16); solutionSet.put("2954", 16);
        solutionSet.put("3845", 16); solutionSet.put("3485", 16); solutionSet.put("1685", 16); solutionSet.put("1568", 16);


        return;
    }

    void displaySolution(int n, String answer) {
        if (answer.length() == 4) {
            int a, b, c, d;
            SolutionPane solution = solutionPanes[n - 1];
            solution.displayCircles();
            a = answer.charAt(0) - '0';
            b = answer.charAt(1) - '0';
            c = answer.charAt(2) - '0';
            d = answer.charAt(3) - '0';
            solution.connectCircles(a, b, c, d);
        }
    }
}
