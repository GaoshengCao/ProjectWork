package org.example;

import java.awt.*;

public class Champion {
    String name;
    String link;
    Stats stats = new Stats();
    Build build = new Build();

    String counter1;
    String counter2;
    String counter3;


    public Champion(String name) {this.name = name;}
    public Champion(String name, String link) {this.name = name; this.link = link;}
    public String getName() {return name;}
    public void setCounter(String name) {
        if (counter1 == null) { counter1 = name; }
        if (counter2 == null) { counter2 = name; }
        if (counter3 == null) { counter3 = name; }
    }
}
