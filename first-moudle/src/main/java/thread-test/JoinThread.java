/**
 * Created by Administrator on 2017/4/2.
 */

/**
 * author : Administrator
 **/
public class JoinThread extends Thread
{
    @Override
    public void run()
    {
        try
        {
            System.out.println("我是一个子线程，我必须等待一下");
            this.sleep(5000);
            System.out.println("子线程等待完了");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        JoinThread t1 = new JoinThread();
        JoinThread t2 = new JoinThread();
        System.out.println("t1子线程开启");
        t1.setDaemon(true);
        t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {

            }
        });
        t1.start();
        System.out.println("t2子线程开启");
        t2.start();
        /*t1.join();
        t2.join();*/

        System.out.println("主线程运行结束");

    }
}
