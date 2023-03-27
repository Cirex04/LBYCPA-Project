package com.project.bookface;


import javafx.scene.image.Image;

public class Story {
    private String image;
    private String displayName;
    private String uid;
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Image getImage() {
        Image pic = new Image(image);
        return pic;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageFile(){
        return image;
    }
}
