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
        protected Peg previous;

        protected Peg(Circle c, int num) {
            this.circle = c;
            this.n = num;
        }
        public boolean wasPressed() {
            return pressed;
        }
    }

    public Pegboard(Circle[] circles, Line[] l) {
        for (int i = 0; i < circles.length; i++) {
            pegs[i] = new Peg(circles[i], i+1);
        }
        this.lines = l;
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

    public int getCount() {
        return count;
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

        //Check a shape has already been formed
        if (count > 4) {
            return -4;
        }

        //Handle cases where peg was already pressed
        if (peg.pressed) {
            if (count == 4 && peg.n == first.n) {
                peg.previous = lastClicked;
                count++;
                connect(lastClicked, peg, count);
                lastClicked = peg;
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
                peg.previous = lastClicked;
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
            pegs[i].previous = null;
            pegs[i].circle.setFill(javafx.scene.paint.Color.web("#944314"));
        }
        for (int i = 0; i < 4; i++) {
            lines[i].setVisible(false);
        }
    }

    public void undo() {
        if (count < 1) { return; }

        if (count == 1) {
            lastClicked.circle.setFill(javafx.scene.paint.Color.web("#944314"));
            lastClicked.pressed = false;
            first = null;
            lastClicked = null;
            count--;
        }
        else {
            Line connector = lines[count - 2];
            connector.setVisible(false);
            Peg prev = lastClicked.previous;
            if (lastClicked != first) {
                lastClicked.circle.setFill(javafx.scene.paint.Color.web("#944314"));
                lastClicked.pressed = false;
            }
            lastClicked.previous = null;
            prev.next = null;
            lastClicked = prev;
            count--;
        }
        return;
    }

    public void connect(Peg p1, Peg p2, int count) {
        Line connector = lines[count-2];
        connector.setStartX(p1.circle.getLayoutX());
        connector.setStartY(p1.circle.getLayoutY());
        connector.setEndX(p2.circle.getLayoutX());
        connector.setEndY(p2.circle.getLayoutY());
        connector.setVisible(true);
    }

    public int[] trace () {
        String answer = "";
        int[] v = new int[4];
        Peg iter = first;
        int i = 0;
        while (iter != null && i < 4) {
            answer += iter.n;
            v[i] = iter.n;
            iter = iter.next;
            i++;
        }

        return v;
    }

    public String traceReformat (int[] v) {
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
        //Covert back to string that starts with min
        String s = "";
        for (int i = 0; i < 4; i++) {
            s+= v[index];
            if (index == 3) { index = 0; }
            else { index++; }
        }
        return s;
    }



}
