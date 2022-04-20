package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 15:09
 * @desc 工厂模式
 */
public class FactoryPattern {

    public static void main(String[] args) {
        Computer computer = new HuaweiComputerFactory().create();
        computer.powerOn();
        computer.powerOff();
        Computer computer1 = new XiaomiComputerFactory().create();
        computer1.powerOn();
        computer1.powerOff();

    }
}

/**
 *  电脑：开机，关机
 */
interface Computer {
    void powerOn();
    void powerOff();
}
class HuaweiComputer implements Computer {
    public void powerOn() {
        System.err.println("Huawei Computer power on");
    }
    public void powerOff() {
        System.err.println("Huawei Computer power off");
    }
}

class XiaomiComputer implements Computer {
    public void powerOn() {
        System.err.println("Xiaomi Computer power on");
    }
    public void powerOff() {
        System.err.println("Xiaomi Computer power off");
    }
}

abstract class ComputerFactory {
    protected abstract Computer create();
}
class HuaweiComputerFactory extends ComputerFactory {
    protected Computer create() {
        return new HuaweiComputer();
    }
}
class XiaomiComputerFactory extends ComputerFactory {
    protected Computer create() {
        return new XiaomiComputer();
    }
}


