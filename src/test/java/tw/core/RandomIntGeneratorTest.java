package tw.core;


import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Before
    public void setUp() throws Exception {
        Random random = mock(Random.class);

        when(random.nextInt()).thenReturn(1);
        when(random.nextInt()).thenReturn(2);
        when(random.nextInt()).thenReturn(3);
        when(random.nextInt()).thenReturn(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();

        randomIntGenerator.generateNums(3, 4);
    }

    @Test
    public void should_generate_1234() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();

        String generatedAnswer = randomIntGenerator.generateNums(9, 4);

        assertEquals(7, generatedAnswer.length());
    }
}