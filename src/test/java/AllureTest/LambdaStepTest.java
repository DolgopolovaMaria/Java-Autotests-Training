package AllureTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import java.nio.charset.StandardCharsets;

@Disabled
@DisplayName("[LAMBDA] Тесты для гитхаба")
public class LambdaStepTest {

    private static final String REPOSITORY = "DolgopolovaMaria/Java-Autotests";
    private static final String ownerMaria = "Maria Dolgopolova";

    //@Disabled
    @DisplayName("[LAMBDA] В репозитории "+ REPOSITORY + " есть .gitignore и build.gradle")
    @Owner(ownerMaria)
    @Severity(SeverityLevel.MINOR)
    @Feature("Файлы в репозитории")
    @Story("Просмотр файлов в репозитории")
    @Link(value = "github", url = "https://github.com")
    @Test
    public void TestGithubFiles(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем реопзиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Проверяем что в репозитории есть .gitignore", () -> {
            $("[role='rowheader'] [title='.gitignore']").should(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });

        step("Проверяем что в репозитории есть build.gradle", () -> {
            $("[role='rowheader'] [title='build.gradle']").should(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}