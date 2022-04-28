package TestPackage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Month;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormTests {

    @BeforeAll
    static void setUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest(){
        Selenide.open("/automation-practice-form");

        String firstName = "Maria";
        String lastName = "Dolgopolova";
        String email = "maria@test.com";
        String gender = "Female";
        String number = "7777777777";
        int year = 1998;
        int month = 5;
        int day = 17;
        String hobby1 = "Sports";
        String hobby2 = "Reading";
        String subject = "Programmaing";
        String address = "Moscow, Lenina, 12";
        String state = "Haryana";
        String city = "Panipat";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOptionByValue(Integer.toString(year));
        $(byText(Integer.toString(day))).click();
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#subjectsInput").setValue(subject);
        $("#currentAddress").setValue(address);
        $("[id=state]").click(); // Выбор государства
        $(byText(state)).click();
        $("[id=city]").click(); // Выбор государства
        $(byText(city)).click();


        $("#submit").click();

        $(".table").shouldHave(text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(number),
                text(day + " " + Month.of(month+1) + "," + year),
                text(hobby1),
                text(hobby2),
                text(subject),
                text(address),
                text(state + " " + city));


    }
}
