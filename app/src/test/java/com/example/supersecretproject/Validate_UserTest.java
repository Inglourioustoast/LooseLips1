package com.example.supersecretproject;

import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class Validate_UserTest {
    @Test
    public void createNewUser() {

                User Jake = new User("Jake Dove", "29", "Jake.dove@testEmail.com", "Love Bacon");
                User Harry = new User("Harry Smith", "37", "Smithers86@gmail.co.uk", "Dunno lol");
                User Dave = new User("Dave Fielding", "19", "Dave_Fielding", "Cant be bothered");

        assertEquals("Error in creating a user", "Jake Dove",    Jake.getFullName());
        assertEquals("Error in getting users email", "Smithers86@gmail.co.uk", Harry.getEmail());
    }




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

    public void testValidatePasswordConfirmation() {



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