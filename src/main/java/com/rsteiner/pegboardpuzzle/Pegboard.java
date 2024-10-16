package com.rsteiner.pegboardpuzzle;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Pegboard extends AnchorPane {
    private static Peg[] pegs = new Peg[9];
    private Line[] lines = new Line[4];
    private Peg first;
    private Peg lastClicked;
    private int count = 0;

    public class Peg {
        protected Circle circle;
        protected int n;
        protected boolean pressed = false;
        protected Peg next;

        protected Peg(Circle c, int num) {
            this.circle = c;
            this.n = num;
        }
        public boolean wasPressed() {
            return pressed;
        }
    }

    public Pegboard(Circle c1, Circle c2, Circle c3, Circle c4, Circle c5, Circle c6, Circle c7, Circle c8, Circle c9,
                    Line l1, Line l2, Line l3, Line l4) {
        pegs[0] = new Peg(c1, 1); pegs[1] = new Peg(c2, 2); pegs[2] = new Peg(c3, 3);
        pegs[3] = new Peg(c4, 4); pegs[4] = new Peg(c5, 5); pegs[5] = new Peg(c6, 6);
        pegs[6] = new Peg(c7, 7); pegs[7] = new Peg(c8, 8); pegs[8] = new Peg(c9, 9);
        lines[0] = l1;  lines[1] = l2; lines[2] = l3; lines[3] = l4;
    }

    public Peg getPeg(int n) {
        if (n < 1 || n > 9) {
            return null; }
        else if(pegs[n-1] == null) {
            return null; }
        else {
            return pegs[n-1];
        }
    }

    public int press(int index) {
        //Check if peg exists and is valid
        if (index < 0 || index > 9) {
            return -3;
        }
        Peg peg = pegs[index - 1];
        if (peg == null) {
            return -3;
        }

        //Handle cases where peg was already pressed
        if (peg.pressed) {
            if (count == 4 && peg.n == first.n) {
                count++;
                connect(lastClicked, peg, count);
                return 0;
            } else {
                return -2;
            }
        }

        //Handle cases where peg was not pressed
        if (count == 4) { return -1; }
        peg.pressed = true;
        count++;
            if (first == null) {
                first = peg;
                lastClicked = peg;
            }
            else {
                lastClicked.next = peg;
                connect(lastClicked, peg, count);
                lastClicked = peg;
            }
            return 1;
    }

    public void reset() {
        first = null;
        lastClicked = null;
        count = 0;
        for (int i = 0; i < 9; i++) {
            pegs[i].pressed = false;
            pegs[i].next = null;
            pegs[i].circle.setFill(javafx.scene.paint.Color.web("#944314"));
        }
        for (int i = 0; i < 4; i++) {
            lines[i].setVisible(false);
        }
    }

    public void connect(Peg p1, Peg p2, int count) {
        Line connector = lines[count-2];
        connector.setStartX(p1.circle.getLayoutX());
        connector.setStartY(p1.circle.getLayoutY());
        connector.setEndX(p2.circle.getLayoutX());
        connector.setEndY(p2.circle.getLayoutY());
        connector.setVisible(true);
    }

    public String trace () {
        int[] vertices = new int[4];
        Peg iter = first;
        int i = 0;
        while (iter != null && i < 4) {
            vertices[i] = iter.n;
            iter = iter.next;
            i++;
        }

        return traceReformat(vertices);
    }

    private String traceReformat (int[] v) {
        if (v.length != 4) { return null; }
        //Find lowest value in chain
        int index = 0;
        int min = v[index];
        for (int i = 1; i < 4; i++) {
            if (v[i] < min) {
                index = i;
                min = v[i];
            }
        }
        //Covert to string that starts with min
        String s = "";
        for (int i = 0; i < 4; i++) {
            s+= v[index];
            if (index == 3) { index = 0; }
            else { index++; }
        }
        return s;
    }



}
