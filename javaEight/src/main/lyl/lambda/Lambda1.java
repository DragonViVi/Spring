package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * Date:2018/11/26 0026 下午 2:48
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/11/26 0026 下午 2:48
 */
public class Lambda1 {

    public static void main(String[] args) {
//        testPredicate();
        testSupplier();
//        testGrammaticalSugar();
    }


    /**
     * 布尔表达式
     * <p>
     * (List<String> list)->list.isEmpty()
     * <p>
     * Predicate<List<String>>
     */

    private static void testPredicate() {
        List<String> list = Arrays.asList("", "111", "222", "333");
        Predicate<String> predicate = (s) -> s.length() > 0;
        /**
         * true
         */
        boolean foo1 = predicate.test("foo");
        System.out.println(foo1);
        /**
         * false
         */
        boolean foo = predicate.negate().test("foo");
        System.out.println(foo);
    }


    /**
     * supplier 创建一个对象
     * <p>
     * ()->new Apple(10)
     * Supplier<Person> personSupplier = Person::new;
     * String firstName = personSupplier.get().firstName;
     */

    private static void testSupplier() {
        Supplier<Apple> appleSupplier = Apple::new;
        Apple apple = appleSupplier.get();
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("苹果1", 250));
        apples.add(new Apple("苹果2", 150));
        apples.add(new Apple("苹果3", 22));
        //提取重量大于150的苹果
        List<Apple> heavierThan150g = LambdaPredicate.filter(apples, a -> a.getWeight() > 11);
        //比较苹果的重量
//       apples.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
        heavierThan150g.sort(comparing(Apple::getWeight));
        heavierThan150g.sort(comparing(Apple::getWeight).reversed());
        System.out.println(apples);
        System.out.println(heavierThan150g);

        //语法糖

    }

    /**
     * 测试语法糖
     */
    private static void testGrammaticalSugar() {
        List<String> stringList = Arrays.asList("3", "4", "2", "1");
        stringList.sort(String::compareToIgnoreCase);
        System.out.println(stringList);

        Function<Integer, Apple> c2 = Apple::new;
        Apple apply = c2.apply(110);
        System.out.println(apply);

    }





    /**
     *    消费一个对象
     *    (Apple a)->System.out.println(a.getWeight)
     *   Consumer<Apple>
     */


    /**
     * 从提个对象中提取
     *
     * (String s)-s.length
     *  Function<String ,Integer>或者 ToIntFunction<String>
     */

    /**
     * 合并两个值
     * (int a,int b)->a*b
     * IntBinaryOperator
     */

    /**
     * 比较两个值
     * (Apple a1,Apple a2)-> a1.getWeight.compareTo(a2.getWeight))
     *
     * Comparator<Apple> 或者 BiFunction<Apple,Apple,Integer>或者 ToIntBiFunction<Apple,Apple>
     */



}
