package com.mycompany.app.domainmodel;

public class Obj1{
    public Obj1(String name){
        this.name = name;
    }

    private String name = "";
    public String getName(){
        return this.name;
    };
    public void setName(String name){
        this.name = name;
    }
}