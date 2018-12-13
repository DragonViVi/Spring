package learnOptional;

import java.util.Optional;

/**
 * Date:2018/12/7 0007 下午 3:59
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/7 0007 下午 3:59
 */
public class Car {

    /**
     * 车可能进行了保险,也可能没进行保险
     */
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
