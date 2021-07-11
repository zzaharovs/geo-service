package ru.netology.i18n;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {


    //Заметил, что в кдассу Location не переопределен equals. Это потенциально может вызвать проблемы
    @Test
    public void localeRussia_return_russianText() {

        LocalizationService localization = new LocalizationServiceImpl();

        Country russiaTestCountry = Country.RUSSIA;

        String expectedResult = "Добро пожаловать";

        MatcherAssert.assertThat(localization.locale(russiaTestCountry), Matchers.is(expectedResult));
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, mode = Mode.EXCLUDE, names = {"RUSSIA"})
    public void localeOther_return_EnglishText(Country anyCountry) {
        //given

        LocalizationService localization = new LocalizationServiceImpl();
        String expectedResult = "Welcome";

        //then

        //when
        MatcherAssert.assertThat(localization.locale(anyCountry), Matchers.is(expectedResult));
    }
}
