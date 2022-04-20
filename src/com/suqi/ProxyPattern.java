package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/15 15:09
 * @desc 代理模式
 */
public class ProxyPattern {

    public static void main(String[] args) {
        new ProxySubject().doWork();
    }
}

interface Subject{
    void doWork();
}

class RealSubject implements Subject{
    @Override
    public void doWork() {
        System.out.println("实际执行工作");
    }
}
// 代理对象
class ProxySubject implements Subject{

    private RealSubject realSubject;
    public ProxySubject() {
        try {
            this.realSubject = (RealSubject)this.getClass().getClassLoader().loadClass("com.suqi.RealSubject").newInstance();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void doWork() {
        System.out.println("代理业务逻辑");
        realSubject.doWork();
        System.out.println("是不是和AOP很想");
    }
}