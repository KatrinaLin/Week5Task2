package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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


    @Test
    public void checkTest1() {
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("2", "6", "3", "8"));
        Record record = answer.check(userAnswer);   // 1A1B

        assertTrue(record.getValue()[0] == 1 && record.getValue()[1] == 1);
    }

    @Test
    public void checkTest2() {
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("5", "6", "7", "8"));
        Record record = answer.check(userAnswer);   // 0A0B

        assertTrue(record.getValue()[0] == 0 && record.getValue()[1] == 0);
    }

    @Test
    public void validateTest() {
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("10", "6", "7", "8"));

        try {
            userAnswer.validate();
        } catch (Exception e) {
            assertTrue(e instanceof OutOfRangeAnswerException);
            assertEquals(e.getMessage(), "Answer format is incorrect");
        }
    }
}