package com.p5k.simple_rv.model;

public class Champ {
    final String name;
    final String imgUrl;
    final int cost;
    final int id;

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public Champ(String name, int cost, int id) {
        this.name = name;
        this.imgUrl = createImgUrl(name);
        this.cost = cost;
        this.id = id;
    }
    private String createImgUrl(String name){
        String newName = name.replace(" ","").replace("'","");
        return "https://rerollcdn.com/characters/Skin/5/"+newName+".png";
    }
}
