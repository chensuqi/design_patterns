package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/15 15:49
 * @desc 装饰器模式
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        new DecoratorImpl(new ComponentImpl()).operation();
        System.out.println();
        new DecoratorImpl(new ComponentImpl()).addedFunction();
        System.out.println();
        new DecoratorImpl(new ComponentImpl()).addedFunction2();
    }
}

interface Component{
    void operation();
}
class ComponentImpl implements Component{

    @Override
    public void operation() {
        System.out.println("ComponentImpl 具体原有实现功能");
    }
}

// 抽象装饰器壳子
abstract class Decorator implements Component{

    private Component component;
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
    // 可以加额外功能方法
    protected abstract void addedFunction();
    protected abstract void addedFunction2();
}
// 实现新增功能的装饰器
class DecoratorImpl extends Decorator{

    public DecoratorImpl(Component component) {
        super(component);
    }
    @Override
    public void operation() {
        super.operation();
    }
    @Override
    public void addedFunction() { // 这一步就和代理模式有点像
        super.operation();
        System.out.println("通过装饰器的新功能-辅助原有业务");
    }
    @Override
    protected void addedFunction2() {
        System.out.println("通过装饰器的新功能-不与原有挂钩");
    }
}
