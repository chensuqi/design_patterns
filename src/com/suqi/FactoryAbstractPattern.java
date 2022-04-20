package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 15:35
 * @desc 抽象工厂模式
 */
public class FactoryAbstractPattern {
    public static void main(String[] args) {
        HuaweiComputerFactory_ huaweiComputerFactory = new HuaweiComputerFactory_();
        Computer desktopComputer = huaweiComputerFactory.createDesktopComputer();
        desktopComputer.powerOn();
        desktopComputer.powerOff();
        Computer noteBookComputer = huaweiComputerFactory.createNoteBookComputer();
        noteBookComputer.powerOn();
        noteBookComputer.powerOff();

        XiaomiComputerFactory_ XiaomiComputerFactory = new XiaomiComputerFactory_();
        Computer desktopComputer1 = XiaomiComputerFactory.createDesktopComputer();
        desktopComputer1.powerOn();
        desktopComputer1.powerOff();
        Computer noteBookComputer1 = XiaomiComputerFactory.createNoteBookComputer();
        noteBookComputer1.powerOn();
        noteBookComputer1.powerOff();

    }
}
//interface Computer {
//    void powerOn();
//    void powerOff();
//}
abstract class DesktopComputer implements Computer {
    public abstract void powerOn();
    public abstract void powerOff();
}
abstract class NoteBookComputer implements Computer {
    public abstract void powerOn();
    public abstract void powerOff();
}
class HuaweiDesktopComputer extends DesktopComputer {
    @Override
    public void powerOn() {
        System.err.println("HuaweiDesktopComputer power on");
    }
    @Override
    public void powerOff() {
        System.err.println("HuaweiDesktopComputer power off");
    }
}
class XiaomiDesktopComputer extends DesktopComputer {
    @Override
    public void powerOn() {
        System.err.println("XiaomiDesktopComputer power on");
    }
    @Override
    public void powerOff() {
        System.err.println("XiaomiDesktopComputer power off");
    }
}
class HuaweiNoteBookComputer extends NoteBookComputer {
    @Override
    public void powerOn() {
        System.err.println("HuaweiNoteBookComputer power on");
    }
    @Override
    public void powerOff() {
        System.err.println("HuaweiNoteBookComputer power off");
    }
}
class XiaomiNoteBookComputer extends NoteBookComputer {
    @Override
    public void powerOn() {
        System.err.println("XiaomiNoteBookComputer power on");
    }
    @Override
    public void powerOff() {
        System.err.println("XiaomiNoteBookComputer power off");
    }
}

abstract class AbstractComputerFactory {
    public abstract Computer createDesktopComputer();
    public abstract Computer createNoteBookComputer();
}
class HuaweiComputerFactory_ extends AbstractComputerFactory {
    @Override
    public Computer createDesktopComputer() {
        return new HuaweiDesktopComputer();
    }

    @Override
    public Computer createNoteBookComputer() {
        return new HuaweiNoteBookComputer();
    }
}
class XiaomiComputerFactory_ extends AbstractComputerFactory {
    public Computer createDesktopComputer() {
        return new XiaomiDesktopComputer();
    }
    public Computer createNoteBookComputer() {
        return new XiaomiNoteBookComputer();
    }
}