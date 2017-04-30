/**
 * Created by Administrator on 2017/4/4.
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * author : Administrator
 **/
public class CountDownLatchThread extends Thread
{

    CountDownLatch begin;

    CountDownLatch end;

    public CountDownLatchThread(String name, CountDownLatch begin, CountDownLatch end)
    {
        super(name);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run()
    {
        try
        {
            begin.await();
            sleep(1000 * new Random().nextInt(5));
            System.out.println(Thread.currentThread().getName() + "正在赛跑runing");
            end.countDown();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++)
        {
            new CountDownLatchThread("运动员" + i, begin, end).start();
        }
        System.out.println("比赛准备开始，预备！");
        begin.countDown();
        end.await();
        System.out.println("比赛已经结束，正在统计分数");
        sleep(1000);
        System.out.println("冠军是我哈哈哈");
    }
}
