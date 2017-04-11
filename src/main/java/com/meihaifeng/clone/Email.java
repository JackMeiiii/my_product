package com.meihaifeng.clone;

import java.io.Serializable;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/4/5
 * @description
 */
public class Email implements Serializable,Cloneable{

   private String obejct;

   private String content;

    public String getObejct() {
        return obejct;
    }

    public void setObejct(String obejct) {
        this.obejct = obejct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Email(String obejct,String content){
        this.content= content;
        this.obejct = obejct;
    }

    public Email clone() {
        Email email = null;
        try {
              email = (Email) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return email;
    }


}
