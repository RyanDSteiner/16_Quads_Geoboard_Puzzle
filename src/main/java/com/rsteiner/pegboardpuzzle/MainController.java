package com.rsteiner.pegboardpuzzle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    private Button InstructionsButton, OptionsButton, QuitButton, SubmitButton, ResetButton, UndoButton;
    @FXML
    private Circle c1, c2, c3, c4, c5, c6, c7, c8, c9;
    @FXML
    private AnchorPane MainPegboard;
    @FXML
    private AnchorPane p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;
    @FXML
    private Line l1, l2, l3, l4;
    @FXML
    private Text EventText;

    private Pegboard pegboard;
    private SolutionGrid sGrid;
    private boolean[] shapesCompleted = new boolean[16];

    @FXML
    public void initialize() {
        Circle[] circles = { c1, c2, c3, c4, c5, c6, c7, c8, c9 };
        Line[] lines = { l1, l2, l3, l4 };
        pegboard = new Pegboard(circles, lines);
        AnchorPane[] panes = { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16 };
        sGrid = new SolutionGrid(panes);
    }

    @FXML
    public void submit(MouseEvent event) {
       if (pegboard.getCount() == 5) {
           int[] submission = pegboard.trace();
           String reformatted = pegboard.traceReformat(submission);
           int qAnswer = sGrid.checkSolution(reformatted);
           if (qAnswer == 0) {
               EventText.setText("This shape is not a valid quadrilateral. Press reset to try again.");
           }
           else if (qAnswer > 0 && !shapesCompleted[qAnswer-1]) {
               sGrid.displaySolution(qAnswer, submission);
               shapesCompleted[qAnswer-1] = true;
               if (isComplete(shapesCompleted)) {
                   EventText.setText("WOW! You found all 16 quadrilaterals! You are amazing!!!!");
               }
               else {
                   EventText.setText("You found quadrilateral #" + qAnswer + "! Congrats!");
               }
           }
           else {
               EventText.setText("This shape is congruent to quadrilateral #" + qAnswer + ", which you already found. Press reset to try again.");
           }
           return;
       }

       else {
           EventText.setText("This shape is not complete, you can't submit it yet.");
       }
    }

    @FXML
    public void quit(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    public void reset(MouseEvent event) {
        pegboard.reset();
        EventText.setText("");
    }

    @FXML
    public void undo(MouseEvent event) {
        pegboard.undo();
    }

    @FXML
    public void pegClick(MouseEvent event) {
        if (event.getSource() instanceof Circle) {
            Circle circle = (Circle) event.getSource();
            int index = Character.getNumericValue(((Circle) event.getSource()).getId().charAt(1));
            int x = pegboard.press(index);
            if (x == -4) {
                EventText.setText("You already finished drawing your shape! Click undo to go back, or click submit.");

            }
            if (x == -3) {
                EventText.setText("Loading error");
            }
            if (x == -2) {
                EventText.setText("You already pressed that peg!");
            }
            if (x == -1) {
                EventText.setText("You already clicked on four pegs, now connect back to the first one you clicked.");
            }
            else {
                    circle.setFill(javafx.scene.paint.Color.web("#375da8"));
                }
            }
        }

        public boolean isComplete (boolean[] shapes) {
            boolean complete = true;
            for (int i = 0; i < shapes.length; i++) {
                if (!shapes[i]) {
                    complete = false;
                }
            }
            return complete;
        }

    }

