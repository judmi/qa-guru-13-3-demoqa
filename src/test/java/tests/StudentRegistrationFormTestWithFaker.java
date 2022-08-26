package tests;

import org.junit.jupiter.api.Test;


public class StudentRegistrationFormTestWithFaker extends BaseTest {

    @Test
    void formTest() {

        registrationFormPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setNumber(testData.number);

        registrationFormPage.setDateOfBirth(testData.day, testData.month, testData.year);

        registrationFormPage.setSubjects(testData.subjects)
                .setHobbies(testData.hobbies);

        registrationFormPage.uploadPicture(testData.fileName);

        registrationFormPage.setAddress(testData.address)
                .setState(testData.state)
                .setCity(testData.city)
                .clickSubmit();

        registrationFormPage
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.number)
                .checkResult("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                .checkResultArr("Subjects", testData.subjects)
                .checkResultArr("Hobbies", testData.hobbies)
                .checkResult("Picture", testData.fileName).checkResult("Address", testData.address)
                .checkResult("State and City", testData.state + " " + testData.city);
    }
}
