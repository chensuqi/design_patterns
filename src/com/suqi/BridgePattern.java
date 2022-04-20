package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/20 9:17
 * @desc 桥接模式
 */
public class BridgePattern {
    public static void main(String[] args) {
        OrderCoffee orderCoffee = new OrderCoffee(new Milk());
        orderCoffee.orderCoffee();
    }
}
abstract class Coffee{
    protected ICoffeeAdditives additives;
    public Coffee(ICoffeeAdditives additives){
        this.additives=additives;
    }
    public abstract void orderCoffee();

}
class OrderCoffee extends Coffee{

    public OrderCoffee(ICoffeeAdditives additives) {
        super(additives);
    }
    @Override
    public void orderCoffee() {
        System.out.println("点了杯咖啡");
        this.additives.addSomething();
    }
}
interface ICoffeeAdditives {
    void addSomething();
}
//加奶
class Milk implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加奶");
    }
}
//加糖
class Sugar implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加糖");
    }
}