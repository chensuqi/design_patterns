package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/19 10:03
 * @desc 建造者模式
 */
public class BuilderPattern {
    public static void main(String[] args) {
        ConcreteDecorator1 concreteDecorator = new ConcreteDecorator1();
        ProjectManager projectManager = new ProjectManager(concreteDecorator);
        House decorate = projectManager.decorate();
        decorate.show();

        Car build = new Car.Builder()
                .setDoor("木门")
                .setShell("铁壳")
                .build();
        System.out.println(build);
    }
}

class House {
    private String wall;
    private String TV;
    private String sofa;

    public void setWall(String wall) {
        this.wall = wall;
    }
    public void setTV(String TV) {
        this.TV = TV;
    }
    public void setSofa(String sofa) {
        this.sofa = sofa;
    }
    public void show() {
        System.out.println(wall+"--" + TV +"--"+sofa);
    }
}
//抽象建造者：装修工人
abstract class DecoratorBuilder {
    //创建产品对象
    protected House product = new House();
    public abstract void buildWall();
    public abstract void buildTV();
    public abstract void buildSofa();
    //返回产品对象
    public House getResult() {
        return product;
    }
}
//具体建造者：具体装修工人1
class ConcreteDecorator1 extends DecoratorBuilder {
    public void buildWall() {
        product.setWall("w1");
    }
    public void buildTV() {
        product.setTV("TV1");
    }
    public void buildSofa() {
        product.setSofa("sf1");
    }
}
//指挥者：项目经理
class ProjectManager {
    private DecoratorBuilder builder;
    public ProjectManager(DecoratorBuilder builder) {
        this.builder = builder;
    }
    //产品构建与组装方法
    public House decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }
}








class Car{
    private String door;
    private String shell;
    private String wheel;

    public Car(Builder builder) {
        this.door = builder.door;
        this.shell = builder.shell;
        this.wheel = builder.wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "door='" + door + '\'' +
                ", shell='" + shell + '\'' +
                ", wheel='" + wheel + '\'' +
                '}';
    }

    static final class Builder{
        private String door;
        private String shell;
        private String wheel;

        public Builder setDoor(String door) {
            this.door = door;
            return this;
        }

        public Builder setShell(String shell) {
            this.shell = shell;
            return this;
        }

        public Builder setWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }
        public Car build(){
            return  new Car(this);
        }
    }
}