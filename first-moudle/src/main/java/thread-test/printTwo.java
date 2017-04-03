/**
 * Created by Administrator on 2017/4/3.
 */

import ProperityObject.CalNumber;

import static java.lang.Thread.sleep;

/**
 * author : Administrator
 **/
public class printTwo implements Runnable
{
    private CalNumber calNumber;

    public printTwo(CalNumber calNumber)
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
                if (calNumber.getCount() == 2)
                {
                    try
                    {
                        sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
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

                    }
                }
            }
        }
    }
}
