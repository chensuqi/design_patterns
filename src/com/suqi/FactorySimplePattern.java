package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 15:09
 * @desc 简单工厂模式
 */
public class FactorySimplePattern {

    public static void main(String[] args) {
        Computer1 huawei = new ComputerFactory1().create("Huawei");
        huawei.powerOn();
        huawei.powerOff();
        Computer1 xiaomi = new ComputerFactory1().create("Xiaomi");
        xiaomi.powerOn();
        xiaomi.powerOff();
    }
}


/**
 *  电脑：开机，关机
 */
interface Computer1 {
    void powerOn();
    void powerOff();
}
class HuaweiComputer1 implements Computer1 {
    public void powerOn() {
        System.err.println("Huawei Computer power on");
    }
    public void powerOff() {
        System.err.println("Huawei Computer power off");
    }
}

class XiaomiComputer1 implements Computer1 {
    public void powerOn() {
        System.err.println("Xiaomi Computer power on");
    }
    public void powerOff() {
        System.err.println("Xiaomi Computer power off");
    }
}

class ComputerFactory1 {
    public Computer1 create(String type){
        if (type == null || type.length() == 0){
            return null;
        }
        if ("Huawei".equalsIgnoreCase(type)) {
            return new HuaweiComputer1();
        } else if ("Xiaomi".equalsIgnoreCase(type)) {
            return new XiaomiComputer1();
        }
        return null;
    }
}