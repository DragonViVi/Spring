package stream;

import java.util.stream.Stream;

/**
 * Date:2018/12/6 0006 上午 9:42
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/6 0006 上午 9:42
 * <p>
 * 并行流并行处理数据
 * 并行流的性能分析
 * 分支和合并框架
 * 使用 Spliterator分隔流
 */
public class parallelStream {
    /**
     * 并行流 就是一个把内容分成多个数据块,并用不用的线程分别处理每个数据块的流。
     * 可以自动把给定操作的工作负荷分配给多个核心处理器的所有内核。
     *
     * @param args
     */
    public static void main(String[] args) {

        Stream.iterate(1L, i -> i + 1)//生成自然数无限流
                .limit(4) //限制到钱n个数
                .reduce(0L, Long::sum);

    }
}
