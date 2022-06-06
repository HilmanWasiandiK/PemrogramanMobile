package com.example.simplecalc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

import androidx.test.filters.SmallTest;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class) //used to run the tests in this class
@SmallTest //all the tests in this class are unit tests that have no dependencies, and run in milliseconds
public class ExampleUnitTest {
    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }
    @Test
    public void addTwoNumbersFloats() {
        double resultAdd = mCalculator.add(1.111f, 1.111d);
        assertThat(resultAdd, is(closeTo(2.222, 0.01)));
    }
    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(1d, 1d);
        assertThat(resultSub, is(equalTo(0d)));
    }
    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.sub(1d, 17d);
        assertThat(resultSub, is(equalTo(-16d)));
    }
    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(32d, 2d);
        assertThat(resultMul, is(equalTo(64d)));
    }
    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(32d,2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }
//    @Test
//    public void divTwoNumbersZero() {
//        double resultDiv = mCalculator.div(32d,0);
//        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
//    }

    @Test(expected = IllegalArgumentException.class)
    public void divByZeroThrows() {
        mCalculator.div(32d,0);
    }

    @Test
    public void powTwoNumbers() {
        double resultPow = mCalculator.pow(2d,2d);
        assertThat(resultPow, is(equalTo(4d)));
    }

    @Test
    public void powTwoNumbersNegativeFirst() {
        double resultPow = mCalculator.pow(-2d,2d);
        assertThat(resultPow, is(equalTo(4d)));
    }

    @Test
    public void powTwoNumbersNegativeSecond() {
        double resultPow = mCalculator.pow(2d,-2d);
        assertThat(resultPow, is(equalTo(0.25d)));
    }

    @Test
    public void powTwoNumbersZeroFirst() {
        double resultPow = mCalculator.pow(0,2d);
        assertThat(resultPow, is(equalTo(0.0d)));
    }

    @Test
    public void powTwoNumbersZeroSecond() {
        double resultPow = mCalculator.pow(2d,0);
        assertThat(resultPow, is(equalTo(1.0d)));
    }

    @Test
    public void powTwoNumbersZeroFirstNegativeSecond() {
        double resultPow = mCalculator.pow(0,-1d);
        assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powTwoNumbersNegativeZeroFirstNegativeSecond() {
        double resultPow = mCalculator.pow(-0,-1d);
        assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}