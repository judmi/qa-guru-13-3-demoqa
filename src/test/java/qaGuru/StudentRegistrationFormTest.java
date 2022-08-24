package qaGuru;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationFormTest extends TestBase {

    @Test
    void formTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //filling the fields
        $("[id=firstName]").setValue("Yulia");
        $("[id=lastName]").setValue("Dmitrieva");
        $("[id=userEmail]").setValue("yulia.dmitrieva@yahoo.com");
        $("[id=gender-radio-2]").parent().click();
        //$("#genterWrapper").$(byText("Female")).click(); //best option
        //$("[for=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");
        /* локаторы через id можно записывать через решетку $("#firstName")
        $("#firstName").setValue("Yulia");
        $("#lastName").setValue("Dmitrieva");
        $("#userEmail").setValue("yulia.dmitrieva@yahoo.com");
        $("#gender-radio-2").parent().click();
        $("#userNumber").setValue("1234567890");
         */


        //test date picker
        $("[id=dateOfBirthInput]").click();
        //$(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("October");
        //$(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1994");
        //$(".react-datepicker__year-select").click();
        $(".react-datepicker__day--024:not(.react-datepicker__day--outside-month)").click();

        //test subjects field
        $("[id=subjectsInput]").sendKeys("Maths");
        $("#subjectsInput").pressEnter();
        //$("[id=subjectsInput]").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        $("#hobbiesWrapper").$(byText("Sports")).click();
        //$("[id=hobbies-checkbox-2]").parent().click();

        //file
        //$("[id=uploadPicture]").uploadFile(new File(
        //        "src/test/resources/test.jpg")); //Нужно указывать относительный путь (от корня проекта), а не абсолютный (от корня пк)
        // Еще можно сделать так:
        $("#uploadPicture").uploadFromClasspath("test.jpg");

        //address
        $("[id=currentAddress]").click();
        $("[id=currentAddress]").setValue("Test address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Jaipur")).click();
        //$("[id=stateCity-wrapper]").click();
        //$("[id=react-select-3-input]").setValue("Rajasthan").pressEnter();
        //$("[id=react-select-4-input]").setValue("Jaipur").pressEnter();

        $("[id=submit]").click();

        //checks
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Yulia Dmitrieva"),
                text("yulia.dmitrieva@yahoo.com"),
                text("Female"),
                text("1234567890"),
                text("24 October,1994"),
                text("Maths"),
                //text("Reading"),
                text("test.jpg"),
                text("Test address"),
                text("Rajasthan Jaipur")
        );
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("24 October,1994"));
        checkTable("Date of Birth","24 October,1994");
    }

    void checkTable (String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }

}
