/**
 * Created by Administrator on 2017/4/2.
 */

/**
 * author : Administrator
 **/
public class ExampleThread extends Thread
{
    @Override
    public void run()
    {
        while ( ! this.isInterrupted())
        {
            try
            {
                this.sleep(3000);
            } catch (InterruptedException e)
            {
                System.out.println("阻塞状态中收到了中断请求，开始响应中断");
                break;
            }
            System.out.println("我没有被终止正在运行着....");
        }
        System.out.println("run线程真正结束");
    }

    public static void main(String[] args) throws InterruptedException
    {
        ExampleThread exampleThread = new ExampleThread();
        exampleThread.start();

        Thread.sleep(10000);

        exampleThread.interrupt();

    }
}
