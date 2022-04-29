package TestPackage.Tests;

import TestPackage.Pages.RegistrationFormPage;
import com.codeborne.selenide.Configuration;

import static java.lang.String.format;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Random;

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
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

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
    }
}
