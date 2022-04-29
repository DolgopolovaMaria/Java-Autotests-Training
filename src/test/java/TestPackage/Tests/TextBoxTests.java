package TestPackage.Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TextBoxTests {

    @BeforeAll
    static void setUp(){
        Configuration.holdBrowserOpen = true;   // чтобы браузер не закрывался
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest(){
        String myName = "Mariya Dolgopolova";

        Selenide.open("/text-box");

        $("[id=userName]").setValue(myName);
        $("[id=userEmail]").setValue("maria@test.com");
        $("[id=currentAddress]").setValue("Some street 1");
        $("[id=permanentAddress]").setValue("Another street 2");
        $("[id=submit]").click();
        //  $("#submit").click();

        //  $("[id=output]").shouldHave(text(myName));
        $("[id=output]").$("[id=name]").shouldHave(text(myName));

        // $("[id=permanentAddress]").shouldHave(text("Permananet Address :Another street 2")); не сработает, тк есть 2 элемента с одинаковым id
        // $("[id=permanentAddress]", 1).shouldHave(text("Permananet Address :Another street 2"));
        $("p[id=permanentAddress]").shouldHave(text("Permananet Address :Another street 2"));

        /*String expectedPermanentAddress = "Permananet Address :Another street 1";
        String actualPermanentAddress = $("p[id=permanentAddress]").text();
        assertEquals(expectedPermanentAddress, actualPermanentAddress);*/
    }
}
