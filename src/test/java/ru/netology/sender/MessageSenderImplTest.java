package ru.netology.sender;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.11.22.2", "172.0.0.1", "172.255.255.0"})
    public void test_SendRussianText_IpRussian(String russianIp) {

        //given
        GeoServiceImpl geoService = (GeoServiceImpl) Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(russianIp)).thenReturn(new Location("Moscow", Country.RUSSIA, (String) null, 0));

        LocalizationServiceImpl localizationService = (LocalizationServiceImpl) Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSenderTest = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);

        String expectedResult = "Добро пожаловать";

        //then

        //when
        MatcherAssert.assertThat(messageSenderTest.send(headers), Matchers.is(expectedResult));
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.14.22.2", "96.2.44.1", "96.155.22.23"})

    public void test_SendEnglishText_IpAmerican(String americanIp) {

        //given
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(americanIp)).thenReturn(new Location("New York", Country.USA, (String) null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSenderTest = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, americanIp);
        String expectedResult = "Welcome";

        //then

        //when

        MatcherAssert.assertThat(messageSenderTest.send(headers), Matchers.is(expectedResult));
    }
}