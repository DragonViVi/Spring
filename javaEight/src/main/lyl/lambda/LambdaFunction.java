package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date:2018/11/26 0026 下午 3:55
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/11/26 0026 下午 3:55
 */
public class LambdaFunction {

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> rs = new ArrayList<>();
        for (T s : list) {
            rs.add(function.apply(s));
        }
        return rs;
    }

    public static void main(String[] args) {
        //输出字段的长度并重新封装成list数据
        List<Integer> l = map(Arrays.asList("lambdas", "in", "acction"), (String s) -> s.length());
        System.out.println(l);
    }


}
