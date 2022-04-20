package com.suqi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/20 16:29
 * @desc 访问者模式
 */
public class VisitorPattern {
    public static void main(String[] args) {
        SetMaterial setMaterial = new SetMaterial();
        setMaterial.add(new glass());
        setMaterial.add(new Fish());

        setMaterial.accept(new Catering());
        System.out.println("=======");
        setMaterial.accept(new Aquarium());
    }
}
class SetMaterial{
    private List<Material> list = new ArrayList<>();
    public void accept(Company visitor){
        Iterator<Material> iterator = list.iterator();
        while (iterator.hasNext()){
            String accept = iterator.next().accept(visitor);
            System.out.println(accept);
        }
    }
    public void add(Material element) {
        list.add(element);
    }
    public void remove(Material element) {
        list.remove(element);
    }
}

//抽象元素：材料
interface Material {
    String accept(Company visitor);
}
//具体元素：鱼
class Fish implements Material {
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}
//具体元素：玻璃
class glass implements Material {
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}
//抽象访问者:公司
interface Company {
    String create(Fish element);
    String create(glass element);
}
//具体访问者：餐饮公司
class Catering implements Company {
    public String create(Fish element) {
        return "欢迎来到餐饮公司：清蒸鲈鱼";
    }
    public String create(glass element) {
        return "欢迎来到餐饮公司：高脚杯";
    }
}
//具体访问者：水族馆
class Aquarium implements Company {
    public String create(Fish element) {
        return "欢迎来到水族馆：锦鲤楼兰";
    }
    public String create(glass element) {
        return "欢迎来到水族馆：水晶宫";
    }
}