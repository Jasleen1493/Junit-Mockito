# Junit5
This repository will cover following learnings:
<ol>
  <li>Basics of Unit Testing with JUnit</li>
  <li>Most Popular Mocking Framework - Mockito</li>
  <li>Write Great Unit Tests using Mocking</li>
  <li>Use Mockito in combination with Junit and PowerMock</li>
</ol>

Reference learning url :
https://sapient.udemy.com/course/mockito-tutorial-with-junit-examples/learn/lecture/8042594#overview
Junit 5 - https://sapient.udemy.com/course/junit5-for-beginners/learn/lecture/12417682#overview

To run JUnit 5 tests through Maven, below are the main required dependencies:
<ul>
<li>junit-jupiter-api: It is the main module where all core annotations are located, such as @Test, Lifecycle method annotations and assertions.</li>
<li>junit-jupiter-engine: It has test engine implementation which is required at runtime to execute the tests.</li>
<li>junit-platform-suite: The @Suite support provided by this module to make the JUnitPlatform runner obsolete.</li>
<li>junit-jupiter-params: Support for parameterized tests in JUnit 5.</li>
</ul>  
    
Junit 4 summary
  <ul>
  <li>unit tests - should always be have return value as void, tests can't return anything </li>
<li>unit tests - should always be public, because if the test needs to be called by some other framework, it should be available across packages</li>
  <li>@BeforeClass -  method should be static</li>
  <li>Comparing arrays test = assertArraysEqual</li>
  <li>Exception handling testing - @Test(expected = NullPointerException.class)</li>
<li>Performance testing - @Test(timeout = 100) // In 100 miliseconds, if the test runs, then it's a success else a failure</li>
<li>Parameterized tests - @RunWith(Parameterized.class) using @Parameters annotation on a static method with a Collection return type and constructor of input and output</li>
  </ul>
  
 Junit 5 documentation - https://junit.org/junit5/docs/current/user-guide/
   
 Migrating from Junit4 to Junit5
 https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4
