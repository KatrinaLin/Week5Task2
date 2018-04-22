package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void should_generate_a_four_digi_answer() throws OutOfRangeAnswerException {

        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);

        when(randomIntGenerator.generateNums(9, 4)).thenReturn("2 0 1 8");

        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);

        Answer answer = answerGenerator.generate();

        assertEquals(0, answer.getIndexOfNum("2"));
        assertEquals(1, answer.getIndexOfNum("0"));
        assertEquals(2, answer.getIndexOfNum("1"));
        assertEquals(3, answer.getIndexOfNum("8"));
        assertEquals(-1, answer.getIndexOfNum("9"));    // 9 does not exist in the answer
    }
}

