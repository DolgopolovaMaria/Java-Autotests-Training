package PropertiesTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PropertiesTests {

    @Disabled
    @Test
    @Tag("test5")
    void someTest() {
        System.out.println("browser");
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "100");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        System.out.println(browser);
        System.out.println(version);
        System.out.println(browserSize);

        /*
           gradle clean test5
                chrome
                100
                1920x1080
           gradle clean test5 -Dbrowser=opera -Dversion=99 -DbrowserSize=300x300
                opera
                99
                300x300
         */
    }
}
