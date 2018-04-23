package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    @Test
    public void should_return_true_when_valid() {
        InputValidator inputValidator = new InputValidator();

        assertTrue(inputValidator.validate("1 2 3 4"));

        assertTrue(inputValidator.validate("5 6 7 8"));

        assertTrue(inputValidator.validate("0 9 1 2"));
    }

    @Test
    public void should_return_false_when_invalid() {
        InputValidator inputValidator = new InputValidator();

        assertFalse(inputValidator.validate("1  2 3 4"));

        assertFalse(inputValidator.validate("1 2 3"));

        assertFalse(inputValidator.validate(""));

        assertFalse(inputValidator.validate("1 1 2 3"));

        assertFalse(inputValidator.validate("10 9 8 7"));
    }
}
