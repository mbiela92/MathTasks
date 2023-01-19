package de.biela.math;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MathTests
{
    final int FAILURE=999;
    private void systemInput (String userInput) {
        String input = userInput;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    
    @ParameterizedTest(name = "run {index}: QueryAmount Input {0} Output {1}")
    @CsvSource(delimiter = '|', textBlock = """
        -1      |       999
        0       |       999   
        1       |       1                 
        49      |       49
        50      |       50
        51      |       999
        k10     |       999
        1k0     |       999
        10k     |       999

        
        
    """)
    
    void TestQueryAmount(String first, int second)
    {
        assertInputAmount(first, second);
    }
    
    private void assertInputAmount (String input,final int output) 
    {
        // GIVEN
        systemInput(input);
        
        // WHEN
        final int result = Math.queryAmount();
        
        // THEN
        final int expected = output;
        assertEquals(expected, result);
    }
    
    @ParameterizedTest(name = "run {index}: TestQueryMaxValue Input {0} Output {1}")
    @CsvSource(delimiter = '|', textBlock = """
        -1      |       999
        0       |       999
        1       |       1                 
        99      |       99
        100     |       100
        101     |       999
        k10     |       999
        1k0     |       999
        10k     |       999

        
        
    """)
    
    void TestQueryMaxValue(String first, int second)
    {
        assertInputMAXVALUE(first, second);
    }
    
    private void assertInputMAXVALUE (String input,final int output) 
    {
            // GIVEN
            systemInput(input);
            
            // WHEN
            final int result = Math.queryMaxValue();
        
            // THEN
            final int expected = output;
            assertEquals(expected, result);
    }
    
    @ParameterizedTest(name = "run {index}: TestQueryArithmeticOperand Input {0} Output {1}")
    @CsvSource(delimiter = '|', textBlock = """
        +       |       +
        -       |       -   
        *       |       *                 
        :       |       :
        +-*:    |       +-*:
        ++++    |       ++++
        +++++   |       999
        100     |       999
        101     |       999
        k10     |       999
        1k0     |       999
        10k     |       999

        
        
    """)
    
    void TestQueryArithmeticOperand(String first, String second)
    {
        assertInputArithmeticOperand(first, second);
    }
    
    private void assertInputArithmeticOperand (String input,final String output) 
    {
            // GIVEN
            systemInput(input);
            
            // WHEN
            final String result = Math.queryArithmeticOperand();
            
            // THEN
            final String expected = output;
            assertEquals(expected, result);
    }
    
    @ParameterizedTest(name = "run {index}: TestFormatArithmeticOperand Input {0} Output {1}")
    @MethodSource("InputStringAndExpectedArray")
    
    void TestFormatArithmeticOperand(String first, String[] second)
    {
        assertInputFormatArithmeticOperand(first, second);
    }
    
    private static void assertInputFormatArithmeticOperand (String input,final String[] output) 
    {
            // GIVEN
            final String[] result;
            
            // WHEN
            result = Math.FormatArithmeticOperations(input);
            
            // THEN
            final String[] expected = output;
            assertArrayEquals(expected, result);
    }
    
    static Stream<Arguments> InputStringAndExpectedArray ()
    {
        String value1 = "+-*:";
        String[] expected1 = {"+","-","*",":"};
        
        String value2 = "+-*";
        String[] expected2 = {"+","-","*"};
        
        String value3 = "+-";
        String[] expected3 = {"+","-"};
        
        String value4 = "+";
        String[] expected4 = {"+"};       

        return Stream.of(Arguments.of(value1,expected1),
                         Arguments.of(value2,expected2),
                         Arguments.of(value3,expected3),
                         Arguments.of(value4,expected4)); 
    }
    
   
    
    @ParameterizedTest(name = "run {index}: TestCreateTasks Input {0}")
    @CsvSource({"5","50","100"})

    void TestCreateTasks(int first)
    {
        // GIVEN
        String[] arrayOperator = {"+"};
        Math.setArrayOperator(arrayOperator);
        
        // WHEN
        List<Math> tasks = Math.createTasks(first);
        // THEN
        
        assertEquals(first,tasks.size());
        
        
    }
    

    
}
