package com.niccolo.memory.controller;

import com.niccolo.memory.model.Card;
import com.niccolo.memory.model.DeckOfCards;
import com.niccolo.memory.model.MemoryCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.animation.AnimationTimer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;

public class MemoryGameController implements Initializable {

    @FXML
    private Label correctValueLabel;

    @FXML
    private Label guessesValueLabel;

    @FXML
    private FlowPane imagesFlowPane;

    @FXML
    private Label timer;


    private AnimationTimer animationTimer;
    private long startTime;
    private boolean timerStarted = false;

    private ArrayList<MemoryCard> cardsActive;
    private int numGuesses, numCorrect;
    private MemoryCard firstCardClicked, secondCardClicked;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeImageView();
        setUpTimer();
        restart(null);
    }

    private void initializeImageView(){
        for(int i = 0; i < imagesFlowPane.getChildren().size(); i++){
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            imageView.setImage(Card.getBackOfCard());
            imageView.setUserData(i);

            //Setto la funzione da chiamare ad ogni click su una carta
            imageView.setOnMouseClicked(event -> flipCard((int) imageView.getUserData()));
        }
    }


    private void setUpTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void start() {
                startTime = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                long enlapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(now - startTime);
                long minutes = enlapsedSeconds / 60;
                long seconds = enlapsedSeconds % 60;

                timer.setText(String.format("%02d:%02d", minutes, seconds));
            }
        };
    }


    @FXML
    private void restart(ActionEvent ignoredEvent){
        //Azzero le variabili
        numGuesses = 0;
        numCorrect = 0;
        firstCardClicked = null;
        secondCardClicked = null;

        //Creo le nuove carte attive in gioco e le mescolo
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        cardsActive = new ArrayList<>();
        for(int i = 0; i < imagesFlowPane.getChildren().size()/2; i++){
            Card c = deck.dealTopCard();
            cardsActive.add(new MemoryCard(c.getSuit(), c.getFaceName()));
            cardsActive.add(new MemoryCard(c.getSuit(), c.getFaceName()));
        }
        Collections.shuffle(cardsActive);
        flipWrongCards();
        updateLabels();

        resetTimer();
    }


    private void resetTimer() {
        animationTimer.stop();
        timer.setText("00:00");
        timerStarted = false;
    }


    /**
     * Viene chiamato ogni volta che si clicca su una carta
     */
    private void flipCard(int index) {
        //Al primo click, faccio partire il timer
        if(!timerStarted){
            animationTimer.start();
            timerStarted = true;
        }

        MemoryCard card = cardsActive.get(index);
        if(card.isMatched()) return;


        ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(index);

        if(firstCardClicked == null && secondCardClicked == null){
            flipWrongCards();
        }

        if(firstCardClicked == null){
            firstCardClicked = cardsActive.get(index);
            imageView.setImage(firstCardClicked.getImage());
        }
        else if (secondCardClicked == null){
            if(cardsActive.get(index) == firstCardClicked) return;
            numGuesses++;
            secondCardClicked = cardsActive.get(index);
            imageView.setImage(secondCardClicked.getImage());
            checkMatch();
            updateLabels();
        }
    }

    /**
     * Gira tutte le carte che hanno la variabile isMatched = false
     */
    private void flipWrongCards(){
        for(int i = 0; i < cardsActive.size(); i++){
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);

            if(!cardsActive.get(i).isMatched()){
                imageView.setImage(Card.getBackOfCard());
            }
        }
    }

    /**
     * Controlla se le due carte cliccate sono accoppiate
     */
    private void checkMatch(){
        if(firstCardClicked.isSameCard(secondCardClicked)){
            numCorrect++;
            firstCardClicked.setMatched(true);
            secondCardClicked.setMatched(true);
        }
        firstCardClicked = null;
        secondCardClicked = null;

        if(numCorrect == imagesFlowPane.getChildren().size()/2){
            animationTimer.stop();
        }
    }

    /**
     * Aggiuorna i valori delle label Guesses e Corrects
     */
    private void updateLabels(){
        guessesValueLabel.setText(Integer.toString(numGuesses));
        correctValueLabel.setText(Integer.toString(numCorrect));
    }
}
