/**
 * Created by Administrator on 2017/4/4.
 */

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.jar.Pack200;

/**
 * author : Administrator
 **/
public class PhaserThread extends Thread
{
    Phaser phaser;

    public PhaserThread(String name, Phaser phaser)
    {
        super(name);
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                switch (phaser.getPhase())
                {
                    case 0:
                        sleep((long) 1000 * new Random().nextInt(6));
                        System.out.println(Thread.currentThread().getName() + "做第一题");
                        break;
                    case 1:
                        sleep((long) 1000 * new Random().nextInt(6));
                        System.out.println(Thread.currentThread().getName() + "做第二题");
                        break;
                    case 2 :
                        sleep((long) 1000 * new Random().nextInt(6));
                        System.out.println(Thread.currentThread().getName() + "做第三题");
                        break;
                    default:
                        System.out.println(Thread.currentThread().getName() + "做完全全部所有题目");
                        phaser.arriveAndDeregister();
                        return ;
                }
                phaser.arriveAndAwaitAdvance();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        Phaser phaser = new Phaser(){

            @Override
            protected boolean onAdvance(int phase, int registeredParties)
            {
                System.out.println("所有人做完题号：" + (phase + 1));
                return false;
            }
        };
        for (int i = 1; i <=10 ; i++)
        {
            new PhaserThread("游客" + i, phaser).start();
        }
        if (phaser.isTerminated())
        {
            return ;
        }
    }
}
