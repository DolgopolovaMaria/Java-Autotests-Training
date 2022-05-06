package SimpleTests.Tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;

@Disabled
public class GithubTests {

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void wikiSoftAssertionsTest(){
        open("/selenide/selenide");

        $("#wiki-tab").click();
        $(byTagAndText("h1","Welcome to the selenide wiki!")).shouldBe(visible);

        $("#user-content-chapters").parent().sibling(0)
                .$$("li").filterBy(text("Soft assertions")).first().$("a").click();

        $(byTagAndText("h1", "SoftAssertions")).shouldBe(visible);

        $("#user-content-3-using-junit5-extend-test-class").parent().sibling(0)
                .$("pre").shouldBe(visible);
    }


}
