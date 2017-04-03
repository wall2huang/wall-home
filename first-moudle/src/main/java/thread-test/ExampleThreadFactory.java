/**
 * Created by Administrator on 2017/4/2.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * author : Administrator
 **/
public class ExampleThreadFactory implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("我是一个Thread");
    }

    public static void main(String[] args)
    {
        ThreadFactory threadFactory = new ThreadFactory()
        {
            List<String> names = new ArrayList<>();

            @Override
            public Thread newThread(Runnable r)
            {
                Thread thread = new Thread(r);
                names.add(thread.getName());
                return thread;
            }

            @Override
            public String toString()
            {
                System.out.println(names);
                return null;
            }
        };
        for (int i = 1; i <= 3; i++)
        {
            threadFactory.newThread(new ExampleThreadFactory());
        }
        threadFactory.toString();
    }
}
