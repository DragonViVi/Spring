package learnOptional;

import java.util.Optional;

/**
 * Date:2018/12/7 0007 下午 4:16
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/7 0007 下午 4:16
 */
public class OptionalStudy {
    private Insurance insurance;

    private Person person;

    private Car car;
    /**
     * 通过静态方法optional.empty创建一个空的Optional对象
     */
//    Optional<Car> getOptCar = Optional.empty();
    /**
     * 创建一个非空的car
     */
//    Optional<Car> optCar = Optional.of(Car car);

    /**
     * ，你可以创建一个允许null值的Optional对象
     */
//    Optional<Car> optCar = Optional.ofNullable(car);


    Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
    Optional<String> name = optInsurance.map(Insurance::getName);
}
