package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/15 11:48
 * @desc 模板方法模式-钩子模式
 */
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
