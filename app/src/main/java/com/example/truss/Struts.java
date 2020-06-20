package com.example.truss;

public class Struts {
    float feet;
    int quantity, side;
    float cost;

    public Struts(float feet, int quantity, int side) {
        this.feet = feet;
        this.quantity = quantity;
        this.side = side;
        this.cost = 0;
    }

    public void calculateAndStoreCost() {
        cost = feet * quantity * side;
    }
}
