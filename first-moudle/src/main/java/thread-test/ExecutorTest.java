/**
 * Created by Administrator on 2017/4/9.
 */

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * author : Administrator
 **/
public class ExecutorTest implements Runnable, Callable<String>
{
    @Override
    public void run()
    {

        try
        {
            Thread.sleep(1000 * new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName() + "子任务被执行了");
        } catch (InterruptedException e)
        {
        }

    }

    @Override
    public String call() throws Exception
    {
        int i = new Random().nextInt(6);
        Thread.sleep(i*1000);
        return "延时" + i + "秒后返回";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ArrayList<Callable<String>> runnables = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
           runnables.add((Callable)new ExecutorTest());
        }
        List<Future<String>> futures = executor.invokeAll(runnables);
        while (true)
        {
            Iterator<Future<String>> iterator = futures.iterator();
            while (iterator.hasNext())
            {
                Future<String> next = iterator.next();
                if (next.isDone())
                {
                    System.out.println(next.get());
                    iterator.remove();
                }
            }
        }

    }


}
