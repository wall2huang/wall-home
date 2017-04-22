/**
 * Created by Administrator on 2017/4/13.
 */

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : Administrator
 **/
public class SynThread implements Runnable
{
    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static volatile int num = 0;

    @Override
    public void run()
    {
        try
        {
            reentrantLock.lockInterruptibly();
            Thread.sleep(new Random().nextInt(3) * 1000);
            num ++;
            System.out.println("现在的num为：" + num);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args)
    {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++)
        {
            threadPoolExecutor.execute(new SynThread());
        }
        HashMap hashMap = new HashMap();
        Map map = Collections.synchronizedMap(hashMap);
        TreeSet<Object> objects = new TreeSet<>();
    }
}
