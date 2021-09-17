package com.example.fa21_group1_project1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Local unit test, which will execute on the development machine (host).
 * Tests RegisterUser.java and MainActivity.java methods.
 *
 */
public class LoginRegisterUnitTests {
    @Test
    public void testValidateAccountLogin(){
        Accounts accountTest1 = new Accounts("admin", "admin");
        assertTrue(MainActivity.validateUser(accountTest1));
    }

    @Test
    public void testTakenUsernameRegister(){
        Accounts accountTest2 = new Accounts("admin", "admin");
        assertEquals(3, RegisterUser.checkForValidUsername("admin", accountTest2));
    }

    @Test
    public void testShortUsernameRegister(){
        Accounts accountTest2 = new Accounts("admin", "admin");
        assertEquals(2, RegisterUser.checkForValidUsername("adm", accountTest2));
    }

    @Test
    public void testEmptyUsernameRegister(){
        Accounts accountTest2 = new Accounts("admin", "admin");
        assertEquals(1, RegisterUser.checkForValidUsername("", accountTest2));
    }

    @Test
    public void testShortPasswordRegister(){
        assertEquals(2, RegisterUser.checkForValidPassword("adm"));
    }

    @Test
    public void testEmptyPasswordRegister(){
        assertEquals(1, RegisterUser.checkForValidPassword(""));
    }

}