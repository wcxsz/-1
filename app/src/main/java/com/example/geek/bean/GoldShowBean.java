package com.example.geek.bean;

import java.io.Serializable;

/**
 * @author xts
 *         Created by asus on 2019/4/18.
 */

public class GoldShowBean implements Serializable{
    public String title;
    public boolean isChecked;
    public String cid;

    public GoldShowBean(String title, boolean isChecked, String cid) {
        this.title = title;
        this.isChecked = isChecked;
        this.cid = cid;
    }
}
