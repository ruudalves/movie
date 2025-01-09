package com.xpand.xpandit.domain;

public enum Ranks {
    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10);

    private int value;

    Ranks(int i) {
        this.value = i;
    }

    void Rank(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
