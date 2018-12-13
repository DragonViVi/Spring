package stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Date:2018/12/5 0005 下午 1:04
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/5 0005 下午 1:04
 * <p>
 * 5.7 构建流
 */
public class Stream5 {

    public static void main(String[] args) {
        /**
         * 由值创建流
         *
         *可以接受任何数量的参数
         */
        Stream<String> stringStream = Stream.of("JAVA 8", "Lambdas", "In", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        /**
         * 由数组创建流
         */

        int[] numbers = {2, 3, 4, 5, 6, 7, 11};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        /**
         * 有文件生成流
         *
         * Files.line 得到一个流
         * 其中每个元素都是给定文件中的一行
         * 然后可以对line调用split方法将行拆分成单词。
         *  如何使用flatMap产生一个扁平的单词流
         *  而不是给每一行产生一个单词流
         */
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {//流会自动关闭
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()//删除重复项
                    .count();//查询有多少个不同的单词
        } catch (IOException e) {

        }
    }
}
