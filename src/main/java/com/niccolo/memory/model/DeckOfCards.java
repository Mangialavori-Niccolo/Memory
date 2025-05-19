package com.niccolo.memory.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class DeckOfCards {
    private List<Card> cards;

    public DeckOfCards() {
        this.cards = new ArrayList<>();
        for(Suit s : Suit.values()){
            for(FaceName fn : FaceName.values()){
                cards.add(new Card(s, fn));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card dealTopCard() {
        if(!cards.isEmpty()){
            return cards.removeFirst();
        }
        else return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Card c : cards){
            sb.append(c).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckOfCards that = (DeckOfCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
