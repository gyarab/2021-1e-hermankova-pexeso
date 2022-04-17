package com.example.heeelp;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    //Vytvoření proměnných a jejich hodnot

    private static final int P = 8;
    private static final int R = 4;
    private K selected = null;
    private int pocetKliknuti = 2;

    /*
    Tato část kódu vytváří nová pole a kartičky pexesa.

    Nejdříve vztvoří "kartičky" a následně je ve smyčce
    rozdistribuuje na herní plochu neboli scénu hry.
     */

    private Parent vytvor(){
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

    //Tato část vytváří hlavní scénu a tím pádem také základní panel hry.

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(vytvor()));
        primaryStage.show();
     }

     //Tento celý blok kódu má na starost označení kartiček a všechny akce s nimi.


    private class K extends StackPane {
        private final Text text = new Text();

        /*
        Tento kousek kódu vypisuje na kartičky písmena podle jejich hodnoty získané výše
         a je zde zakódováno jakou barvou a jakou velikostí se kartičky nakonec zobrazí.
         */

        public K (String value){
            Rectangle ram = new Rectangle(50,50);
            ram.setFill(null);
            ram.setStroke(Color.BLACK);
            text.setText(value);
            text.setFont(Font.font(30));
            setAlignment(Pos.CENTER);
            getChildren().addAll(ram, text);
            setOnMouseClicked(this::handleMouseClicked);
            zavrit();
        }

        //Zde můžete vyčíst počet kliknutí a změny, které se po kliknutí s kartičkou stanou.

        public void handleMouseClicked(MouseEvent event){
            if(jeOtevreno() || pocetKliknuti == 0)
                return;
            pocetKliknuti--;

            if(selected==null){
                selected=this;
                otevrit(() -> {});
            }

            else {
                otevrit(() -> {
                    if(!maStejnouHodnotu(selected)){
                        selected.zavrit();
                        this.zavrit();
                    }
                    selected = null;
                    pocetKliknuti = 2;
                });
            }
        }

        //Toto zajišťuje, že když na kartičku kliknete a ta se "otočí", tak se zobrazí její hodnota.

        public boolean jeOtevreno(){
            return text.getOpacity()==1;
        }

        //Zde je funkce, která "otáčí" kartičky.

        public void otevrit(Runnable action){
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        //Zde naopak po otočení kartiček je tato funkce "otočí" zas zpátky.

        public void zavrit(){
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(0);
            ft.play();
        }

        //Tato funkce jen porovnává hodnotu kartiček.

        public boolean maStejnouHodnotu(K other){
            return text.getText().equals(other.text.getText());
        }
    }

    //A na závěr tato hlavní funkce celou aplikaci spouští.

    public static void main(String[] args) {
        launch(args);
    }
}