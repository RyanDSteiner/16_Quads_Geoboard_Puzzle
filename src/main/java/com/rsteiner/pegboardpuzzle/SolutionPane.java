package com.rsteiner.pegboardpuzzle;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SolutionPane {
    private AnchorPane pane;
    private int num;
    private Circle[] circles;
    private Rectangle rectangle;

    public SolutionPane(AnchorPane p, int n) {
        pane = p;
        num = n;
    }

    public void displayBoard() {
        circles = new Circle[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Circle circle = new Circle();
                circle.setRadius(4);
                circle.setFill(javafx.scene.paint.Color.web("#c6c6c6"));
                circle.setStroke(javafx.scene.paint.Color.web("#909090"));
                circle.setLayoutY(36 + 45*i);
                circle.setLayoutX(36 + 45*j);
                circles[index] = circle;
                index++;
                pane.getChildren().add(circle);
            }
        }
    }

    public void connectCircles(int[] solution) {
        if (solution.length == 4) {
            Circle a, b, c, d;
            a = circles[solution[0] - 1];
            b = circles[solution[1] - 1];
            c = circles[solution[2] - 1];
            d = circles[solution[3] - 1];
            Circle[] colorChange = { a, b, c, d };
            for (int i = 0; i < colorChange.length; i++) {
                colorChange[i].setFill(javafx.scene.paint.Color.web("#909090"));
                colorChange[i].setStroke(javafx.scene.paint.Color.web("#252525"));
            }
            connect(a, b);
            connect(b, c);
            connect(c, d);
            connect(d, a);
        }
    }

    public void connect(Circle a, Circle b) {
        Line connector = new Line();
        connector.setStartX(a.getLayoutX());
        connector.setStartY(a.getLayoutY());
        connector.setEndX(b.getLayoutX());
        connector.setEndY(b.getLayoutY());
        pane.getChildren().add(connector);
    }
}
