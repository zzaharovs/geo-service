package ru.netology.geo;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @Test
    public void byIp_RussianIp_returnRussia() {

        //given
        GeoService geo = new GeoServiceImpl();
        String ip = "172.12.11.2";
        Country expectedResult = Country.RUSSIA;

        //then

        //when
        MatcherAssert.assertThat(geo.byIp(ip).getCountry(), Matchers.is(expectedResult));
    }

    @Test
    public void byIp_AmericanIp_returnUsa() {
        //given
        GeoService geo = new GeoServiceImpl();
        String ip = "96.44.11.2";

        Country expectedResult = Country.USA;

        //then
        Country result = geo.byIp(ip).getCountry();

        //when
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void byIp_nonRussianOrAmericanIp_returnNull() {
        //given
        GeoService geo = new GeoServiceImpl();
        String ip = "197.18.12.33";

        //then
        Location result = geo.byIp(ip);

        //when
        Assertions.assertNull(result);
    }
}

