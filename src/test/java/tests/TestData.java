package tests;

import com.github.javafaker.Faker;
import utils.RandomUtils;
import java.util.Random;

public class TestData {

    Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String number = String.valueOf(faker.number().randomNumber(10, true));
    public String fileName = "test.jpg";
    public String address = faker.address().streetAddress();
    public String day = String.valueOf(faker.number().numberBetween(1, 28));
    public String month = RandomUtils.getRandomMonth();
    public String year = String.valueOf(faker.number().numberBetween(1922, 2022));
    public String gender = RandomUtils.getRandomGender();
    public String state = RandomUtils.getRandomState();
    public String city = RandomUtils.getRandomCity(state);
    public String[] subjects = RandomUtils.getRandomSubject();
    public String[] hobbies = RandomUtils.getRandomHobby();
}
