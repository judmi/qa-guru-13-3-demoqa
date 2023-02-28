package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTest {

    @Test
    @Tag("properties")
    void simplePropertyTest() {
        String browserName = System.getProperty("browser_name", "firefox");
        String browserVersion = System.getProperty("browser_version", "101");
        String browserSize = System.getProperty("browser_size", "1920x1080");

        System.out.println(browserName);
        System.out.println(browserVersion);
        System.out.println(browserSize);
    }

    @Test
    @Tag("hello")
    void simplePropertyTest1() {
        System.out.println("Hello, " + System.getProperty("some_text", "qa.guru"));
    }

}
