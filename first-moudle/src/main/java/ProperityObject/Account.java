package ProperityObject;/**
 * Created by Administrator on 2017/4/2.
 */

/**
 * author : Administrator
 **/
public class Account
{
    private Double amount;

    public Account(Double amount)
    {
        this.amount = amount;
    }

    public Account()
    {
        super();
    }

    public synchronized void addAmount(Double amount)
{

    this.amount = this.amount + amount;
}

    public synchronized void substructAmount(Double amount)
    {
        this.amount = this.amount - amount;
    }


    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
}
