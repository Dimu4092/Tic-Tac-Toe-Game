package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TicTacToe extends Application {
    private boolean isXTurn = true;
    private Button[][] buttons = new Button[3][3];
    private GridPane grid = new GridPane();
    private boolean gameActive = true;


    @Override
    public void start(Stage primaryStage) {
        grid.setStyle("-fx-background-color: #f0f0f0;");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setOnAction(e -> handleMove(button));
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMove(Button button) {
        if (!gameActive || !button.getText().isEmpty()) {
            return; // Ігнорувати, якщо гра завершена або клітинка вже зайнята
        }

        button.setText(isXTurn ? "X" : "O");
        button.setTextFill(isXTurn ? Color.BLACK : Color.BLUE);

        if (checkWinner()) {
            gameActive = false;
            showEndDialog(isXTurn ? "X has won!" : "O has won!");
        } else if (isBoardFull()) {
            showEndDialog("Draw!");
        } else {
            isXTurn = !isXTurn;
        }
    }

    private boolean checkWinner() {
        // Логіка перевірки на перемогу
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][0].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().isEmpty()) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[0][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().isEmpty()) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showEndDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(message);
        alert.showAndWait();
        resetBoard();
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        gameActive = true;
        isXTurn = true;
    }
}
