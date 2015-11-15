package com.zuliangwang.FaceShop.bean;

import java.util.ArrayList;

/**
 * Created by zuliangwang on 15/10/16.
 */
public class DetectFaceModel {
    private String session_id ;
    private String image_id;
    private int image_width;
    private int image_height;
    private ArrayList<Face> face;
    private int errorcode;
    private String errormsg;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public ArrayList<Face> getFace() {
        return face;
    }

    public void setFace(ArrayList<Face> face) {
        this.face = face;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public int getImage_width() {
        return image_width;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
