/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sparkrentals.forms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spark.QueryParamsMap;

/**
 *
 * @author eroy4u
 */
public class BaseFormTest {
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateAlphaNumericLength method, of class BaseForm.
     */
    @Test
    public void testValidateAlphaNumericLength() {
        BaseForm form = new BaseFormImpl();
        form.validateAlphaNumericLength("field", "ASDFS", 5);
        assertTrue(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateAlphaNumericLength("field", "ASDFS", 6);
        assertFalse(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateAlphaNumericLength("field", "f*pop", 6);
        assertFalse(form.getErrorMessages().isEmpty());
    }

    /**
     * Test of validateNonNegativeFloat method, of class BaseForm.
     */
    @Test
    public void testValidateNonNegativeFloat() {
        BaseForm form = new BaseFormImpl();
        form.validateNonNegativeFloat("field", "3.120");
        assertTrue(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeFloat("field", "0");
        assertTrue(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeFloat("field", "-0.99");        
        assertFalse(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeFloat("field", "ab");
        assertFalse(form.getErrorMessages().isEmpty());
    }

    /**
     * Test of validateNonNegativeInt method, of class BaseForm.
     */
    @Test
    public void testValidateNonNegativeInt() {
        BaseForm form = new BaseFormImpl();
        form.validateNonNegativeInt("field", "50");
        assertTrue(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeInt("field", "0");
        assertTrue(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeInt("field", "0.99");        
        assertFalse(form.getErrorMessages().isEmpty());
        
        form.clear();
        form.validateNonNegativeInt("field", "-5");
        assertFalse(form.getErrorMessages().isEmpty());
    }

    /**
     * Test of validateChoices method, of class BaseForm.
     */
    @Test
    public void testValidateChoices() {
        BaseForm form = new BaseFormImpl();
        List choices = Arrays.asList("a", "b", "c");
        form.validateChoices("field", "a", choices);
        assertTrue(form.getErrorMessages().isEmpty());

        form.clear();
        form.validateChoices("field", "abc", choices);
        assertFalse(form.getErrorMessages().isEmpty());
    }

    public class BaseFormImpl extends BaseForm {

        public boolean validate() {
            return false;
        }
    }
    
}
