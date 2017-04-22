/**
 * Created by Administrator on 2017/4/4.
 */

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author : Administrator
 **/
public class CyclicBarrierThread extends Thread
{
    CyclicBarrier cyclicBarrier;

    public CyclicBarrierThread(String name, CyclicBarrier cyclicBarrier)
    {
        super(name);
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep((long)1000 * new Random().nextInt(5));
                System.out.println(Thread.currentThread().getName() + "集合完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("所有队员集合完毕！继续海起来");
            }
        });

        for (int i = 1; i <=10 ; i++)
        {
            new CyclicBarrierThread("游客" + i, cyclicBarrier).start();
        }
    }
}
