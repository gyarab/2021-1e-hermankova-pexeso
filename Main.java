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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    private static final int P = 8;
    private static final int R = 4;

    private Parent Vytvor(){
        Pane root = new Pane();
        root.setPrefSize(600,600);
        char c = 'A';
        List<K> tiles = new ArrayList<>();
        for(int i=0; i<P; i++){
            tiles.add(new K(String.valueOf(c)));
            tiles.add(new K(String.valueOf(c)));
            c++;
        }
        Collections.shuffle(tiles);
        for(int i=0; i<tiles.size(); i++){
            K tile = tiles.get(i);
            tile.setTranslateX(50*(i%R));
            tile.setTranslateY(50*(i/R));
            root.getChildren().add(tile);
        }
        return root;
    }

    private class K extends StackPane {

        public K (String value){
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
