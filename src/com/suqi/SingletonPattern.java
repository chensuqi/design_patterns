package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 16:17
 * @desc 单例模式
 */
public class SingletonPattern {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
class Singleton{
    private volatile static Singleton singleton;
    private Singleton(){};
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}