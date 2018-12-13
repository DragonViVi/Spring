package stream;

/**
 * Date:2018/12/5 0005 上午 11:12
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 上午 11:12
 */
public class Trader {

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Trader() {
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
