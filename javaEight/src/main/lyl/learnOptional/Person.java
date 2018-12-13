package learnOptional;

import java.util.Optional;

/**
 * Date:2018/12/7 0007 下午 3:58
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/7 0007 下午 3:58
 */
public class Person {
    /**
     * 人可能有车,也可能没车,因此将这个字段声明为Optional
     */
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
