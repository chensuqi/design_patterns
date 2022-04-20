package com.suqi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suqi
 * @version 1.0
 * @date 2022/4/18 11:28
 * @desc 组合模式
 */
public class CombinationPattern {
    public static void main(String[] args) {
        ComCounter one = new ComCounter();
        ComCounter two1 = new ComCounter();
        ComCounter two2 = new ComCounter();
        ComCounter three = new ComCounter();

        one.add(two1);
        one.add(two2);
        two1.add(three);

        System.out.println(one.count());

    }

}
interface Counter{
    int count();
}
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
