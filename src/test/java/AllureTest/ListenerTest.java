package AllureTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[LISTENER] Тесты для гитхаба")
public class ListenerTest {

    private static final String REPOSITORY = "DolgopolovaMaria/Java-Autotests";
    private static final String ownerMaria = "Maria Dolgopolova";

    @Disabled
    @DisplayName("[LISTENER] В репозитории "+ REPOSITORY + " есть .gitignore и build.gradle")
    @Owner(ownerMaria)
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Файлы в репозитории")
    @Story("Просмотр файлов в репозитории")
    @Link(value = "github", url = "https://github.com")
    @Test
    public void TestGithubFiles(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(linkText(REPOSITORY)).click();

        $("[role='rowheader'] [title='.gitignore']").should(Condition.visible);

        $("[role='rowheader'] [title='build.gradle']").should(Condition.visible);
    }

    @Disabled
    @DisplayName("В репозитории есть .gitignore")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "DolgopolovaMaria/Software-Engineering-SPBU",
            "DolgopolovaMaria/Test-Automation-with-Selenium"})
    public void TestGithubFiles(String repoParam){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repoParam);
        $(".header-search-input").submit();

        $(linkText(repoParam)).click();

        $("[role='rowheader'] [title='.gitignore']").should(Condition.visible);
    }
}
