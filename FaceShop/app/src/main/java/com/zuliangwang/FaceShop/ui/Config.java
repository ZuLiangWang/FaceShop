package com.zuliangwang.FaceShop.ui;

import com.zuliangwang.FaceShop.bean.FaceTemplateBean;

import java.util.ArrayList;

/**
 * Created by zuliangwang on 15/11/15.
 */
public class Config {
    public static ArrayList<FaceTemplateBean> templateList = new ArrayList<FaceTemplateBean>();


    public Config() {
        FaceTemplateBean mo1= new FaceTemplateBean();
        mo1.setLeft(0.39f);
        mo1.setRight(0.76f);
        mo1.setTop(0.4f);
        mo1.setBottom(0.71f);
        templateList.add(mo1);


    }

    public FaceTemplateBean getTemplateConfig(int position){
        return templateList.get(position);
    }
}
