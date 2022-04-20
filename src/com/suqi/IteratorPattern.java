package com.suqi;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/20 10:49
 * @desc 迭代器模式
 */
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