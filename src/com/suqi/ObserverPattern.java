package com.suqi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 10:14
 * @desc 观察者模式-发布订阅模式
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Publish publish = new Publish();

        //订阅
        publish.subscription(new HexObserver());
        publish.subscription(new BinaryObserver());
        // 每次状态改变都会通知订阅者
        System.out.println("First state change: 15");
        publish.setStatus(15);

    }
}

class Publish {
    private List<Observer> observerList = new ArrayList<>();
    private Integer statusMessage;

    public Integer getStatus() { return statusMessage; }
    public void setStatus(Integer statusMessage) {
        this.statusMessage = statusMessage;
        notifyAllObservers();
    }

    public void remove(Observer observer){
        observerList.remove(observer);
    }
    public void subscription(Observer observer){
        observerList.add(observer);
    }
    public void notifyAllObservers(){
        observerList.forEach(temp ->{
            temp.execute(statusMessage);
        });
    }
}

abstract class Observer{
    public  abstract void execute(Integer status);
}
class BinaryObserver extends Observer{
    @Override
    public void execute(Integer message) {
        System.out.println( "Binary String:"+ Integer.toBinaryString( message ) );
    }
}
class HexObserver extends Observer{
    @Override
    public void execute(Integer message) {
        System.out.println( "Hex String: " + Integer.toHexString( message ).toUpperCase() );
    }
}
