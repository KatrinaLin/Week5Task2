package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private GameController gameController;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);

        gameController = new GameController(new Game(answerGenerator), new GameView());
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString().trim();
    }

    @Test
    public void should_print_begin_game() {
        try {
            gameController.beginGame();
            assertEquals("------Guess Number Game, You have 6 chances to guess!  ------", systemOut());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}