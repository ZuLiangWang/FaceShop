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
        mo1.setLeft(0.237f);
        mo1.setRight(0.776f);
        mo1.setTop(0.223f);
        mo1.setBottom(0762.f);
        templateList.add(mo1);

        FaceTemplateBean mo2 = new FaceTemplateBean();
        mo2.setLeft(0.207f);
        mo2.setRight(0.824f);
        mo2.setTop(0.188f);
        mo2.setBottom(0.805f);
        templateList.add(mo2);

        FaceTemplateBean mo3 = new FaceTemplateBean();
        mo3.setLeft(0.170f);
        mo3.setRight(0.829f);
        mo3.setTop(0.170f);
        mo3.setBottom(0.829f);
        templateList.add(mo3);

        FaceTemplateBean mo4 = new FaceTemplateBean();
        mo4.setLeft(0.163f);
        mo4.setRight(0.736f);
        mo4.setTop(0.156f);
        mo4.setBottom(0.729f);
        templateList.add(mo4);

        FaceTemplateBean mo5 = new FaceTemplateBean();
        mo5.setLeft(0.131f);
        mo5.setTop(0.152f);
        mo5.setRight(0.521f);
        mo5.setBottom(0.855f);
        templateList.add(mo5);

        FaceTemplateBean mo6 = new FaceTemplateBean();
        mo6.setLeft(0.347f);
        mo6.setTop(0.092f);
        mo6.setRight(0.896f);
        mo6.setBottom(0.639f);
        templateList.add(mo6);

        FaceTemplateBean mo7 = new FaceTemplateBean();
        mo7.setLeft(0.464f);
        mo7.setTop(0.212f);
        mo7.setRight(0.891f);
        mo7.setBottom(0.848f);
        templateList.add(mo7);
    }

    public FaceTemplateBean getTemplateConfig(int position){
        return templateList.get(position);
    }
}
