package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);

        Answer correctAnswer = new Answer();
        correctAnswer.setNumList(Arrays.asList("1", "2", "3", "4"));

        when(answerGenerator.generate()).thenReturn(correctAnswer);
        
        game = new Game(answerGenerator);
    }

    @Test
    public void should_return_1A1B_when_guess() {

        Answer guessAnswer = new Answer();
        guessAnswer.setNumList(Arrays.asList("6", "2", "4", "0"));

        GuessResult guessResult = game.guess(guessAnswer);

        assertEquals("1A1B", guessResult.getResult());
    }

    @Test
    public void should_return_0A0B_when_guess() {

        Answer guessAnswer = new Answer();
        guessAnswer.setNumList(Arrays.asList("5", "6", "7", "8"));

        GuessResult guessResult = game.guess(guessAnswer);

        assertEquals("0A0B", guessResult.getResult());
    }

    @Test
    public void should_return_4A0B_when_guess_correct() {

        Answer guessAnswer = new Answer();
        guessAnswer.setNumList(Arrays.asList("1", "2", "3", "4"));

        GuessResult guessResult = game.guess(guessAnswer);

        assertEquals("4A0B", guessResult.getResult());
    }

    @Test
    public void should_return_guessHistory_when_guess() {

        Answer guessAnswer1 = new Answer();
        guessAnswer1.setNumList(Arrays.asList("6", "2", "4", "0"));

        game.guess(guessAnswer1);

        Answer guessAnswer2 = new Answer();
        guessAnswer2.setNumList(Arrays.asList("9", "2", "5", "4"));

        game.guess(guessAnswer2);

        Answer guessAnswer3 = new Answer();
        guessAnswer3.setNumList(Arrays.asList("1", "3", "2", "4"));

        game.guess(guessAnswer3);

        Answer guessAnswer4 = new Answer();
        guessAnswer4.setNumList(Arrays.asList("1", "2", "3", "4"));

        game.guess(guessAnswer4);

        assertEquals("1A1B", game.guessHistory().get(0).getResult());
        assertEquals("2A0B", game.guessHistory().get(1).getResult());
        assertEquals("2A2B", game.guessHistory().get(2).getResult());
        assertEquals("4A0B", game.guessHistory().get(3).getResult());
    }

    @Test
    public void should_return_status_success() {

        Answer guessAnswer1 = new Answer();
        guessAnswer1.setNumList(Arrays.asList("6", "2", "4", "0"));
        game.guess(guessAnswer1);
        assertEquals("continue", game.checkStatus());
        assertTrue(game.checkCoutinue());

        Answer guessAnswer2 = new Answer();
        guessAnswer2.setNumList(Arrays.asList("9", "2", "5", "4"));
        game.guess(guessAnswer2);
        assertEquals("continue", game.checkStatus());
        assertTrue(game.checkCoutinue());

        Answer guessAnswer3 = new Answer();
        guessAnswer3.setNumList(Arrays.asList("1", "3", "2", "4"));
        game.guess(guessAnswer3);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer4 = new Answer();
        guessAnswer4.setNumList(Arrays.asList("1", "2", "3", "4"));
        game.guess(guessAnswer4);
        assertEquals("success", game.checkStatus());
        assertFalse(game.checkCoutinue());
    }

    @Test
    public void should_return_status_fail() {

        Answer guessAnswer1 = new Answer();
        guessAnswer1.setNumList(Arrays.asList("6", "2", "4", "0"));
        game.guess(guessAnswer1);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer2 = new Answer();
        guessAnswer2.setNumList(Arrays.asList("9", "2", "5", "4"));
        game.guess(guessAnswer2);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer3 = new Answer();
        guessAnswer3.setNumList(Arrays.asList("9", "7", "6", "0"));
        game.guess(guessAnswer3);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer4 = new Answer();
        guessAnswer4.setNumList(Arrays.asList("0", "2", "8", "1"));
        game.guess(guessAnswer4);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer5 = new Answer();
        guessAnswer5.setNumList(Arrays.asList("1", "2", "6", "3"));
        game.guess(guessAnswer5);
        assertEquals("continue", game.checkStatus());

        Answer guessAnswer6 = new Answer();
        guessAnswer6.setNumList(Arrays.asList("1", "2", "4", "3"));
        game.guess(guessAnswer6);
        assertEquals("fail", game.checkStatus());
        assertFalse(game.checkCoutinue());
    }
}
