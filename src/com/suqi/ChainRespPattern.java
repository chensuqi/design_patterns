package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 8:58
 * @desc 责任链模式
 */
public class ChainRespPattern {
    public static void main(String[] args) {
        ChainHandler handler = new ChainLeader();
        ChainBoss boss = new ChainBoss();
        handler.nextHandler = boss;

        handler.process(10);
    }
}
abstract class ChainHandler{
    protected ChainHandler nextHandler;
    public abstract void process(Integer in);
}
class ChainLeader extends ChainHandler{
    @Override
    public void process(Integer in) {
        if (in>0 && in <10){
            System.out.println("leader");
        }else {
            nextHandler.process(in);
        }
    }
}
class ChainBoss extends ChainHandler{
    @Override
    public void process(Integer in) {
        System.out.println("Boss");
    }
}
