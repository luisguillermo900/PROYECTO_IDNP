package com.example.proyecto_idnp.Entidades;

public class RoomTestEntity {
    private int id;
    private String name;
    private String description;
    private int top;
    private int bottom;
    private int left;

    public int getId() {
        return id;
    }
    public RoomTestEntity(){}
    public RoomTestEntity(int id, String name, String description, int top, int bottom, int left, int right) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    private int right;
}
