package stream;

/**
 * Date:2018/12/5 0005 上午 11:14
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 上午 11:14
 */
public class Transaction {

    private  Trader trader;
    private  int year;
    private  int value;

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Transaction() {
    }

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
