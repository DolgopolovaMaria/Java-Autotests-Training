package TestPackage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Month;

public class RegistrationFormTests {

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = "Female";
    String number = faker.phoneNumber().subscriberNumber(10);
    String year = Integer.toString(faker.number().numberBetween(1980, 2010));
    int month = faker.number().numberBetween(0, 11);
    String day = "17";
    String hobby1 = "Sports";
    String hobby2 = "Reading";
    String subject = "Math";
    String address = faker.address().fullAddress();
    String state = "Haryana";
    String city = "Panipat";
    String pictureName = "2.png";

    @BeforeAll
    static void setUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void appearance(){
        Selenide.open("/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#genterWrapper").shouldHave(text("Male"),
                text("Female"),
                text("Other"));
        $("#hobbiesWrapper").shouldHave(text("Sports"),
                text("Reading"),
                text("Music"));
    }

    @Test
    void fillFormTest(){

        Selenide.open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        //  $(byText(day)).click();
        //  $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
        $x("//div[not(contains(@class , 'react-datepicker__day--outside-month'))][text()='17']").click();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        //  $("#uploadPicture").uploadFile(new File("src/test/resources/img/2.png"));
        $("#uploadPicture").uploadFromClasspath("img/" + pictureName);
        //  $("#subjectsInput").setValue(subject);
        $("#subjectsInput").setValue(subject).pressEnter();

        $("#currentAddress").setValue(address);
        $("[id=state]").click();
        $(byText(state)).click();
        $("[id=city]").click();
        $(byText(city)).click();

        $("#submit").click();

        String fullName = format("%s %s", firstName, lastName);
        String bith = format("%s %s,%s", day, Month.of(month+1), year);
        String addr = format("%s %s", state, city);

        $(".table").shouldHave(text(fullName),
                text(email),
                text(gender),
                text(number),
                text(bith),
                text(hobby1),
                text(hobby2),
                text(subject),
                text(address),
                text(addr),
                text(pictureName));
    }
}
