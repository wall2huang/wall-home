/**
 * Created by Administrator on 2017/4/2.
 */

import entity.Account;

/**
 * author : Administrator
 **/
public class SynTest implements Runnable
{
    Account account;

    int oper = 1;

    public SynTest(Account account, int oper)
    {
        this.account = account;
        this.oper = oper;
    }

    @Override
    public void run()
    {
        if (oper == 1)
        {
            for (int i = 1; i <= 100; i++)
            {
                account.addAmount(Double.valueOf("1000"));
                try
                {
                    Thread.sleep(10);
                } catch (InterruptedException e)
                {

                }
            }

        } else
        {
            for (int i = 1; i <= 100; i++)
            {
                account.substructAmount(Double.valueOf("1000"));
                try
                {
                    Thread.sleep(10);
                } catch (InterruptedException e)
                {

                }
            }

        }
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public static void main(String[] args) throws InterruptedException
    {
        Account account = new Account(Double.valueOf("0"));
        SynTest synTest = new SynTest(account, 1);
        SynTest synTest1 = new SynTest(account, 2);
        Thread t1 = new Thread(synTest);
        Thread t2 = new Thread(synTest1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("T1金额:" + synTest.getAccount().getAmount());
        System.out.println("T2金额:" + synTest1.getAccount().getAmount());


    }
}
