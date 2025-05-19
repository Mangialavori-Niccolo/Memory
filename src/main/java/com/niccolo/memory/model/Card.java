package com.niccolo.memory.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Card {
    private Suit suit;
    private FaceName faceName;

    public Card(Suit suit, FaceName faceName) {
        this.suit = suit;
        this.faceName = faceName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(suit, card.suit) && Objects.equals(faceName, card.faceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, faceName);
    }

    @Override
    public String toString() {
        return faceName + " of " + suit;
    }
}
