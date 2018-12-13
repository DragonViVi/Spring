package stream;

import java.util.ArrayList;
import java.util.List;

/**
 * 5.3 5.2 使用流
 */
public class Streams2 {
    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        menu.add(new Dish("beef", false, 701, Dish.Type.MEAT));
        menu.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        menu.add(new Dish("french fries", true, 530, Dish.Type.OTHER));
        menu.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        menu.add(new Dish("season fruit", true, 120, Dish.Type.OTHER));
        menu.add(new Dish("pizza", true, 550, Dish.Type.OTHER));
        menu.add(new Dish("prawns", false, 300, Dish.Type.FISH));
        menu.add(new Dish("salmon", false, 450, Dish.Type.FISH));

        /**
         * 移除集合中匹配到的数据
         */
        menu.removeIf(dish -> dish.getName().equals("pork"));
        menu.forEach(System.out::println);
        /**
         * Stream接口操作一个谓词（一个返回boolean的函数）作为参数，并返回一个包含啥偶有符合谓词的元素的流
         */

//        menu.stream().filter(Dish::isVegetarian).map(Dish::getCalories).forEach(System.out::println);

        /**
         * 截断流操作
         */
//        List<Integer> collect = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getCalories).limit(3).collect(learncollectors.toList());

        /**
         * 跳过流操作skip面多少个
         */
//        menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getCalories).skip(3).collect(learncollectors.toList()).forEach(System.out::println);

        /**
         * 转换为整数Int
         */
//        collect.stream().filter(integer -> integer % 2 == 0).distinct().forEach(System.out::println);

        /**
         * 映射元素操作
         */
//        List<String> collect1 = menu.stream().map(Dish::getName).collect(learncollectors.toList());

        /**
         * 映射字段长度
         */
//        List<Integer> collect2 = collect1.stream().map(String::length).collect(learncollectors.toList());
        /**
         * 整合上面两个方法
         */
//        menu.stream().map(Dish::getName).map(String::length).collect(learncollectors.toList()).forEach(System.out::println);

        /**
         * 使用flatMap
         *使用 flatMap 方法的效果是各个数组并不是分别映射成一个流，而是映射成流的内容。所
         *有使用 map(Arrays::stream) 时生成的单个流都被合并起来，即扁平化为一个流
         *
         * flatmap 方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接
         起来成为一个流
         */

//        List<String> stringList = Arrays.asList("Hello", "World");
//        List<String> collect = stringList.stream()
//                .map(s -> s.split(""))  //将每个单词转换为由字母构成的数组
//                .flatMap(Arrays::stream)  //将各个生成流扁平化单个流
//                .distinct().collect(learncollectors.toList());
//        collect.forEach(System.out::println);
//
//
//        List<String> stringList2 = Arrays.asList("a,b,c,d,e,f,g");
//        List<String> collect2 = stringList2.stream().map(s -> s.split(",")).flatMap(Arrays::stream).distinct().collect(learncollectors.toList());
//        collect2.forEach(System.out::println);

//
//        List<Integer> integerList = Arrays.asList(1, 2, 3);
//        List<Integer> integerList1 = Arrays.asList(3, 4);
//        List<int[]> collect1 = integerList.stream()
//                .flatMap(integer ->
//                        integerList1.stream()
//                                .filter(integer1
//                                        -> (integer + integer1) % 3 == 0)
//                                .map(integer1
//                                        -> new int[]{integer, integer1}))
//                .collect(learncollectors.toList());
//        collect1.forEach(System.out::println);
//
//
//        /**
//         * 查找和匹配
//         */
//        //a
// 方法可以回答 流中会否有个元素匹配给定的谓词
//        if (menu.stream().anyMatch(Dish::isVegetarian)) {
//            System.out.println("存在");
//        }
//        //allMatch 方法 流中的原始元素是否都能匹配给定的谓词
//        boolean b = menu.stream().allMatch(d -> d.getCalories() < 1000);
//
//        //noneMatch 可以确保流中元素没有任何元素与给定的谓词匹配
//        boolean b1 = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
//
//        //findAny 方法 将返回当前流中的任意元素   他可以与其他流操作结合使用
//        /**
//         *    ifPresent(Consumer<T> block) 会在值存在的时候执行给定的代码块。我们在第3章
//         *介绍了 Consumer 函数式接口；它让你传递一个接收 T 类型参数，并返回 void 的Lambda
//         * 表达式
//         */
//
//        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
//        /**
//         * isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false  判断是否存在 然后返回boolen值
//         */
//        boolean present = menu.stream().filter(Dish::isVegetarian).findAny().isPresent();
//        System.out.println(present);
//        /**
//         *  T get() 如果存在值 返回值 否则抛出异常
//         */
//        Dish dish = menu.stream().filter(Dish::isVegetarian).findAny().get();
//        System.out.println(dish);
//        /**
//         *  T orElse(T other) 会在值存在时返回值，否则返回一个默认值。
//         */
//        Dish dish1 = menu.stream().filter(Dish::isVegetarian).findAny().orElse(dish);
//        System.out.println(dish1);
//        /**
//         * 查找第一个元素
//         */
//        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        Integer integer = integerList.stream().findFirst().orElse(1);
//        System.out.println(integer);

        /**
         * 元素求和
         */

//        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        Integer reduce = integerList.stream().reduce(0, Integer::sum);
//        System.out.println(reduce);
//        //有初始值
//        Integer reduce1 = menu.stream().map(m -> m.getCalories()).reduce(0, Integer::sum);
//        System.out.println("reduce1  " + reduce1);
//        //无初始值
//        Integer integer = menu.stream().map(m -> m.getCalories()).reduce((a, b) -> (a + b)).get();
//        System.out.println(integer);
//        /**
//         * 最大值 最小值
//         */
//        Integer integer1 = menu.stream().map(m -> m.getCalories()).reduce(Integer::max).get();
//        System.out.println(integer1);
//
//        Integer integer2 = menu.stream().map(m -> m.getCalories()).reduce(Integer::min).get();
//        System.out.println(integer2);
//        /**
//         * 获取长度个数
//         */
//        long count = menu.stream().count();
//        System.out.println(count);
//
//        Integer reduce2 = menu.stream.parallelStream().map(m -> m.getCalories()).reduce(0, Integer::sum);
//        System.out.println(reduce2);
        /**
         * 排序
         */
//        menu.stream()
//                .map(Dish::getCalories)
//                .sorted()
//                .forEach(System.out::println);

    }

}
