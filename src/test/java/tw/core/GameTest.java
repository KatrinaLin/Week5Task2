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

        Answer correctAnswer = Answer.createAnswer("1 2 3 4");

        when(answerGenerator.generate()).thenReturn(correctAnswer);
        
        game = new Game(answerGenerator);
    }

    @Test
    public void should_return_1A1B_when_guess() {

        GuessResult guessResult = game.guess(Answer.createAnswer("6 2 4 0"));

        assertEquals("1A1B", guessResult.getResult());
    }

    @Test
    public void should_return_0A0B_when_guess() {
        GuessResult guessResult = game.guess(Answer.createAnswer("5 6 7 8"));

        assertEquals("0A0B", guessResult.getResult());
    }

    @Test
    public void should_return_4A0B_when_guess_correct() {

        GuessResult guessResult = game.guess(Answer.createAnswer("1 2 3 4"));

        assertEquals("4A0B", guessResult.getResult());
    }

    @Test
    public void should_return_guessHistory() {

        game.guess(Answer.createAnswer("6 2 4 0"));

        game.guess(Answer.createAnswer("9 2 5 4"));

        game.guess(Answer.createAnswer("1 3 2 4"));

        game.guess(Answer.createAnswer("1 2 3 4"));

        assertEquals("1A1B", game.guessHistory().get(0).getResult());
        assertEquals("2A0B", game.guessHistory().get(1).getResult());
        assertEquals("2A2B", game.guessHistory().get(2).getResult());
        assertEquals("4A0B", game.guessHistory().get(3).getResult());
    }

    @Test
    public void should_return_status_success() {

        game.guess(Answer.createAnswer("6 2 4 0"));
        assertEquals("continue", game.checkStatus());
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("9 2 5 4"));
        assertEquals("continue", game.checkStatus());
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("1 3 2 4"));
        assertEquals("continue", game.checkStatus());
        assertTrue(game.checkCoutinue());

        game.guess(Answer.createAnswer("1 2 3 4"));
        assertEquals("success", game.checkStatus());
        assertFalse(game.checkCoutinue());
    }

    @Test
    public void should_return_status_fail() {

        game.guess(Answer.createAnswer("6 2 4 0"));
        assertEquals("continue", game.checkStatus());

        game.guess(Answer.createAnswer("9 2 5 4"));
        assertEquals("continue", game.checkStatus());

        game.guess(Answer.createAnswer("9 7 6 0"));
        assertEquals("continue", game.checkStatus());

        game.guess(Answer.createAnswer("0 2 8 1"));
        assertEquals("continue", game.checkStatus());

        game.guess(Answer.createAnswer("1 2 6 3"));
        assertEquals("continue", game.checkStatus());

        game.guess(Answer.createAnswer("1 2 5 4"));
        assertEquals("fail", game.checkStatus());
        assertFalse(game.checkCoutinue());
    }
}
