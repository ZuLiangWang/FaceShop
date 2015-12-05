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
        mo1.setLeft(0.309f);
        mo1.setRight(0.762f);
        mo1.setTop(0.256f);
        mo1.setBottom(0.715f);
        templateList.add(mo1);


    }

    public FaceTemplateBean getTemplateConfig(int position){
        return templateList.get(position);
    }
}
