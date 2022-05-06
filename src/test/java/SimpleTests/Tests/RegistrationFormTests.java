package SimpleTests.Tests;

import SimpleTests.Pages.RegistrationFormPage;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.openqa.selenium.logging.LogType.BROWSER;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

//@Disabled
@DisplayName("Тесты для формы регистрации")
public class RegistrationFormTests {

    Faker faker = new Faker();
    Random random = new Random();

    String[] genderArray = {"Male", "Female", "Other"};
    String[] subjectArray = {"Accounting", "Maths", "Computer Science", "English", "Biology", "Arts", "Chemistry", "Economics", "Physics", "History"};

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = genderArray[random.nextInt(3)];
    String number = faker.phoneNumber().subscriberNumber(10);
    LocalDate birthday = faker.date().birthday(16, 40).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String month = String.valueOf(birthday.getMonth());
    String year = String.valueOf(birthday.getYear());
    String day = String.valueOf(birthday.getDayOfMonth());
    String hobby1 = "Sports";
    String hobby2 = "Reading";
    String hobby3 = "Music";
    String subject = subjectArray[random.nextInt(subjectArray.length)];
    String[] subjects = {subjectArray[random.nextInt(subjectArray.length)],
            subjectArray[random.nextInt(subjectArray.length)],
            subjectArray[random.nextInt(subjectArray.length)]};
    String address = faker.address().fullAddress();
    String state = "Haryana";
    String city = "Panipat";
    String pictureName = "2.png";

    String fullName = format("%s %s", firstName, lastName);
    String birth = format("%s %s,%s", day, month, year);
    String fullAddress = format("%s %s", state, city);

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Attachment(value = "Last screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] attachPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Browser console logs", type = "text/plain")
    public static String attachBrowserConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    @AfterEach
    void addAttachments(){
        attachScreenshot();
        attachPageSource();
        attachBrowserConsoleLogs();
        closeWebDriver();
    }

    //@Disabled
    @DisplayName("Заполнение всех полей валидными значениями") // for Allure integration
    @Test
    void fillFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        step("Open and fill registration form", () -> {
            registrationFormPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setGender(gender)
                    .setMobile(number)
                    .setBirthDate(day, month, year)
                    .setHobby(hobby1).setHobby(hobby2).setHobby(hobby3)
                    .setSubjects(subjects)
                    .setPicture("img/" + pictureName)
                    .setCurrentAddress(address)
                    .setState(state).setCity(city)
                    .submit();
        });

        step("Verify form data", () ->
        {
            registrationFormPage.checkResult("Student Name", fullName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", number)
                    .checkResult("Date of Birth", birth)
                    .checkResult("Subjects", subjects)
                    .checkResult("Hobbies", hobby1)
                    .checkResult("Hobbies", hobby2)
                    .checkResult("Picture", pictureName)
                    .checkResult("Address", address)
                    .checkResult("State and City", fullAddress);
        });
    }

    @Disabled
    @DisplayName("Заполнение только обязательных полей валидными значениями")
    @Test
    void fillFormTestOnlyRequiredFields() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(number)
                .submit();

        registrationFormPage.checkResult("Student Name", fullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", number);
    }

    @Disabled
    @DisplayName("Ввод разных валидных дат")
    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource({
            "1, JANUARY, 2000",
            "30, JULY, 1998",
            "29, FEBRUARY, 2016"})
    void fillDatesTest(String dayParam, String monthParam, String yearParam) {
        String birthParam = format("%s %s,%s", dayParam, monthParam, yearParam);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(number)
                .setBirthDate(dayParam, monthParam, yearParam)
                .submit();

        registrationFormPage.checkResult("Date of Birth", birthParam);
    }
    
    @Disabled
    @DisplayName("Ввод разных валидных номеров телефона")
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {
            "1234567890",
            "0000000000",
            "9712999999"})
    void fillPhoneTest(String numberParam) {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(numberParam)
                .submit();

        registrationFormPage.checkResult("Mobile", numberParam);
    }
}
