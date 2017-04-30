/**
 * Created by Administrator on 2017/4/4.
 */

import entity.Room;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * author : Administrator
 **/
public class SemaphoreThread extends Thread
{

    private List<Room> list;

    private Semaphore semaphore;

    public SemaphoreThread(String name, List<Room> list, Semaphore semaphore)
    {
        super(name);
        this.list = list;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                semaphore.acquire();
                for (Room room : list)
                {
                    if (room.getThreads().size() == 0)
                    {
                        room.enter(Thread.currentThread().getName());
                        System.out.println(list.toString());
                        sleep(1000);
                        room.out(Thread.currentThread().getName());
                        break;
                    }
                }

            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                semaphore.release();
            }
        }

    }

    public static void main(String[] args)
    {
        List<Room> list = Arrays.asList(new Room("房间1"), new Room("房间2"), new Room("房间3"));
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i< 11; i++)
        {
            new SemaphoreThread("线程" + i, list, semaphore).start();
        }
    }
}
