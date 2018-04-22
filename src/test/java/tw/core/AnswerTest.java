package tw.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    List<String> answerList = Arrays.asList("1", "2", "3", "4");
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        answer = new Answer();
        answer.setNumList(answerList);
    }

    @Test
    public void getIndexOfNumTest() {
        assertEquals(answer.getIndexOfNum("3"), 2);
    }

    @Test
    public void toStringTest() {
        assertEquals(answer.toString(), "1 2 3 4");
    }

}