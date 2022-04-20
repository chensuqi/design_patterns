package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/19 9:07
 * @desc 原型模式
 */
public class PrototypePattern {
    public static void main(String[] args) {
        People people = new People();
        People clone = (People) people.clone();
        System.out.println(clone);
    }

}
interface Clone{
    Clone clone();
}

class People implements Clone{

    private String name;
    private String age;

    public People() {
        this.name = "Name:"+Math.random();
        this.age = "age:"+Math.random();
    }
    private People(People people){
        this.name = people.name;
        this.age = people.age;
    }
    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }

    @Override
    public Clone clone() {
        return new People(this);
    }
}