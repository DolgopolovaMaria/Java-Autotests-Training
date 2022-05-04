package FilesTest;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@DisplayName("Тесты для парсинга файлов разных форматов")
public class ParsingTests {
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void parsePdfTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = $(byText("PDF download")).download();
        PDF pdf = new PDF(pdfDownload);
        assertThat(pdf.author).contains("Marc Philipp");
        assertThat(pdf.numberOfPages).isEqualTo(166);
    }

    @Test
    void parseXlsTest() throws Exception {
        open("http://romashka2008.ru/price");
        File xlsDownload = $(".site-main__inner a[href*='prajs_ot']").download();
        XLS xls = new XLS(xlsDownload);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(11)
                .getCell(1)
                .getStringCellValue()).contains("Сахалинская обл, Южно-Сахалинск");
    }

    @Test
    void parseCsvTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("files/example.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)).contains(
                    "Ivan",
                    "Ivanov");
        }
    }

    @Test
    void parseJsonTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream is = classLoader.getResourceAsStream("files/jsonExample.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            assertThat(jsonObject.get("name").getAsString()).isEqualTo("Maria");
            assertThat(jsonObject.get("address").getAsJsonObject().get("street").getAsString()).isEqualTo("Lenina");
        }
    }

    @Test
    void parseZipTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("files/test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
             ZipEntry entry;
             while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test.txt");
            }
        }
    }

}
