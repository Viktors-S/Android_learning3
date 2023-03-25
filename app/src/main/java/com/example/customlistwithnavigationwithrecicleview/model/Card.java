package com.example.customlistwithnavigationwithrecicleview.model;

public class Card {

    private final int value;

    private int imageResource;

    private boolean isOpened;

    public Card(int value,int imageResource) {
        this.value = value;
        this.imageResource = imageResource;
        isOpened = false;
    }

    public int getValue(){return value;}

    public int getImageResource(){return imageResource;}

    public void setImageResource(int imageResource){this.imageResource = imageResource;}

    public boolean isOpened(){return isOpened;}

    public void setOpened(boolean isOpened){this.isOpened = isOpened;}
}
