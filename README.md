# design_patterns
学会23种设计模式，这一篇就够了

@[TOC](设计模式)

## 1、模板方法模式
> **官方**：定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
>  
> **理解**：如果你已知某一业务（toStudy）的大体执行步骤，比如早上必须学英文，但下午学啥不清楚，也就是不知某一步骤的具体逻辑（noon、afternoon）你可以将其抽象出来，让学生对象自己去安排。而钩子方法就是增加了一种选择，比如傍晚是否也一定要学习。
![在这里插入图片描述](https://img-blog.csdnimg.cn/74b2ddc6c59f42048c27452b44770db5.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java 
public class TemplateMethodPattern {
    public static void main(String[] args) {
        Student student = new Student();
        student.isDusk();
        student.toStudy();
    }
}
abstract class study{

    static boolean isDusk = false;
    // 钩子方法
    public void isDusk(){
        isDusk = true;
    }
    protected void morning(){
        System.out.println("早上学英语");
    };
    protected abstract void noon();
    protected abstract void afternoon();
    protected void dusk(){
        System.out.println("傍晚可能学生物");
    };
    // 具体业务模板
    public final void toStudy(){
        morning(); // 原定计划逻辑
        noon();    // 自身计划逻辑
        afternoon();
        if (isDusk){    // 钩子函数回调
            dusk();
        }
    }

}

class Student extends study{

    @Override
    protected void noon() {
        System.out.println("中午计划学数学");
    }
    @Override
    protected void afternoon() {
        System.out.println("下午计划学语文");
    }
}
```

## 2、代理模式
> **官方**：为其他对象提供一种代理以控制对这个对象的访问
> **理解**：增加权限的概念，我们要访问某个类的真实业务，通过一个代理人来实现对他使用，而代理人又可以执行自己的代理逻辑。例子Spring AOP 实现。
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/9ed2d25b3afe4420bd40a35a566c934f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
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
        realSubject.doWork(); //被代理逻辑
        System.out.println("是不是和AOP很像");
    }
}
```
## 3、装饰器模式
> **官方**：动态地给一个对象添加一些额外的功能。就增加功能来说，装饰器模式比生成子类更加灵活
> **理解**：装饰器模式强调自身功能的扩展，将原对象放到新的装饰器壳子里他就具备了新的功能，是代理模式的一个特殊应用。代理模式强调对代理过程的控制。
![在这里插入图片描述](https://img-blog.csdnimg.cn/af657dc095b24fc8aeab8e11cd62c29f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
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
    public void addedFunction() {// 这一步就和代理模式有点像
        super.operation();
        System.out.println("通过装饰器的新功能-辅助原有业务");
    }
    @Override
    protected void addedFunction2() {
        System.out.println("通过装饰器的新功能-不与原有挂钩");
    }
}
```
## 4、策略模式
>**官方**：定义一组算法，将每个算法都封装起来，并且使它们之间可以互换。策略模式让算法独立于使用它的客户而变化，也称为政策模式
>**理解**：依官方表述，计算机容器的具体执行逻辑（execute），是根据你传入的策略决定的。
>![在这里插入图片描述](https://img-blog.csdnimg.cn/9d4d80d3edec40a48bee925f244741ca.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_19,color_FFFFFF,t_70,g_se,x_16)
```java
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
```

## 5、状态模式
> **官方**：允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类。其别名为状态对象（Objects for States），状态模式是一种对象行为型模式。
> **理解**：当一个对象内在状态改变时允许其改变行为，这个对象看起来像改变了其类。换言之就是状态改变了，他因为当前状态能操作的作为也受到了新的约束
![在这里插入图片描述](https://img-blog.csdnimg.cn/63346a50856d4c2a999cb2c1e773bc38.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

> 状态模式较难理解，这里借鉴一个电梯例子进行解析（本人也是靠这个例子才真正理解)。
> 假设电梯的 开门、关门、运行、停止 都由我们手动按钮控制，那么针对电梯处于4个不同状态时，可以点击的4个按钮（行为）也会受到限制。而状态的维护是交由当前状态下的个个按钮去维护的。
![在这里插入图片描述](https://img-blog.csdnimg.cn/3241dbe74b704c468e6e8b155e81c872.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/965c96dabb6f449589b80eee50d086e3.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
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
//==========================以下代码只是举一反三==========================
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
```

## 6、责任链模式
> **官方**：一种处理请求的模式，它让多个处理器都有机会处理该请求，直到其中某个处理成功为止。责任链模式把多个处理器串成链，然后让请求在链上传递。
> **理解**：利用类似于递归的方式，只处理自身模块，无效关注整个流程，不符合处理条件就转交给下一级
![在这里插入图片描述](https://img-blog.csdnimg.cn/d2faa7d35dbd4e47bb8ead3f3ea9f736.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java 
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
```

## 7、适配器模式
> **官方**：将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
> **理解**:简单理解就是对已有接口进行适配，使得调用一个接口方法即可完成所有类型的接口适配
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/20424d05b3ae4098b25da9f17015d985.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
public class AdapterPattern {
    public static void main(String[] args) {
        MediaAdapter adapter = new MediaAdapter();
        adapter.play("mp4","abc.mp4");
        adapter.play("vlc","vlc123.vlc");
        adapter.play("mp3","mpc.mp3");
    }
}
interface MediaPlayer {
     void play(String audioType, String fileName);
}
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }
    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}

class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer = new VlcPlayer();
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
            advancedMusicPlayer.playMp4(fileName);
        } else {
            System.out.println("暂时没有适配播放器");
        }
    }
}
```
## 8、观察者模式
> **官方**：当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知依赖它的对象。观察者模式属于行为型模式
> **理解**：学过mq的就知道，这就是简单的发布-订阅模式，消费者订阅生产，生产者一有新的状态改变，就通知消费者。
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/45ec3d849ba34af4b09042ca9e45d782.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)


```java
public class ObserverPattern {
    public static void main(String[] args) {
        Publish publish = new Publish();

        //订阅
        publish.subscription(new HexObserver());
        publish.subscription(new BinaryObserver());
        // 每次状态改变都会通知订阅者
        System.out.println("First state change: 15");
        publish.setStatus(15);

    }
}

class Publish {
    private List<Observer> observerList = new ArrayList<>();
    private Integer statusMessage;

    public Integer getStatus() { return statusMessage; }
    public void setStatus(Integer statusMessage) {
        this.statusMessage = statusMessage;
        notifyAllObservers();
    }
    
    public void remove(Observer observer){
        observerList.remove(observer);
    }
    public void subscription(Observer observer){
        observerList.add(observer);
    }
    public void notifyAllObservers(){
        observerList.forEach(temp ->{
            temp.execute(statusMessage);
        });
    }
}

abstract class Observer{
    public  abstract void execute(Integer status);
}
class BinaryObserver extends Observer{
    @Override
    public void execute(Integer message) {
        System.out.println( "Binary String:"+ Integer.toBinaryString( message ) );
    }
}
class HexObserver extends Observer{
    @Override
    public void execute(Integer message) {
        System.out.println( "Hex String: " + Integer.toHexString( message ).toUpperCase());
    }
}
```
## 9、外观模式
> **官方**：隐藏系统的复杂性，要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。外观模式提供一个高层次的接口，使得子系统更易使用。
> **理解**：将多个子类的方法一同调用。
![在这里插入图片描述](https://img-blog.csdnimg.cn/9f0e3424aeba41cd94bad3e1407127fc.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_17,color_FFFFFF,t_70,g_se,x_16)
```java
public class FacadePattern {
    public static void main(String[] args) {
        Facade f = new Facade();
        f.method();
    }
}
//外观角色
class Facade {
    private SubSystem01 obj1 = new SubSystem01();
    private SubSystem02 obj2 = new SubSystem02();
    private SubSystem03 obj3 = new SubSystem03();
    public void method() {
        obj1.method1();
        obj2.method2();
        obj3.method3();
    }
}
//子系统角色
class SubSystem01 {
    public void method1() {
        System.out.println("子系统01的method1()被调用！");
    }
}
//子系统角色
class SubSystem02 {
    public void method2() {
        System.out.println("子系统02的method2()被调用！");
    }
}
//子系统角色
class SubSystem03 {
    public void method3() {
        System.out.println("子系统03的method3()被调用！");
    }
}
```
## 11、组合模式
> **官方**：又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。
> **理解**：对于某一类对象进行集合封装，每个对象都可以作为父节点，同时拥有子节点集合，然后执行递归实现套娃操作。
```java
class ComCounter implements Counter{
    private List<ComCounter> comCounters = new ArrayList<>();
    public void add(ComCounter comCounter){
        comCounters.add(comCounter);
    }
    public void remove(ComCounter comCounter){
        comCounter.remove(comCounter);
    }
    @Override
    public int count() {
        int sum = 1;
        for (ComCounter comCounter:comCounters) {
            sum +=comCounter.count();
        }
        return sum;
    }
}
```
## 12、工厂模式
> 简单工厂模式： 简单工厂模式结构比较简单，创建对象的任务由工厂类来完成。(不属于23种设计模式）
```java
 if ("Huawei".equalsIgnoreCase(type)) {
            return new HuaweiComputer();
        } else if ("Xiaomi".equalsIgnoreCase(type)) {
            return new XiaomiComputer();
        }
 ```
 > 工厂模式
 > **官方**：工厂方法模式定义一个创建对象的接口，让子类决定实例化那个类
 > **理解**：在简单工厂模式的基础上，对创建工厂也进行抽象，使得一个工厂只能创建一个一种类型产品
 > ![在这里插入图片描述](https://img-blog.csdnimg.cn/a304b9f67008475e88291a16239b7813.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
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
        System.out.println("Huawei Computer power on");
    }
    public void powerOff() {
        System.out.println("Huawei Computer power off");
    }
}

class XiaomiComputer implements Computer {
    public void powerOn() {
        System.out.println("Xiaomi Computer power on");
    }
    public void powerOff() {
        System.out.println("Xiaomi Computer power off");
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
```
## 13、抽象工厂模式
>**官方**：抽象工厂模式是提供一个接口，用于创建相关或者依赖对象的家族，而不需要明确指定具体类，违反开闭原则
>**理解**： 在工厂模式的基础上加了一层，工厂模式只能创建一大类，抽象工厂可以创建相同品牌下的不同品种工厂
>![在这里插入图片描述](https://img-blog.csdnimg.cn/f287fa542e904102ac0430ab0db3d19a.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
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
interface Computer {
    void powerOn();
    void powerOff();
}
abstract class DesktopComputer implements Computer {
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
abstract class NoteBookComputer implements Computer {
    public abstract void powerOn();
    public abstract void powerOff();
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
```
## 14、单例模式
>**官方**：定义就是确保某一个类只有一个实例，并且提供一个全局访问点
>**理解**:该类全局唯一实例，且有自己创建，即构造方法私有化，volatile防止指令重排，双检锁保证并发唯一。
>![在这里插入图片描述](https://img-blog.csdnimg.cn/ed65f37f96384c7ab754ac8a7bc6283e.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_14,color_FFFFFF,t_70,g_se,x_16)
```java
class Singleton{
    private volatile static Singleton singleton;
    private Singleton(){};
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```
## 15、享元模式
> **官方**：运行共享技术有效地支持大量细粒度对象的复用。
> **理解**：利用池化技术使得内存对象资源复用，注意需要分离出外部状态和内部状态，而且外部状态具有固有化的性质
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/348b4418cd9e40bbbf1e18a27bd5ba22.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
public class FlyweightPattern {
    public static void main(String[] args) {
        FlyWeightFactory flyWeightFactory = FlyWeightFactory.getFlyWeightFactory();

        MOMOFlyWeight moMOFlyWeight1 = flyWeightFactory.getMoMOFlyWeight();

        moMOFlyWeight1.ride("小明");
        MOMOFlyWeight moMOFlyWeight2 = flyWeightFactory.getMoMOFlyWeight();
        moMOFlyWeight2.ride("小红");
        moMOFlyWeight2.back();
        moMOFlyWeight1.ride("小王");
        moMOFlyWeight2.ride("小王");
    }
}
abstract class BikeFlyWeight{
    protected Integer state = 0; // 0是未使用 1是使用种

    abstract void ride(String name);
    abstract void back();

    public Integer getState(){
        return state;
    }
}

class MOMOFlyWeight extends BikeFlyWeight{
    private String bikeId;
    public MOMOFlyWeight(String bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    void ride(String name) {
        if (state == 0){
            state = 1;
            System.out.println(name + "开始使用" + bikeId);
        }else {
            System.out.println("无法使用");
        }
    }
    @Override
    void back() {
        state = 0;
        System.out.println(bikeId +"推出使用");
    }
}
class FlyWeightFactory{

    private static FlyWeightFactory instance = new FlyWeightFactory();
    private FlyWeightFactory(){
        for (int i = 0; i < 2; i++) {
            pool.add(new MOMOFlyWeight("NO"+i));
        }
    }
    private Set<MOMOFlyWeight> pool = new HashSet<>();

    public static FlyWeightFactory getFlyWeightFactory(){
        return instance;
    }
    public MOMOFlyWeight getMoMOFlyWeight(){
        for (MOMOFlyWeight momoFlyWeight:pool) {
            if (momoFlyWeight.state == 0 )
                return momoFlyWeight;
        }
        return null;
    }
}
```

## 15、命令模式
> **官方**:命令模式是一个行为设计模式，它可将请求转换为一个包含与请求相关的所有信息的独立对象。该转换让你能根据不同的请求将方法参数化、延迟请求执行或将其放入队列中，且能实现可撤销操作。
> **理解**：通过将命令操作信息装置到一个对象中，去执行调用具体的Receiver对象，实现解耦
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/87601ff1dcb34623a6f4b79f6acf1ced.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
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
```
## 16、原型模式
>**官方**：原型模式是用原型实例指定创建对象的种类，并且通过复制这些原型创建新的对象
>**理解**：当需要对某个进行复制，却又无法通过外部对其进行复制时（没有set() ，和有参构造）。
>![在这里插入图片描述](https://img-blog.csdnimg.cn/23b1b974450744219b5a980846d7d8d0.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
public class PrototypePattern {
    public static void main(String[] args) {
        People people = new People();
        People clone = (People) people.clone();
        System.out.println(clone);
    }
}
interface Clone{
    Clone clone();
}

class People implements Clone{

    private String name;
    private String age;

    public People() {
        this.name = "Name:"+Math.random();
        this.age = "age:"+Math.random();
    }
    private People(People people){
        this.name = people.name;
        this.age = people.age;
    }
    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }

    @Override
    public Clone clone() {
        return new People(this);
    }
}
```
## 17、建造者模式
> **官方**：建造模式是将一个复杂对象的构建与表示分离，使得同样的构建过程可以创建不同的表示。一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
> **理解**：如何将一个复杂对象赋值的问题，然后一步一步构建而成。它将变与不变相分离，即产品的组成部分是不变的，但每一部分是可以灵活选择的,内部Builder类的结构其实是偏模板化的了

> 简单版本
```java
public class BuilderPattern {
    public static void main(String[] args) {
        Car build = new Car.Builder()
                .setDoor("木门")
                .setShell("铁壳")
                .build();
        System.out.println(build);
    }
}
class Car{
    private String door;
    private String shell;
    private String wheel;

    public Car(Builder builder) {
        this.door = builder.door;
        this.shell = builder.shell;
        this.wheel = builder.wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "door='" + door + '\'' +
                ", shell='" + shell + '\'' +
                ", wheel='" + wheel + '\'' +
                '}';
    }

    static final class Builder{
        private String door;
        private String shell;
        private String wheel;

        public Builder setDoor(String door) {
            this.door = door;
            return this;
        }

        public Builder setShell(String shell) {
            this.shell = shell;
            return this;
        }

        public Builder setWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }
        public Car build(){
            return  new Car(this);
        }
    }
}
```
>规范版本
```java
public class BuilderPattern {
    public static void main(String[] args) {
        ConcreteDecorator1 concreteDecorator = new ConcreteDecorator1();
        ProjectManager projectManager = new ProjectManager(concreteDecorator);
        House decorate = projectManager.decorate();
        decorate.show();
    }
}
class House {
    private String wall;
    private String TV;
    private String sofa;

    public void setWall(String wall) {
        this.wall = wall;
    }
    public void setTV(String TV) {
        this.TV = TV;
    }
    public void setSofa(String sofa) {
        this.sofa = sofa;
    }
    public void show() {
        System.out.println(wall+"--" + TV +"--"+sofa);
    }
}
//抽象建造者：装修工人
abstract class DecoratorBuilder {
    //创建产品对象
    protected House product = new House();
    public abstract void buildWall();
    public abstract void buildTV();
    public abstract void buildSofa();
    //返回产品对象
    public House getResult() {
        return product;
    }
}
//具体建造者：具体装修工人1
class ConcreteDecorator1 extends DecoratorBuilder {
    public void buildWall() {
        product.setWall("w1");
    }
    public void buildTV() {
        product.setTV("TV1");
    }
    public void buildSofa() {
        product.setSofa("sf1");
    }
}
//指挥者：项目经理
class ProjectManager {
    private DecoratorBuilder builder;
    public ProjectManager(DecoratorBuilder builder) {
        this.builder = builder;
    }
    //产品构建与组装方法
    public House decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }
}
```
## 18、桥接模式
>**官方**：抽象部分和实现部分隔离开来，使得他们能够独立变化
>**理解**：桥接模式表达的主要意义其实是接口隔离的原则，即把本质上并不内聚的两种体系区别开来，使得它们可以松散的组合，而策略在解耦上还仅仅是某一个算法的层次，没有到体系这一层次。![在这里插入图片描述](https://img-blog.csdnimg.cn/5bfa63f1bc144d1abc00ccfcce344b5d.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
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
```
## 19、备忘录模式
> **官方**：在不破坏封装的前提下，捕获一个对象的内部状态
> **理解**：原始信息具备快照功能，容器存储原始记录的快照，恢复和存储通过中间memento承接。
![在这里插入图片描述](https://img-blog.csdnimg.cn/c817ac2851b14c5899c8035830dc4256.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
public class MementoPattern {
    public static void main(String[] args) {
        Document document = new Document();
        Store store = new Store();

        document.setMessage("第一条记录");
        store.add(document.save());
        document.print();
        document.setMessage("第二条记录");
        store.add(document.save());
        document.print();

        document.setMessage("第三条记录");
        document.print();
        System.out.println();
        document.resume(store.get());
        document.print();
    }
}
class Document{
    private String message;

    public BackUP save(){
        return new BackUP(message);
    }
    public void resume(BackUP backUP){
        this.message = backUP.getMessage();
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void print(){
        System.out.println(message);
    }
}
class BackUP{
    private String message;

    public BackUP(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
class Store {
    private Stack<BackUP> stack = new Stack<>();

    public void add(BackUP backUP){
        stack.add(backUP);
    }
    public BackUP get(){
        return stack.pop();
    }
}
```
## 20、迭代器模式
> **官方**：迭代子模式是提供一种方法顺序访问一个聚合对象中的各个元素，而不暴露其内部的表示
> **理解**：在原有的集合容器中，让其内部自行实现迭代器的子类，实现迭代功能,推荐点开ArrayList源码观看。
![在这里插入图片描述](https://img-blog.csdnimg.cn/16feffbd402f4d01846f29172e6aebba.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)

```java
public class IteratorPattern {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.Iterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
interface Iterator {
    boolean hasNext();
    Object next();
}
interface Container {
    Iterator Iterator();
}
class NameRepository implements Container {
    public String[] names = {"Robert" , "John" ,"Julie" , "Lora"};

    @Override
    public Iterator Iterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;
        @Override
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }
        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
```
## 21、中介者模式
> **官网**：中介者模式是一种行为设计模式，能让你减少对象之间混乱无序的依赖关系。该模式会限制对象之间的直接交互，迫使它们通过一个中介者对象进行合作。
> **理解**：中介者起到过度转发的作用，所有中介者必须有注册并存储所有容器，以及转发的功能，而用户类应当具备接受功能和存储归属那个中介者的功能。
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/8ad79c9568c84e0489337e035500ab50.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
public class MediatorPattern {
    public static void main(String[] args) {
        MediatorImpl 好运来中介 = new MediatorImpl();
        Person 刘德华 = new Person("刘德华",61,Sex.MAN);
        Person 刘亦菲 = new Person("刘亦菲",30,Sex.WOMAN );
        Person 刘嘉玲 = new Person("刘嘉玲",56,Sex.WOMAN );

        好运来中介.register(刘德华);
        好运来中介.register(刘亦菲);
        好运来中介.register(刘嘉玲);

        刘德华.find(35);
    }
}
// 婚姻介绍所
abstract class Mediator {
    public abstract void register(Person person);
    public abstract void relay(Person person);
}
class Person{
    protected Mediator mediator; //挂在哪个介绍所
    protected String name;
    protected int age;
    protected Sex sex;
    protected int requestAge;
    
    public Person( String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    void find(int requestAge) {
        this.requestAge = requestAge;
        mediator.relay(this);
    }

}
class MediatorImpl extends Mediator{
    
    private final List<Person> personList = new ArrayList<>();
    
    @Override
    public void register(Person person) {
        personList.add(person);
        person.mediator = this;
    }
    @Override
    public void relay(Person person) {
        for (Person ren : personList){
            if (ren.sex!=person.sex && ren.age <= person.requestAge){
                System.out.println(person.name +"您好，"+ren.name+"今年"+ren.age+"与您匹配");
            }
        }
    }
}
enum Sex{
    MAN,WOMAN
}
```
## 22、访问者模式
> **官方**：访问者模式，用于封装一些作用于某种数据结构中的各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
> **理解**：
>  - 抽象访问者需具备访问元素的重载方法，针对不同的元素种类个数都有对应的重载访问方法相对应。
>  - 抽象元素需要做的就是暴露一个accept供访问者调用，让实际的业务操作丢回访问者自身，当然它也可以定义自身的业务方法供访问者调用。
>  - 对象结构（原来的类对象）用于存储所有具体元素，以保证某一个访问者到来时，能够到各个元素(访问者可以选择重载某个元素时不执行逻辑，从而达到不操作的目的）.
>  最终通过不同访问者的不同，对所具备的元素加工也不同。
> ![在这里插入图片描述](https://img-blog.csdnimg.cn/9b21d7c370014bd68ef511711a55a8cb.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
public class VisitorPattern {
    public static void main(String[] args) {
        SetMaterial setMaterial = new SetMaterial();
        setMaterial.add(new glass());
        setMaterial.add(new Fish());

        setMaterial.accept(new Catering());
        System.out.println("=======");
        setMaterial.accept(new Aquarium());
    }
}
class SetMaterial{
    private List<Material> list = new ArrayList<>();
    public void accept(Company visitor){
        Iterator<Material> iterator = list.iterator();
        while (iterator.hasNext()){
            String accept = iterator.next().accept(visitor);
            System.out.println(accept);
        }
    }
    public void add(Material element) {
        list.add(element);
    }
    public void remove(Material element) {
        list.remove(element);
    }
}

//抽象元素：材料
interface Material {
    String accept(Company visitor);
}
//具体元素：鱼
class Fish implements Material {
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}
//具体元素：玻璃
class glass implements Material {
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}
//抽象访问者:公司
interface Company {
    String create(Fish element);
    String create(glass element);
}
//具体访问者：餐饮公司
class Catering implements Company {
    public String create(Fish element) {
        return "欢迎来到餐饮公司：清蒸鲈鱼";
    }
    public String create(glass element) {
        return "欢迎来到餐饮公司：高脚杯";
    }
}
//具体访问者：水族馆
class Aquarium implements Company {
    public String create(Fish element) {
        return "欢迎来到水族馆：锦鲤楼兰";
    }
    public String create(glass element) {
        return "欢迎来到水族馆：水晶宫";
    }
}
```
## 23、解释器模式
> **官方**：定义语言的文法，并且建立一个解释器来解释该语言中的句子
> **理解**：非终结符表达式主要作用其实是对语言进行分解，分解为最终解释器能够解析的格式，而最终解释器在环境类一开始创建时旧匹配好了规则内容，
> l![在这里插入图片描述](https://img-blog.csdnimg.cn/7b97e804a5f144f8846fd84e4707c3bc.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5LiA5rmrMTk1OQ==,size_20,color_FFFFFF,t_70,g_se,x_16)
```java
 public class InterpreterPattern {
    public static void main(String[] args) {
        InterpreterContext bus = new InterpreterContext();
        bus.freeRide("韶关的老人");
        bus.freeRide("韶关的年轻人");
        bus.freeRide("广州的妇女");
        bus.freeRide("广州的儿童");
        bus.freeRide("山东的儿童");
    }
}
//抽象表达式类
interface Expression {
    public boolean interpret(String info);
}
//终结符表达式类
class TerminalExpression implements Expression {
    private Set<String> set = new HashSet<String>();
    public TerminalExpression(String[] data) {
        for (int i = 0; i < data.length; i++) set.add(data[i]);
    }
    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        }
        return false;
    }
}
//非终结符表达式类
class AndExpression implements Expression {
    private Expression city = null;
    private Expression person = null;
    public AndExpression(Expression city, Expression person) {
        this.city = city;
        this.person = person;
    }
    public boolean interpret(String info) {
        String s[] = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}
//环境类
class InterpreterContext {
    private String[] citys = {"韶关", "广州"};
    private String[] persons = {"老人", "妇女", "儿童"};
    private Expression cityPerson;
    public InterpreterContext() {
        Expression city = new TerminalExpression(citys);
        Expression person = new TerminalExpression(persons);
        cityPerson = new AndExpression(city, person);
    }
    public void freeRide(String info) {
        boolean ok = cityPerson.interpret(info);
        if (ok) System.out.println("您是" + info + "，您本次乘车免费！");
        else System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
    }
}
```
