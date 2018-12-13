package lambda;

/**
 * Date:2018/11/27 0027 下午 2:48
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/11/27 0027 下午 2:48
 */
public class Apple {

    private String name;

    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple() {
    }
}
