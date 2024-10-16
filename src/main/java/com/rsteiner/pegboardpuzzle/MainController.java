package com.rsteiner.pegboardpuzzle;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    private Line l1, l2, l3, l4;

    private Pegboard pegboard;

    @FXML
    public void initialize() {
        pegboard = new Pegboard(c1, c2, c3, c4, c5, c6, c7, c8, c9, l1, l2, l3, l4);
    }

    @FXML
    public void submit(MouseEvent event) {
        String submission = pegboard.trace();
        System.out.println(submission);
        return;
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

