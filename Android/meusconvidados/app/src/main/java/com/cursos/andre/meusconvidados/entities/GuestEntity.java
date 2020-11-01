package com.cursos.andre.meusconvidados.entities;

public class GuestEntity {

    private Integer id;
    private String name;
    private int confirmed;
    private String document;

    public GuestEntity() {
    }

    public GuestEntity(Integer id,String name,int confirmed) {
        this.id = id;
        this.name = name;
        this.confirmed = confirmed;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
