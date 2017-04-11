package com.meihaifeng.clone;


import com.meihaifeng.util.CloneUtil;

import java.io.Serializable;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/4/5
 * @description
 */
public class Person implements Serializable, Cloneable {

    /** 姓名 **/
    private String name;

    /** 电子邮件 **/
    private Email email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Person(String name,Email email){
        this.name  = name;
        this.email = email;
    }

    protected Person clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
//            person.setEmail(new Email(person.getEmail().getObejct(),person.getEmail().getContent()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }

    public static void main(String[] args) {
        //写封邮件
        Email email = new Email("请参加会议","请与今天12:30到二会议室参加会议...");

        Person person1 =  new Person("张三",email);
//        Person person2 =  person1.clone();
        Person person2 = CloneUtil.clone(person1);
        person2.setName("李四");
//        Person person3 =  person1.clone();
        Person person3 =  CloneUtil.clone(person1);
        person3.setName("王五");
        person1.getEmail().setContent("请与今天12:00到二会议室参加会议...");
        System.out.println(person1.getName() + "的邮件内容是：" + person1.getEmail().getContent()+person1);
        System.out.println(person2.getName() + "的邮件内容是：" + person2.getEmail().getContent()+person2);
        System.out.println(person3.getName() + "的邮件内容是：" + person3.getEmail().getContent()+person3);
    }
}
