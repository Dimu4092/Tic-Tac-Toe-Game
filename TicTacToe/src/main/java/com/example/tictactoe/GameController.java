package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void handlePlayButtonClick() {
        // Запустити гру Tic Tac Toe
        TicTacToe game = new TicTacToe();
        game.start(new Stage());
    }
}
