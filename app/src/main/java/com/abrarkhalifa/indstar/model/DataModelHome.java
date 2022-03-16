package com.abrarkhalifa.indstar.model;

public class DataModelHome {
    int image,map,chat,downArrow;
    String garageName;

    public DataModelHome(int image, String garageName ,int rating, int map, int chat, int downArrow) {
        this.image = image;
        this.map = map;
        this.chat = chat;
        this.downArrow = downArrow;
        this.garageName = garageName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public int getDownArrow() {
        return downArrow;
    }

    public void setDownArrow(int downArrow) {
        this.downArrow = downArrow;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }
}

