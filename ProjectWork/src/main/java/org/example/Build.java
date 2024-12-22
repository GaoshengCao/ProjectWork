package org.example;

import java.util.ArrayList;
import java.util.List;

public class Build {
    String path;
    String keystone;
    String slot1;
    String slot2;
    String slot3;
    String secondarypath;
    String secondary1;
    String secondary2;
    String shard1;
    String shard2;
    String shard3;

    String summoner1;
    String summoner2;

    public Build() {}

    public void setRune(ArrayList<String> rune) {
        path = rune.get(0);
        keystone = rune.get(1);
        slot1 = rune.get(2);
        slot2 = rune.get(3);
        slot3 = rune.get(4);
        secondarypath = rune.get(5);
        secondary1 = rune.get(6);
        secondary2 = rune.get(7);
        shard1 = rune.get(8);
        shard2 = rune.get(9);
        shard3 = rune.get(10);
    }

    public void setsummoner(String spell1,String spell2) {
        this.summoner1 = spell1;
        this.summoner2 = spell2;
    }
}
