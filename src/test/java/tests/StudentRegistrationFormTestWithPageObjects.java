package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class StudentRegistrationFormTestWithPageObjects extends BaseTest {

    @Test
    @Tag("demoqa")
    void formTest() {

        String firstName = "Yulia";
        String lastName = "Dmitrieva";
        String email = "yulia.dmitrieva@yahoo.com";
        String number = "1234567890";
        String fileName = "test.jpg";
        String address = "Test address";
        String day = "24";
        String month = "October";
        String year = "1994";
        String gender = "Female";
        String state = "Rajasthan";
        String city = "Jaipur";
        String[] subjects = {"Maths", "Physics", "Computer Science"};
        String[] hobbies = {"Sports", "Music"};

        step("Fill form", () -> {
            registrationFormPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setGender(gender)
                    .setNumber(number);


            registrationFormPage.setDateOfBirth(day, month, year);

            registrationFormPage.setSubjects(subjects)
                    .setHobbies(hobbies);

            registrationFormPage.uploadPicture(fileName);

            registrationFormPage.setAddress(address)
                    .setState(state)
                    .setCity(city)
                    .clickSubmit();
        });

        step("Check form results", () -> {
            registrationFormPage
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", number)
                    .checkResult("Date of Birth", day + " " + month + "," + year)
                    .checkResultArr("Subjects", subjects)
                    .checkResultArr("Hobbies", hobbies)
                    .checkResult("Picture", fileName).checkResult("Address", address)
                    .checkResult("State and City", state + " " + city);
        });
    }

}
