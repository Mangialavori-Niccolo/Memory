package com.niccolo.memory.model;

import lombok.Getter;

public enum FaceName {
    ACE("ace"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king");

    @Getter
    private final String filenamePart;

    FaceName(String filenamePart) {
        this.filenamePart = filenamePart;
    }

    public String getFilenamePart() {
        return filenamePart;
    }
}
