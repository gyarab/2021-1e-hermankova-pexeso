package com.example.heeelp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent Vytvor(){
        Pane root = new Pane();
        root.setPrefSize(600,600);
        Tile tile = new Tile("A");
        root.getChildren().add(tile);
        return root;
    }

    private class Tile extends StackPane {

        public Tile (String value){
            Rectangle ram = new Rectangle(50,50);
            ram.setFill(null);
            ram.setStroke(Color.BLACK);
            Text text = new Text(value);
            text.setFont(Font.font(30));
            setAlignment(Pos.CENTER);
            getChildren().addAll(ram, text);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(Vytvor()));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
