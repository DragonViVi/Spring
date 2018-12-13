package learncollectors;

import stream.Dish;
import stream.Trader;
import stream.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;


/**
 * Date:2018/12/5 0005 下午 2:06
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 下午 2:06
 * collection.stream().forEach() → collection.forEach()
 * collection.stream().collect(toList/toSet/toCollection()) → new CollectionType<>(collection)
 * collection.stream().toArray() → collection.toArray()
 * Arrays.asList().stream() → Arrays.stream() or Stream.of()
 * IntStream.range(0, array.length).mapToObj(idx -> array[idx]) → Arrays.stream(array)
 * IntStream.range(0, list.size()).mapToObj(idx -> list.get(idx)) → list.stream()
 * Collections.singleton().stream() → Stream.of()
 * Collections.emptyList().stream() → Stream.empty()
 * stream.filter().findFirst().isPresent() → stream.anyMatch()
 * stream.collect(counting()) → stream.count()
 * stream.collect(maxBy()) → stream.max()
 * stream.collect(mapping()) → stream.map().collect()
 * stream.collect(reducing()) → stream.reduce()
 * stream.collect(summingInt()) → stream.mapToInt().sum()
 * stream.mapToObj(x -> x) → stream.boxed()
 * stream.map(x -> {...; return x;}) → stream.peek(x -> ...)
 * !stream.anyMatch() → stream.noneMatch()
 * !stream.anyMatch(x -> !(...)) → stream.allMatch()
 * stream.map().anyMatch(Boolean::booleanValue) -> stream.anyMatch()
 */
public class Collectors {

    /**
     * learncollectors:收集器
     * <p>
     * Collection :征收;收集，采集;收藏品;募捐
     * <p>
     * collect ：收集
     * <p>
     * 看看你用 collect 和收集器能够做什么。
     *   对一个交易列表按货币分组，获得该货币的所有交易额总和（返回一个 Map<Currency,Integer> ）。
     *   将交易列表分成两组：贵的和不贵的（返回一个 Map<Boolean, List<Transaction>> ）。
     *   创建多级分组，比如按城市对交易分组，然后进一步按照贵或不贵分组（返回一个Map<Boolean, List<Transaction>> ）
     */
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
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
        /**
         * 根据目标参数进行分组
         *
         * groupingBy 生成一个map
         * groupingBy(Function<? super T, ? extends K> classifier) {return groupingBy(classifier, toList());}
         */
        Map<Integer, List<Transaction>> collect = transactions.stream()
                .collect(
                        //根据年份进行分组
                        groupingBy(Transaction::getYear)
                );
        System.out.println(collect);


        Long collect1 = menu.stream().count();

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> collect2 = menu.stream().collect(maxBy(dishComparator));


        /**
         * Collectors.summingLong 和 Collectors.summingDouble 方法的作用完全一样，可以用于求和字段为 long 或 double 的情况
         *
         *  可以计算数值的平均数    Collectors.averagingInt   averagingLong
         */
        /**
         * 求和
         */
        Integer collect3 = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(collect3);
        /**
         * 求平均数
         */
        Double collect4 = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(collect4);

//        Stream<String> s = Stream.of("3", "4", "5");
//        double ans = s
//                .collect(Collectors
//                        .averagingDouble(
//                                num -> Double.parseDouble(num)));
//        System.out.println(ans);


        String collect6 = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(collect6);


        /**
         * 提取列表中某个值 转为逗号分隔的字符串
         */
        String collect5 = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(collect5);

        /**
         * 用收集器--提取列表中某个值的综合
         * 第一个参数：归约操作的起始值 也就是流中若没有元素时的返回值，所以很显然对于数值
         *
         * 第二个元素： 提取对象中的值
         *
         * 第三个参数：BinaryOperator（二进制算子） 将两个项目累积成一个同类型的值
         *
         */
        Integer collect7 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(collect7);


        Integer collect8 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect8);


    }
}
