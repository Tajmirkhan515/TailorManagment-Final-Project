package com.tajmirkhan.tailormanagment;

/**
 * Created by Tajmir khan on 10/14/2017.
 */

public class Design_Data {
    String id,name,flage;
    byte[] image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getFlage(){
        return flage;
    }
    public void setFlage(String flage){
        this.flage=flage;
    }
}
