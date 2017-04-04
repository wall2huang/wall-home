package ProperityObject;/**
 * Created by Administrator on 2017/4/4.
 */

import java.util.ArrayList;

/**
 * author : Administrator
 **/
public class Room
{

    private String name;

    private ArrayList<String> threads = new ArrayList<>(20);

    public Room(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name + "正在占用的线程:" + threads.toString();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void enter(String thread)
    {
        threads.add(thread);
    }

    public void out(String thread)
    {
        threads.remove(thread);
    }

    public ArrayList<String> getThreads()
    {
        return threads;
    }

    public void setThreads(ArrayList<String> threads)
    {
        this.threads = threads;
    }
}
