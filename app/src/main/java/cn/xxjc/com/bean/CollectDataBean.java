package cn.xxjc.com.bean;

import java.util.List;

/**
 * 类描述:
 *
 * @author xull
 * @date 2017/7/14.
 */
public class CollectDataBean {
    /**
     * log_id : 848143615
     * direction : 0
     * words_result_num : 5
     * words_result : [{"words":" OFFICE"},{"words":" WRITING BOOK"},{"words":" he best quality and design is just for you"},{"words":" lou will feel like writing with it all the time"},{"words":" 8 mm"}]
     */

    private long log_id;
    private int direction;
    private int words_result_num;
    /**
     * words :  OFFICE
     */

    private List<WordsResultBean> words_result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<WordsResultBean> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResultBean> words_result) {
        this.words_result = words_result;
    }

    public static class WordsResultBean {
        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }
}
