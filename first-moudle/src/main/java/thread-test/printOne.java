/**
 * Created by Administrator on 2017/4/3.
 */

import ProperityObject.CalNumber;

import static java.lang.Thread.sleep;

/**
 * author : Administrator
 **/
public class printOne implements Runnable
{
    private CalNumber calNumber;

    public printOne(CalNumber calNumber)
    {
        this.calNumber = calNumber;
    }

    @Override
    public void run()
    {
        synchronized (calNumber)
        {
            while (true)
            {
                try
                {
                    sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (calNumber.getCount() == 1)
                {
                    System.out.println(1);
                    calNumber.setCount(2);
                    calNumber.notify();
                } else if (calNumber.getCount() == 2)
                {
                    System.out.println(2);
                    calNumber.setCount(1);
                    calNumber.notify();
                } else
                {
                    try
                    {
                        calNumber.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args)
    {
        CalNumber calNumber = new CalNumber(1);
        printOne printOne = new printOne(calNumber);
        printOne printTwo = new printOne(calNumber);
        new Thread(printOne, "print1").start();
        new Thread(printTwo, "print2").start();
    }
}
