package com.p5k.simple_rv.model;

public class ChampDetail {
    public Champ getChamp() {
        return champ;
    }

    public String getNote() {
        return note;
    }

    final Champ champ;
    final String note;
    final int level;

    public int getLevel() {
        return level;
    }

    public ChampDetail(Champ champ, String note, int level) {
        this.champ = champ;
        this.note = note;
        this.level = level;
    }
}
