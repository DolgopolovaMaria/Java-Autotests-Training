package AllureTest;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Disabled
@DisplayName("[ANNOTATED] Тесты для гитхаба")
public class AnnotatedStepsTest {

    private static final String REPOSITORY = "DolgopolovaMaria/Java-Autotests";
    private static final String ownerMaria = "Maria Dolgopolova";

    //@Disabled
    @DisplayName("[ANNOTATED] В репозитории "+ REPOSITORY + " есть .gitignore и build.gradle")
    @Owner(ownerMaria)
    @Severity(SeverityLevel.MINOR)
    @Feature("Файлы в репозитории")
    @Story("Просмотр файлов в репозитории")
    @Link(value = "github", url = "https://github.com")
    @Test
    public void TestGithubIssue() {
        Allure.parameter("GITIGNORE", ".gitignore");
        Allure.parameter("GRADLE", "build.gradle");
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.shouldSeeGitignore();
        steps.shouldSeeGradle();
    }
}
