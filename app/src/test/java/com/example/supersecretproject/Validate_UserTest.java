package com.example.supersecretproject;

import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class Validate_UserTest {

    @Test
    public void validateFirstName() {
        assertTrue("Jake ", true);
        assertTrue("Deez Nutts", true);
        assertTrue("Leroy Jenkins", true);

        assertFalse("", false);
        assertFalse(" ", false);
        assertFalse("2", false);
        assertFalse("!", false);
        assertFalse("+", false);
        assertFalse("%", false);
        assertFalse("R2D2", false);

    }

    @Test
    public void validateLastName() {
        assertTrue("Dove ", true);
        assertTrue("Deez Nutts", true);
        assertTrue("Leroy Jenkins", true);

        assertFalse("", false);
        assertFalse(" ", false);
        assertFalse("2", false);
        assertFalse("!", false);
        assertFalse("+", false);
        assertFalse("%", false);
        assertFalse("R2D2", false);

    }

    @Test
    public void validatePasswordTest() {

        assertTrue("Password1", true);
        assertFalse("bleh", false);
        assertFalse("123456", false);
        assertFalse("password", false);
        assertFalse("", false);
        assertFalse(" ", false);
        assertFalse("bleh", false);

    }

    @Test
    public void validateEmail() {
        assertTrue( "JakeDove@outlook.co.uk", true );
        assertTrue( "testEmailAddress@Googlemail.com", true );
        assertTrue( "testEmailA234234ddress@Google33242mail.com", true );
        assertFalse("bleh", false);
        assertFalse("12313@", false);
        assertFalse(" ", false);
        assertFalse("TestEmail.com", false);
        assertFalse("sdfsdfkjsghjn", false);
        assertFalse(".@>@>@.", false);

    }

    public void validatePasswordConfirmation(String password, String passwordConfirm) {


        assertFalse("bleh", false);
        assertTrue("Password1!", false);
        assertTrue("Password1", true);
        assertFalse("bleh", false);
        assertFalse("123456", false);
        assertFalse("password", false);
        assertFalse("", false);
        assertFalse(" ", false);
        assertFalse("bleh", false);

    }


}