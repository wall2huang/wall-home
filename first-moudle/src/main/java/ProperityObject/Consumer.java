package ProperityObject;/**
 * Created by Administrator on 2017/4/3.
 */

import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : Administrator
 **/
public class Consumer extends Thread
{
    private Deque deque;
    ReentrantLock reentrantLock ;
    Condition condition;

    public Consumer(Deque deque, ReentrantLock reentrantLock, Condition condition)
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
                if(deque.size() > 0)
                {
                    sleep(1000);
                    deque.removeLast();
                    System.out.println("消费deque:" + deque.toString());
                    condition.signal();
                }
                else
                {
                    condition.await();
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                reentrantLock.unlock();
            }
        }
    }
}
