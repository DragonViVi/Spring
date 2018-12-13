package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date:2018/11/26 0026 下午 3:38
 *
 * @author :liyunlong
 * @Description: java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，
 * 它接受泛型 T对象，并返回一个boolean。
 * 这恰恰和你先前创建的一样，现在就可以直接使用了。
 * 在你需要表示一个涉及类型T的布尔表达式时，就可以使用这个接口。比如，你可以定义一个接受String
 * 对象的Lambda表达式，
 * @UpdateDate: 2018/11/26 0026 下午 3:38
 */
public class LambdaPredicate {

    /**
     * @param <T>
     * @FunctionalInterface 标注标注该接口会设计成函数式接口
     * 如果定义了 但却不是函数接口 编译器会返回一个提示原因的错误，
     * Multiple non-overriding abstract methods found in interface Foo”，表明存在多个抽象方法。
     * 不是必需的，但是对于设计接口而言 他是比较好的做法
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }


    public static void main(String[] args) {

        //行为参数化  不为空的参数
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> noEmpty = filter(Arrays.asList("", "1", "2", "3", "4"), nonEmptyStringPredicate);
        System.out.println(noEmpty);


        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("苹果1", 250));
        apples.add(new Apple("苹果2", 150));
        apples.add(new Apple("苹果3", 22));




    }


}
