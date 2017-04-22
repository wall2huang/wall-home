/**
 * Created by Administrator on 2017/4/4.
 */

import java.util.concurrent.Exchanger;

/**
 * author : Administrator
 **/
public class ExchangerThread extends Thread
{
    Exchanger<String> exchanger;

    String data ;

    public ExchangerThread(String name, Exchanger<String> exchanger, String data)
    {
        super(name);
        this.exchanger = exchanger;
        this.data = data;
    }

    @Override
    public void run()
    {
        while (true)
        {

            try
            {
                sleep(1000);
                System.out.println(Thread.currentThread().getName() + "我有：" + data);
                String exchange = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName() + "我交换到了:" + exchange);

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        Exchanger<String> objectExchanger = new Exchanger<>();
        new ExchangerThread("我是面包工人", objectExchanger, "面包").start();
        new ExchangerThread("我是商人", objectExchanger, "钱").start();
    }
}
