package mySpliterator;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Date:2018/12/6 0006 下午 3:23
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/6 0006 下午 3:23
 */
public class SpliteratorStream {

    /**
     * Spliterator  可分迭代器
     * 可以遍历 数据源的元素
     * T是Spliterator 遍历的元素的类型
     * <p>
     * tryAdvance
     * <p>
     * trySplit
     */
//    public interface Spliterator<T> {
//        /**
//         * 方法类似普通的Iterator 因为它会顺序一个一个使用Spliterator的元素,并且如果还有其他元素要遍历就返回true。
//         */
//        boolean tryAdvance(Consumer<? super T> action);
//
//        /**
//         * 是专为Spliterator设计 ，可以它可以吧一些元素划分出去给第二个Spliterator（由该方法返回）让他们两个并行处理
//         */
//        Spliterator<T> trySplit();
//
//        /**
//         * 还可通过estimateSize 方法估计还剩下多少元素要遍历
//         */
//        long estimateSize();
//
//        /**
//         * 抽象方法：返回一个int,代表Spliterator本身特性集的编码
//         *
//         * @return
//         */
//        int characteristics();
//    }

    /**
     * Spliterator 拆分过程：
     * 第一步是对第一个Spliterator调用trysplit,然后会生成Spliterator,生成第二个Spliterator.
     * 第二步是对两个Spliterator调用trysplit。
     * 直到调用trysplit时候返回null，递归拆分则会终止。
     */


    /**
     * Spliterator的特性：
     * <p>
     * ORDERED       元素有既定的顺序（例如 List ），因此 Spliterator 在遍历和划分时也会遵循这一顺序
     * DISTINCT       对于任意一对遍历过的元素 x 和 y ， x.equals(y) 返回 false
     * SORTED      遍历的元素按照一个预定义的顺序排序
     * SIZED     该 Spliterator 由一个已知大小的源建立（例如 Set ），因此 estimatedSize() 返回的是准确值
     * NONNULL   保证遍历的元素不会为 null
     * IMMUTABLE  Spliterator 的数据源不能修改。这意味着在遍历时不能添加、删除或修改任何元素
     * CONCURRENT 该 Spliterator 的数据源可以被其他线程同时修改而无需同步
     * SUBSIZED     该 Spliterator 和所有从它拆分出来的 Spliterator 都是 SIZED
     */

    /**
     * 自定义实现Sprliteratoe
     *
     * @param args
     */
    public static void main(String[] args) {
        getStreamTest();
    }

    public static void getStreamTest() {
        final String SENTENCE =
                " 的数据 源 不能 修 改。这 意味 着在遍 历时不 能添加 、删除 或  修改 任何 元 素 ";
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }
    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();

    }
    /**
     * 一个迭代式字数统计方法
     *
     * @param s
     * @return
     */
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    public static void getTest() {
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Finde   " + countWordsIteratively(SENTENCE));
    }




}
