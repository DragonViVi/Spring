package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * Date:2018/12/5 0005 上午 11:12
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 上午 11:12
 * <p>
 * 5.6 付诸实践
 * <p>
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 */
public class Streams3 {
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
        /**
         * 找出2011年发生的所有交易，并按交易额排序（从低到高）
         */
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        collect.forEach(System.out::println);

        /**
         * 找出2012年发生的所有交易，并按交易额排序（从高到低）
         */
        List<Transaction> collect1 = transactions.stream().filter(transaction -> transaction.getYear() == 2012).sorted(Comparator.comparing(Transaction::getValue).reversed()).collect(Collectors.toList());
        collect1.forEach(System.out::println);

        /**
         * 交易员都在哪些不同的城市工作过？
         */

        List<String> collect2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct() //只选择出互不相同的城市
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        /**
         * distinct 等于 .collect(toSet()
         */
        Set<String> collect3 = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(toSet());
        System.out.println(collect3);


        /**
         * 查找所有来自于剑桥的交易员，并按姓名排序。
         */

        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        cambridge.forEach(System.out::println);

        /**
         * 返回所有交易员的姓名字符串，按字母顺序排序。
         */

        List<String> collect4 = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.toList());
        collect4.forEach(System.out::println);

        /**
         * 将所有名字拼接成一条字符串
         */
        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> (a + b));
        System.out.println(reduce);


        /**
         * 将所有名字拼接成一条字符串  更加高效的方法
         */
        String collect5 = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(collect5);

        /**
         * 有没有交易员是在米兰工作的？
         */

        boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);


        /**
         * (6) 打印生活在剑桥的交易员的所有交易额。
         */
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
        /**
         * 所有交易中，最高的交易额是多少？
         */
        Integer integer = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        System.out.println(integer);


        Integer integer1 = transactions.stream().map(Transaction::getValue).reduce(Integer::min).orElse(0);
        System.out.println(integer1);
        //优化
        Optional<Transaction> min = transactions.stream().min(Comparator.comparing(Transaction::getValue));
    }
}
