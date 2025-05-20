package com.niccolo.memory.controller;

import com.niccolo.memory.model.Card;
import com.niccolo.memory.model.DeckOfCards;
import com.niccolo.memory.model.MemoryCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class MemoryGameController implements Initializable {

    @FXML
    private Label correctValueLabel;

    @FXML
    private Label guessesValueLabel;

    @FXML
    private FlowPane imagesFlowPane;

    @FXML
    private Button restartButton;

    @FXML
    private Label timer;

    private ArrayList<MemoryCard> cardsActive;
    private int numGuesses, numCorrect;
    private MemoryCard firstCardClicked, secondCardClicked;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeImageView();
        restart(null);
    }

    private void initializeImageView(){
        for(int i = 0; i < imagesFlowPane.getChildren().size(); i++){
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            imageView.setImage(Card.getBackOfCard());
            imageView.setUserData(i);

            imageView.setOnMouseClicked(event -> flipCard((int) imageView.getUserData()));
        }
    }

    @FXML
    private void restart(ActionEvent event){
        numGuesses = 0;
        numCorrect = 0;
        firstCardClicked = null;
        secondCardClicked = null;

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
    }

    private void flipCard(int index) {
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
            numGuesses++;
            secondCardClicked = cardsActive.get(index);
            imageView.setImage(secondCardClicked.getImage());
            checkMatch();
            updateLabels();
        }
    }

    private void flipWrongCards(){
        for(int i = 0; i < cardsActive.size(); i++){
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);

            if(!cardsActive.get(i).isMatched()){
                imageView.setImage(Card.getBackOfCard());
            }
        }
    }

    private void checkMatch(){
        if(firstCardClicked.isSameCard(secondCardClicked)){
            numCorrect++;
            firstCardClicked.setMatched(true);
            secondCardClicked.setMatched(true);
        }
        firstCardClicked = null;
        secondCardClicked = null;
    }

    private void updateLabels(){
        guessesValueLabel.setText(Integer.toString(numGuesses));
        correctValueLabel.setText(Integer.toString(numCorrect));
    }
}
