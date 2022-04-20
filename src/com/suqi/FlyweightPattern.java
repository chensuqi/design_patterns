package com.suqi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 16:47
 * @desc 享元模式
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory(2);

        MOMOFlyWeight moMOFlyWeight1 = flyWeightFactory.getMoMOFlyWeight();

        moMOFlyWeight1.ride("小明");
        MOMOFlyWeight moMOFlyWeight2 = flyWeightFactory.getMoMOFlyWeight();
        moMOFlyWeight2.ride("小红");
        moMOFlyWeight2.back();
        moMOFlyWeight1.ride("小王");
        moMOFlyWeight2.ride("小王");

    }
}
abstract class BikeFlyWeight{
    protected Integer state = 0; // 0是未使用 1是使用种

    abstract void ride(String name);
    abstract void back();

    public Integer getState(){
        return state;
    }
}

class MOMOFlyWeight extends BikeFlyWeight{
    private String bikeId;
    public MOMOFlyWeight(String bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    void ride(String name) {
        if (state == 0){
            state = 1;
            System.out.println(name + "开始使用" + bikeId);
        }else {
            System.out.println("无法使用");
        }
    }
    @Override
    void back() {
        state = 0;
        System.out.println(bikeId +"推出使用");
    }
}
class FlyWeightFactory{

    public FlyWeightFactory(Integer num){
        for (int i = 0; i < num; i++) {
            pool.add(new MOMOFlyWeight("NO"+i));
        }
    }
    private final Set<MOMOFlyWeight> pool = new HashSet<>();

    public MOMOFlyWeight getMoMOFlyWeight(){
        for (MOMOFlyWeight momoFlyWeight:pool) {
            if (momoFlyWeight.state == 0 )
                return momoFlyWeight;
        }
        return null;
    }
}