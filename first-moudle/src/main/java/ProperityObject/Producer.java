package ProperityObject;/**
 * Created by Administrator on 2017/4/3.
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : Administrator
 **/
public class Producer extends Thread
{
    private Deque deque;

    ReentrantLock reentrantLock;

    Condition condition;

    public Producer(Deque deque, ReentrantLock reentrantLock, Condition condition)
    {
        this.deque = deque;
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                reentrantLock.lockInterruptibly();
                if (deque.size() < 10)
                {
                    sleep(1000);
                    deque.add("wall");
                    System.out.println("deque:" + deque.toString());
                    condition.signal();
                } else
                {
                    condition.await();
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args)
    {
        Deque<String> deque = new LinkedList<>();
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Producer producer = new Producer(deque, reentrantLock, condition);
        Consumer consumer = new Consumer(deque, reentrantLock, condition);
        producer.start();
        consumer.start();

    }
}
