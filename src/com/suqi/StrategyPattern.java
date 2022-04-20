package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/15 16:42
 * @desc 策略模式
 */
public class StrategyPattern {
    public static void main(String[] args) {
        System.out.println(new Context(new AddStrategy()).execute(2,2));

        System.out.println(new Context((new subtractStrategy())).execute(2,2));
    }
}

// 计算机
class Context {

    private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int execute(int num1, int num2){
        return strategy.doOperation(num1,num2);
    }
}
// 算法策略
interface Strategy{
    int doOperation(int num1, int num2);
}
class AddStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        System.out.println("执行加法策略后等于");
        return num1 + num2;
    }
}
class subtractStrategy implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        System.out.println("执行减法策略后等于");
        return num1-num2;
    }
}
