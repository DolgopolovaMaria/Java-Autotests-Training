package FilesTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@DisplayName("Тесты для загрузки и скачивания файлов")
public class DownloadUploadTests {
    @DisplayName("Скачивание файла")
    @Test
    void selenideDownloadTest() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] fileContent = is.readAllBytes();
            String strContent = new String(fileContent, StandardCharsets.UTF_8);
            assertThat(strContent).contains("JUnit 5");
        }
    }

    @DisplayName("Загрузка файла")
    @Test
    void uploadSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("files/1.txt");
        $("#file-submit").click();
        $("div.example").shouldHave(Condition.text("File Uploaded!"));
        $("#uploaded-files").shouldHave(Condition.text("1.txt"));
    }
}
