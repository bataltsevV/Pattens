package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int addDays, String dateFormat) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String generateCity(String locale) {
        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        String[] cities = new String[]{"Горно-Алтайск", "Петропавловск-Камчатский", "Санкт-Петербург", "Нижний Новгород", "Ростов-на-Дону", "Южно-Сахалинск", "Великий Новгород", "Йошкар-Ола", "Нарьян-Мар", "Ханты-Мансийск", "Салехард"};
        int itemIndex = (int) (Math.random() * cities.length);
        return cities[itemIndex];
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale(locale));
        String randomName = (faker.name().firstName() + " " + faker.name().lastName() + "-" + faker.name().lastName());
        return randomName.replace('ё', 'е');
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return new UserInfo(generateName(locale), generatePhone(locale));
        }

        public static UserInfoFull generateUserWithCity(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale), generateCity(locale)
            return new UserInfoFull(generateCity(locale), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String name;
        String phone;
    }

    @Value
    public static class UserInfoFull {
        String city;
        String name;
        String phone;
    }
}
