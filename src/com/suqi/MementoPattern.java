package com.suqi;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/20 9:48
 * @desc 备忘录模式
 */
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