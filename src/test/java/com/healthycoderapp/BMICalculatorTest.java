package com.healthycoderapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "prod";

    @BeforeAll
    static void beforeAll(){
        //before all is generally used for setting up DB or servers
        System.out.println("Before all unit tests");
    }

    @AfterAll
    static void afterAll(){
        //after all is generally used for closing DB connections or servers
        System.out.println("After all unit tests");
    }

    @BeforeEach
    void setUp(){
       //   System.out.println("Before each unit test");
    }
    @Nested
    class innerClass1{ // created when there are multiple unit tests, and we can segregate into into inner classes having relatable tests under 1
        @Test
        void test() {
            assertTrue(BMICalculator.isDietRecommended(89.0,1.72));
        }
    }


    // not sure what we are testing by the name of the test
    @Test
    void test() {
        assertTrue(BMICalculator.isDietRecommended(89.0,1.72));
    }
    //When we need to repeat a test mutiple times - usually in case of multiple threads
    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void repeatedTest() {
        assertTrue(BMICalculator.isDietRecommended(89.0,1.72));
    }
    // naming convention is imp to knw wht we're testing
    @ParameterizedTest
    @ValueSource(doubles = {89.0,95.0,110.0}) // for single parameter
    void should_returnTrue_when_DietRecommended(Double coderWeight) {
        //given
        double weight = coderWeight;
        double height = 1.72;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(dietRecommended);
    }
    @ParameterizedTest(name = "weight={0},height={1}") // defining parameter names and index value
    @CsvSource(value = {"89.0,1.72","95.0,1.75","110.0,1.78"})// for multiple parameters
    void should_returnTrue_when_DietRecommended_MultipleParams(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(dietRecommended);
    }
    @ParameterizedTest(name = "weight={0},height={1}") // defining parameter names and index value
    @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)// using csv file
    void should_returnTrue_when_DietRecommended_CsvFile(Double coderWeight, Double coderHeight) {
        //given
        double weight = coderWeight;
        double height = coderHeight;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(dietRecommended);
    }
    // given when then approach in unit test
    @Test
    void should_returnTrue_when_DietRecommended_given_when_then() {
        //given
        double weight = 89.0;
        double height = 1.32;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertTrue(dietRecommended);
    }

    @Test
    void should_returnFalse_when_DietRecommended(){
        //given
        double weight = 49.0;
        double height = 5.32;

        //when
        boolean dietRecommended = BMICalculator.isDietRecommended(weight,height);

        //then
        assertFalse(dietRecommended);
    }
    //Testing Exception in unit test
    @Test
    void  should_ThrowArithmeticException_when_HeightZero() {
        //given
        double weight = 49.0;
        double height = 0;

        //when
        Executable exectable = ()-> BMICalculator.isDietRecommended(weight,height);;

        //then
        assertThrows(ArithmeticException.class, exectable);
    }

    @Test
    void should_returnCodeWithWorstBMI_When_CoderListNotEmpty(){
        //given
        List<Coder> coderList = new ArrayList<>();
        coderList.add(new Coder(1.80,60.0));
        coderList.add(new Coder(1.82,98.0));
        coderList.add(new Coder(1.82,64.7));

        //when
       Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coderList);

        //then
        //For multiple asserts, to know which assert fails or pass, assertAll is used, else only using
        //multiple assertEquals will not show which other assert is failing if first assert fails
       assertAll(
               ()->assertEquals(1.82, coderWorstBMI.getHeight()),
               ()->assertEquals(98.0, coderWorstBMI.getWeight())
       );
    }

    @Test
    void should_returnCodeWithWorstBMI_In500Millisec_When_CoderListHas10000Elements(){
        //given
        assumeTrue(this.environment.equals("prod"));// never makes test fail whereas assert makes it fail
      //  assertTrue(this.environment.equals("prod")); // it fails, jence not recommended
        List<Coder> coderList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            coderList.add(new Coder(1.0+i,10.0+i));
        }
        //when
        Executable executable = ()-> BMICalculator.findCoderWithWorstBMI(coderList);

        //then
        assertTimeout(Duration.ofMillis(500),executable);
    }

    @Test
    void should_returnCodeWithWorstBMI_When_CoderListEmpty(){
        //given
        List<Coder> coderList = new ArrayList<>();

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coderList);

        //then
       assertNull(coderWorstBMI);
    }
    @Test
    void should_returnCorrectBMIScoreArray_When_CoderListNotEmpty(){
        //given
        List<Coder> coderList = new ArrayList<>();
        coderList.add(new Coder(1.80,60.0));
        coderList.add(new Coder(1.82,98.0));
        coderList.add(new Coder(1.82,64.7));

        double[] expected = {18.52,29.59,19.53};
        //when
        double[] bmiScores = BMICalculator.getBMIScores(coderList);
        //then
        //assertEquals checks if both objects are equal in memory
        //When we need to compare arrays, use assertArrayEqualss
        assertArrayEquals(expected,bmiScores);
    }
}