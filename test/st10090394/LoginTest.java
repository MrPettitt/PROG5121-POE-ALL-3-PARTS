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
    private ByteArrayOutputStream captured;

    @BeforeEach
    public void captureOutput() {
        captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured));
    }

    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    private static Scanner input(String... lines) {
        return new Scanner(String.join("\n", lines) + "\n");
    }

    private static Login registeredLogin() {
        Login login = new Login();
        login.registerUser(input("Richard", "Pettitt", GOOD_USERNAME, GOOD_PASSWORD));
        return login;
    }

    @Test
    public void testNewLoginStartsEmpty() {
        Login login = new Login();
        assertEquals("", login.getFirstName());
        assertEquals("", login.getLastName());
        assertEquals("", login.getUsername());
        assertEquals("", login.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        Login login = new Login();
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
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
        assertTrue(login.checkUserName("_"));
    }

    @Test
    public void testCheckUserNameRejectsMissingUnderscore() {
        assertFalse(new Login().checkUserName("kyle1"));
    }

    @Test
    public void testCheckUserNameRejectsTooLong() {
        assertFalse(new Login().checkUserName("kyle_1"));
    }

    @Test
    public void testCheckPasswordComplexityAcceptsValid() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(login.checkPasswordComplexity("Abcdef1!"));
    }

    @Test
    public void testCheckPasswordComplexityRejectsTooShort() {
        assertFalse(new Login().checkPasswordComplexity("Abcde1!"));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoCapital() {
        assertFalse(new Login().checkPasswordComplexity("password1!"));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoNumber() {
        assertFalse(new Login().checkPasswordComplexity("Password!!"));
    }

    @Test
    public void testCheckPasswordComplexityRejectsNoSpecialCharacter() {
        assertFalse(new Login().checkPasswordComplexity("Password11"));
    }

    @Test
    public void testCheckPasswordComplexityRejectsWhitespace() {
        assertFalse(new Login().checkPasswordComplexity("Pass word1!"));
    }

    @Test
    public void testRegisterUserCapturesDetails() {
        Login login = registeredLogin();
        assertEquals("Richard", login.getFirstName());
        assertEquals("Pettitt", login.getLastName());
        assertEquals(GOOD_USERNAME, login.getUsername());
        assertEquals(GOOD_PASSWORD, login.getPassword());
    }

    @Test
    public void testRegisterUserReturnsUsernameAndPassword() {
        Login login = new Login();
        String result = login.registerUser(input("Richard", "Pettitt", GOOD_USERNAME, GOOD_PASSWORD));
        assertEquals(GOOD_USERNAME + GOOD_PASSWORD, result);
    }

    @Test
    public void testRegisterUserRepromptsUntilValid() {
        Login login = new Login();
        login.registerUser(input("A", "B",
                "toolong_name", "nounderscore", GOOD_USERNAME,
                "short1!", "nocapital1!", "NoNumber!!", "NoSpecial11", GOOD_PASSWORD));
        assertEquals(GOOD_USERNAME, login.getUsername());
        assertEquals(GOOD_PASSWORD, login.getPassword());
    }

    @Test
    public void testRegisterUserWelcomesUserByName() {
        registeredLogin();
        assertTrue(captured.toString().contains("Welcome Richard Pettitt"));
    }

    @Test
    public void testLoginUserSucceedsWithCorrectCredentials() {
        assertTrue(registeredLogin().loginUser(GOOD_USERNAME, GOOD_PASSWORD));
    }

    @Test
    public void testLoginUserFailsWithWrongUsername() {
        assertFalse(registeredLogin().loginUser("zz_z", GOOD_PASSWORD));
    }

    @Test
    public void testLoginUserFailsWithWrongPassword() {
        assertFalse(registeredLogin().loginUser(GOOD_USERNAME, "Wrong1!pw"));
    }

    @Test
    public void testLoginUserFailsWithNullInput() {
        assertFalse(registeredLogin().loginUser(null, null));
    }

    @Test
    public void testReturnLoginStatus() {
        Login login = new Login();
        assertEquals("Login successful", login.returnLoginStatus(true));
        assertEquals("Login failed", login.returnLoginStatus(false));
    }
}
