package st10090394;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Login}.
 *
 * @author Richard Pettitt, ST10090394
 */
public class LoginTest {

    private static final String GOOD_USERNAME = "ab_c";
    private static final String GOOD_PASSWORD = "Strong1!x";

    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void silenceOutput() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    private static Scanner input(String... lines) {
        return new Scanner(String.join("\n", lines) + "\n");
    }

    private static Login validLogin() {
        return new Login(input("Richard", "Pettitt", GOOD_USERNAME, GOOD_PASSWORD));
    }

    @Test
    public void testConstructorCapturesNames() {
        Login login = validLogin();
        assertEquals("Richard", login.getFirstName());
        assertEquals("Pettitt", login.getLastName());
    }

    @Test
    public void testConstructorRepromptsUntilCredentialsAreValid() {
        Login login = new Login(input("A", "B",
                "toolong_name", "nounderscore", GOOD_USERNAME,
                "short1!", "nocapital1!", "NoNumber!!", "NoSpecial11", GOOD_PASSWORD));
        assertEquals(GOOD_USERNAME, login.getUsername());
        assertEquals(GOOD_PASSWORD, login.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        Login login = validLogin();
        login.setFirstName("Jane");
        login.setLastName("Doe");
        login.setUsername("j_d");
        login.setPassword("Passw0rd!");
        assertEquals("Jane", login.getFirstName());
        assertEquals("Doe", login.getLastName());
        assertEquals("j_d", login.getUsername());
        assertEquals("Passw0rd!", login.getPassword());
    }

    @Test
    public void testCheckUserNameAcceptsValid() {
        assertTrue(validLogin().checkUserName(input("kyl_1")));
        assertTrue(validLogin().checkUserName(input("_")));
    }

    @Test
    public void testCheckUserNameRejectsMissingUnderscore() {
        assertFalse(validLogin().checkUserName(input("kyle1")));
    }

    @Test
    public void testCheckUserNameRejectsTooLong() {
        assertFalse(validLogin().checkUserName(input("kyle_1")));
    }

    @Test
    public void testCheckPasswordComplexityAcceptsValid() {
        assertTrue(validLogin().checkPasswordComplexity(input("Ch&&sec@ke99!")));
        assertTrue(validLogin().checkPasswordComplexity(input("Abcdef1!")));
    }

    @Test
    public void testCheckPasswordComplexityRejectsTooShort() {
        assertFalse(validLogin().checkPasswordComplexity(input("Abcde1!")));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoCapital() {
        assertFalse(validLogin().checkPasswordComplexity(input("password1!")));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoNumber() {
        assertFalse(validLogin().checkPasswordComplexity(input("Password!!")));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoSpecialCharacter() {
        assertFalse(validLogin().checkPasswordComplexity(input("Password11")));
    }

    @Test
    public void testCheckPasswordComplexityRejectsWhitespace() {
        assertFalse(validLogin().checkPasswordComplexity(input("Pass word1!")));
    }

    @Test
    public void testRegisterUserReturnsUsernameAndPassword() {
        Login login = validLogin();
        assertEquals(GOOD_USERNAME + GOOD_PASSWORD, login.registerUser(input()));
    }

    @Test
    public void testRegisterUserWelcomesUserByName() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        Login login = validLogin();
        System.setOut(new PrintStream(captured));
        login.registerUser(input());
        assertTrue(captured.toString().contains("Welcome Richard Pettitt"));
    }

    @Test
    public void testLoginUserSucceedsWithCorrectCredentials() {
        assertTrue(validLogin().loginUser(GOOD_USERNAME, GOOD_PASSWORD));
    }

    @Test
    public void testLoginUserFailsWithWrongUsername() {
        assertFalse(validLogin().loginUser("zz_z", GOOD_PASSWORD));
    }

    @Test
    public void testLoginUserFailsWithWrongPassword() {
        assertFalse(validLogin().loginUser(GOOD_USERNAME, "Wrong1!pw"));
    }

    @Test
    public void testLoginUserFailsWithNullInput() {
        assertFalse(validLogin().loginUser(null, null));
    }

    @Test
    public void testReturnLoginStatus() {
        Login login = validLogin();
        assertEquals("Login successful", login.returnLoginStatus(true));
        assertEquals("Login failed", login.returnLoginStatus(false));
    }
}
