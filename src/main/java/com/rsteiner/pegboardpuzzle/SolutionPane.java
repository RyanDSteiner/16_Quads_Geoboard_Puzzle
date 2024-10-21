package com.rsteiner.pegboardpuzzle;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SolutionPane {
    private AnchorPane pane;
    private int num;
    private Circle[] circles;

    public SolutionPane(AnchorPane p, int n) {
        pane = p;
        num = n;
    }

    public void displayCircles() {
        circles = new Circle[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Circle circle = new Circle();
                circle.setRadius(4);
                circle.setFill(javafx.scene.paint.Color.web("#909090"));
                circle.setStroke(javafx.scene.paint.Color.web("#252525"));
                circle.setLayoutY(36 + 45*i);
                circle.setLayoutX(36 + 45*j);
                circles[index] = circle;
                index++;
                pane.getChildren().add(circle);
            }
        }
    }

    public void connectCircles(int a, int b, int c, int d) {
        connect(circles[a-1], circles[b-1]);
        connect(circles[b-1], circles[c-1]);
        connect(circles[c-1], circles[d-1]);
        connect(circles[d-1], circles[a-1]);
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
