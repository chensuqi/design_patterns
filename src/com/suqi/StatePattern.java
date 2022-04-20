package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/15 17:26
 * @desc 状态模式
 */
public class StatePattern {
    public static void main(String[] args) {
        StateContext stateContext = new StateContext();
        stateContext.setLiftState(new OpenningState());
        // 默认电梯门为开启状态
        stateContext.close();
        stateContext.run();
        stateContext.stop();
        stateContext.open();
    }
}

abstract class LiftState{

    //定义一个环境角色，也就是封装状态的变化引起的功能变化
    protected StateContext context;
    public void setContext(StateContext _context) {
        this.context = _context;
    }

    //电梯开门动作
    public abstract void open();
    //电梯关门动作
    public abstract void close();
    //电梯运行动作
    public abstract void run();
    //电梯停止动作
    public abstract void stop();
}

class StateContext {
    //定义出所有的电梯状态
    public final static OpenningState openningState = new OpenningState();//开门状态，这时候电梯只能关闭
    public final static ClosingState closeingState = new ClosingState();//关闭状态，这时候电梯可以运行、停止和开门
    public final static RunningState runningState = new RunningState();//运行状态，这时候电梯只能停止
    public final static StoppingState stoppingState = new StoppingState();//停止状态，这时候电梯可以开门、运行

    //定义一个当前电梯状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return this.liftState;
    }

    public void setLiftState(LiftState liftState) {
        //当前环境改变
        this.liftState = liftState;
        //把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }
    public void close() {
        this.liftState.close();
    }
    public void run() {
        this.liftState.run();
    }
    public void stop() {
        this.liftState.stop();
    }
}

class OpenningState extends LiftState{

    //开启当然可以关闭了，我就想测试一下电梯门开关功能
    @Override
    public void open() {
        System.out.println("电梯门已开启...");
    }
    @Override
    public void close() {//虽然可以关门，但这个动作不归我执行
        //状态修改
        super.context.setLiftState(StateContext.closeingState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().close();
    }
    @Override
    public void run() {
        System.out.println("按钮无法使用。。。。");
    }
    @Override
    public void stop() {
        System.out.println("按钮无法使用。。。。");
    }
}
class ClosingState extends LiftState{

    //关闭状态，这时候电梯可以运行、停止和开门
    @Override
    public void open() {
        //状态修改
        super.context.setLiftState(StateContext.openningState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().open();
    }
    @Override
    public void close() {
        System.out.println("电梯门已关门...");
    }
    @Override
    public void run() {
        //状态修改
        super.context.setLiftState(StateContext.runningState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().run();
    }
    @Override
    public void stop() {
        //状态修改
        super.context.setLiftState(StateContext.stoppingState);
        //动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().stop();
    }
}
class RunningState extends LiftState{

    //运行状态，这时候电梯只能停止
    @Override
    public void open() {
        System.out.println("按钮无法使用。。。。");
    }
    @Override
    public void close() {
        System.out.println("按钮无法使用。。。。");
    }

    //电梯门不能开着就跑，这里什么也不做
    @Override
    public void run() {
        System.out.println("电梯门运行中...");
    }
    //电梯运行只能执行停止
    @Override
    public void stop() {
        //状态修改
        super.context.setLiftState(StateContext.stoppingState);
        super.context.getLiftState().stop();
    }
}
class StoppingState extends LiftState{

    //停止状态，这时候电梯可以开门、运行
    @Override
    public void open() {
        //状态修改
        super.context.setLiftState(StateContext.openningState);
        super.context.getLiftState().open();
    }
    @Override
    public void close() {//虽然可以关门，但这个动作不归我执行
        System.out.println("按钮无法使用。。。。");
    }
    @Override
    public void run() {
        //状态修改
        super.context.setLiftState(StateContext.runningState);
        super.context.getLiftState().run();
    }
    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}