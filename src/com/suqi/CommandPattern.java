package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 18:10
 * @desc 命令模式
 */
public class CommandPattern {
    public static void main(String[] args) {
        //首先声明调用者Invoker
        Invoker invoker = new Invoker();
        //定义接收者
        Receiver receiver = new Receiver();
        //定义一个发送给接收者的命令
        Command command = new ConcreteCommand(receiver);
        //把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();
    }

}

class Receiver { // 接收者
    public void doSomething() {
        //具体的业务逻辑
        System.out.println("........");
    }
}
class Invoker { // 调用者
    private Command command;

    // 设值注入
    public void setCommand(Command command) {
        this.command = command;
    }
    // 执行命令
    public void action() {
        this.command.execute();
    }
}
abstract class Command {
    // 每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}
class ConcreteCommand extends Command {
    // 维持一个对请求接收者对象的引用
    private Receiver receiver;

    //构造函数传递接收者
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        // 调用请求接收者的业务处理方法doSomething()
        receiver.doSomething();
    }
}

