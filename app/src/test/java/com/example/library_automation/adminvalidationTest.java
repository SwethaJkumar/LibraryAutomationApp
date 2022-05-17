package com.example.library_automation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class adminvalidationTest {

    @Test
    public void adminvalidation_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(adminvalidation.isValidEmail("cb.admin01@cb.admin.amrita.edu"));
    }
    @Test
    public void adminValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(adminvalidation.isValidEmail("cb.admin00@aa.bb.amrita.edu"));
    }
    @Test
    public void adminValidator_InvalidEmailusername_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail("cb.admin@cb.admin.amrita.edu"));
    }
    @Test
    public void adminValidator_InvalidEmailusername1_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail("cb.01admin@cb.admin.amrita.edu"));
    }
    @Test
    public void adminValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail("cb.admin02@cb.admin.amrita"));
    }
    @Test
    public void adminValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail("cb.admin00@amrita..edu"));
    }
    @Test
    public void adminValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail("@amrita.edu"));
    }
    @Test
    public void adminValidator_EmptyString_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail(""));
    }
    @Test
    public void adminValidator_NullEmail_ReturnsFalse() {
        assertFalse(adminvalidation.isValidEmail(null));
    }
}