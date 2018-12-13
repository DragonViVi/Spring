package mySpliterator;

/**
 * Date:2018/12/6 0006 下午 4:19
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/6 0006 下午 4:19
 */
public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 和迭代算法一样,accumulate 方法一个个遍历Character
     *
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    //上一个字符是空格,而当前遍历的字符不是空格,将单词计数器加一。
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    /**
     * 合并两个Word-Counter把计数器加起来
     *
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter,
                //仅需要技术器总和,无需要关心lastSpace
                wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
