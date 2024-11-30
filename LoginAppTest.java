package org.example; // Adjust the package to match your file structure

import org.junit.jupiter.api.Test; // JUnit test annotation
import static org.junit.jupiter.api.Assertions.*; // JUnit assertions

public class LoginAppTest {

    @Test
    public void testValidEmail() {
        DatabaseService dbService = new DatabaseService();
        String userName = dbService.authenticateUser("validUser@example.com");
        assertEquals("John Doe", userName); // Replace with expected data
    }

    @Test
    public void testInvalidEmail() {
        DatabaseService dbService = new DatabaseService();
        String userName = dbService.authenticateUser("invalidUser@example.com");
        assertNull(userName);
    }

    @Test
    public void testEmptyEmail() {
        DatabaseService dbService = new DatabaseService();
        String userName = dbService.authenticateUser("");
        assertNull(userName);
    }

    @Test
    public void testNullEmail() {
        DatabaseService dbService = new DatabaseService();
        String userName = dbService.authenticateUser(null);
        assertNull(userName);
    }

    @Test
    public void testDatabaseConnectionError() {
        DatabaseService dbService = new DatabaseService();
        assertThrows(Exception.class, () -> {
            dbService.authenticateUser("simulateError@example.com");
        });
    }
}
