package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
    public void should_return_index_of_number() {
        assertEquals(answer.getIndexOfNum("3"), 2);
    }

    @Test
    public void should_return_formatted_string() {
        assertEquals(answer.toString(), "1 2 3 4");
    }

    @Test
    public void should_return_1A1B() {
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("2", "6", "3", "8"));
        Record record = answer.check(userAnswer);   // 1A1B

        assertTrue(record.getValue()[0] == 1 && record.getValue()[1] == 1);
    }

    @Test
    public void should_return_0A0B() {
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("5", "6", "7", "8"));
        Record record = answer.check(userAnswer);   // 0A0B

        assertTrue(record.getValue()[0] == 0 && record.getValue()[1] == 0);
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throw_exception_when_input_is_invalid() throws OutOfRangeAnswerException{
        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("10", "6", "7", "8"));

        userAnswer.validate();
    }

    // Another way to test for exception
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void should_throws_Exception() throws OutOfRangeAnswerException {
        expectedEx.expect(OutOfRangeAnswerException.class);
        expectedEx.expectMessage("Answer format is incorrect");

        Answer userAnswer = new Answer();
        userAnswer.setNumList(Arrays.asList("10", "6", "7", "8"));

        userAnswer.validate();
    }

    // TODO: learn how to write unit test for such method
    @Test
    public void should_create_answer_with_input_string() {
        Answer ans = Answer.createAnswer("1 2 3 4");

        assertEquals(0, ans.getIndexOfNum("1"));
        assertEquals(1, ans.getIndexOfNum("2"));
        assertEquals(2, ans.getIndexOfNum("3"));
        assertEquals(3, ans.getIndexOfNum("4"));
    }

}