package SimpleTests.Tests;

import org.junit.jupiter.api.*;

@Disabled
public class SimpleTests {
    @BeforeAll
    static void initDB(){
        System.out.println("### BeforeAll");
    }

    @AfterAll
    static void clearDB(){
        System.out.println("### AfterAll");
    }

    @BeforeEach
    void openPage(){
        // Selenide.open();
        System.out.println("###     BeforeEach");
    }

    @AfterEach
    void close(){
        // WebDriverRunner.closeWindow();
        System.out.println("###     AfterEach");
    }

    @Test
    void assertTest1() {
        // Assertions.assertTrue(2 > 3);
        System.out.println("###         Test 1");
    }

    @Test
    void assertTest2() {
        // Assertions.assertTrue(2 < 3);
        System.out.println("###         Test 2");
    }
}
