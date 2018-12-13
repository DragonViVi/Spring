package stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Date:2018/12/5 0005 下午 12:25
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 下午 12:25
 * <p>
 * 5.6 数值流
 * <p>
 * Java 8引入了三个原始类型特化流接口来解决这个问题： IntStream 、 DoubleStream 和 LongStream ，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本。
 */
public class Streams4 {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 701, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        //计算总数
        Integer reduce = integerList.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        /**
         * 原始方法
         */
        Integer reduce1 = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        //----------------------映射到数值流------------------------------
        /**
         *  mapToInt  mapToDouble 和 mapToLong
         *  mapToInt 会从每道菜中提取热量（用一个 Integer 表示），并返回一个 IntStream
         * （而不是一个 stream<Integer> ）。然后你就可以调用 IntStream 接口中定义的 sum 方法，对卡
         *  路里求和了！请注意，如果流是空的， sum 默认返回 0 。 IntStream 还支持其他的方便方法，如
         *  max 、 min 、 average 等
         */
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);

        //----------------------转换为对象流------------------------------
        /**
         * 将Stream转换为数值流
         */
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        /**
         * 将数值流转换为Stream流
         */
        Stream<Integer> boxed = intStream.boxed();

        //----------------------默认值optionalInt------------------------------
        /**
         * 注意： optional类 这是一个可以表示值是否存在的容器
         * optional可以用Integer，String 等参考类型来参数化。
         * 对于三种原始流特化，也分别有一个optional原始类型特化版本
         * OptionalInt 、 OptionalDouble 和 OptionalLong
         * 求和的那个例子很容易，因为它有一个默认值： 0 。但是，如果你要计算 IntStream 中的最
         * 大元素，就得换个法子了，因为 0 是错误的结果。
         */
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        //如果没有最大值的话，显示提供一个默认的最大是
        int i = max.orElse(1);


        // 5.6.2  数值范围
        /**
         * 一个从1 到100的偶数流
         *
         * IntStream 和LongStream 中 range 和 rangeClosed 两个方法
         *
         * range（起始值，结束值） 不包括结束值
         * rangeClosed（起始值，结束值） 包括结束值
         */
        IntStream intStream1 = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(intStream1.count());

        IntStream intStream2 = IntStream.range(1, 100).filter(n -> n % 2 == 0);
        System.out.println(intStream2.count());
        List<Integer> collect = IntStream.range(1, 100).filter(n -> n % 2 == 0).boxed().collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}
