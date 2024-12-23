package org.example;

import java.awt.*;

public class Champion {
    String name;
    String role;
    String link;
    Stats stats = new Stats();
    Build build = new Build();

    String counter1;
    String counter2;
    String counter3;


    public Champion(String name) {this.name = name;}
    public Champion(String name, String link) {this.name = name; this.link = link;}
    public String getName() {return name;}
    public void setCounter(String name, int n) {
        switch (n) {
        case 0:
        counter1 = name;
        break;
        case 1:
        counter2 = name;
        break;
        case 2:
        counter3 = name;
        break;
        }
    }
}
