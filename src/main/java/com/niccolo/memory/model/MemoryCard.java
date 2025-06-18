package com.niccolo.memory.model;

import lombok.Setter;

@Setter
public class MemoryCard extends Card{
    private boolean matched;

    public MemoryCard(Suit suit, FaceName faceName) {
        super(suit, faceName);
        this.matched = false;
    }

    public boolean isMatched(){
        return matched;
    }

    public boolean isSameCard(MemoryCard card){
        if(this == card) return false;
        return this.equals(card);
    }


}
