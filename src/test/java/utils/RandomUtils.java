package utils;

import java.util.Random;

public class RandomUtils {

    public static String getRandomGender() {
        Random generator = new Random();
        String[] gender = {"Male", "Female", "Other"};

        return gender[generator.nextInt(gender.length)];
    }

    public static String getRandomMonth() {
        Random generator = new Random();
        String[] month = {"January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};

        return month[generator.nextInt(month.length)];
    }

    public static String[] getRandomSubject() {
        Random generator = new Random();
        String[] subject = {"Maths", "Chemistry", "Physics",
                "Computer Science", "Arts", "Accounting",
                "Commerce", "Economics", "Social Studies"};
        int randomArrSize = generator.nextInt(subject.length);
        if (randomArrSize == 0) {
            randomArrSize += 1;
        }
        String[] result = new String[randomArrSize];

        for (int i = 0; i < randomArrSize; i++) {
            result[i] = subject[i];
        }

        return result;
    }

    public static String[] getRandomHobby() {
        Random generator = new Random();
        String[] hobby = {"Sports", "Reading", "Music"};
        int randomArrSize = generator.nextInt(hobby.length);
        if (randomArrSize == 0) {
            randomArrSize += 1;
        }
        String[] result = new String[randomArrSize];

        for (int i = 0; i < randomArrSize; i++) {
            result[i] = hobby[i];
        }

        return result;
    }

    public static String getRandomState() {
        Random generator = new Random();
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return state[generator.nextInt(state.length)];
    }

    public static String getRandomCity(String state) {
        Random generator = new Random();

        if (state == "NCR") {
            String[] cityNcr = {"Delhi", "Gurgaon", "Noida"};
            return cityNcr[generator.nextInt(cityNcr.length)];
        } else if (state == "Uttar Pradesh") {
            String[] cityUttar = {"Agra", "Lucknow", "Merrut"};
            return cityUttar[generator.nextInt(cityUttar.length)];
        } else if (state == "Haryana") {
            String [] cityHaryana = {"Karnal", "Panipat"};
            return cityHaryana[generator.nextInt(cityHaryana.length)];
        } else {
            String[] cityRajasthan = {"Jaipur", "Jaiselmer"};
            return cityRajasthan[generator.nextInt(cityRajasthan.length)];
        }

    }
}
