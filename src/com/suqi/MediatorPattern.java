package com.suqi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/20 11:24
 * @desc 中介者模式
 */
public class MediatorPattern {
    public static void main(String[] args) {
        MediatorImpl 好运来中介 = new MediatorImpl();
        Person 刘德华 = new Person("刘德华",61,Sex.MAN);
        Person 刘亦菲 = new Person("刘亦菲",30,Sex.WOMAN );
        Person 刘嘉玲 = new Person("刘嘉玲",56,Sex.WOMAN );

        好运来中介.register(刘德华);
        好运来中介.register(刘亦菲);
        好运来中介.register(刘嘉玲);

        刘德华.find(35);
    }
}
// 婚姻介绍所
abstract class Mediator {
    public abstract void register(Person person);
    public abstract void relay(Person person);
}
class Person{
    protected Mediator mediator; //挂在哪个介绍所
    protected String name;
    protected int age;
    protected Sex sex;
    protected int requestAge;

    public Person( String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    void find(int requestAge) {
        this.requestAge = requestAge;
        mediator.relay(this);
    }

}
class MediatorImpl extends Mediator{

    private final List<Person> personList = new ArrayList<>();

    @Override
    public void register(Person person) {
        personList.add(person);
        person.mediator = this;
    }
    @Override
    public void relay(Person person) {
        for (Person ren : personList){
            if (ren.sex!=person.sex && ren.age <= person.requestAge){
                System.out.println(person.name +"您好，"+ren.name+"今年"+ren.age+"与您匹配");
            }
        }
    }
}
enum Sex{
    MAN,WOMAN
}