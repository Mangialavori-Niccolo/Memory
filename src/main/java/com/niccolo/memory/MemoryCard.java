package com.niccolo.memory;

import lombok.Setter;

@Setter
public class MemoryCard extends Card{
    private boolean matched;

    public MemoryCard(Suit suit, FaceName faceName, boolean matched) {
        super(suit, faceName);
        this.matched = false;
    }

    public boolean isMatched(){
        return matched;
    }

    public boolean isSameCard(MemoryCard card){
        return this.equals(card);
    }


}
