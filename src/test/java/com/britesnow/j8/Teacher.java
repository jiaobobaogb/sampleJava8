package com.britesnow.j8;


/**
 * Created by Bao on 2014/9/29.
 */

public class Teacher {
    private String id;
    private String name;
    private String sex;
    private Double salary;
    private String description ;
    public Teacher(String id,String name,String sex){
        this.id = id ;
        this.name = name;
        this.sex = sex;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }

}
