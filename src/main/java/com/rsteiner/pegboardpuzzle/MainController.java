package com.rsteiner.pegboardpuzzle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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

    private Pegboard pegboard;
    private SolutionGrid sGrid;

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
           System.out.println("\n" + reformatted);
           int qAnswer = sGrid.checkSolution(reformatted);
           if (qAnswer == 0) {
               qAnswer = sGrid.checkSolution(reformatted);
           }
           if (qAnswer > 0) {
               sGrid.displaySolution(qAnswer, reformatted);
           }
           return;
       }
    }

    @FXML
    public void quit(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    public void reset(MouseEvent event) {
        pegboard.reset();
    }

    @FXML
    public void pegClick(MouseEvent event) {
        if (event.getSource() instanceof Circle) {
            Circle circle = (Circle) event.getSource();
            int index = Character.getNumericValue(((Circle) event.getSource()).getId().charAt(1));
            int x = pegboard.press(index);
            if (x == -3) {
                System.out.println("Error loading peg");
            }
            if (x == -2) {
                System.out.println("You already presssed that peg!");
            }
            if (x == -1) {
                System.out.print("Error: You can only click on four pegs.");
            }
            else {
                    circle.setFill(javafx.scene.paint.Color.web("#375da8"));
                }
            }
        }

    }

