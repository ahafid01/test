package fr.olaqin.entities.galaxie.converters;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateConverterTest {
    private static final LocalDateConverter CONVERTER = new LocalDateConverter();

    @Test
    void convert_null() {
        assertNull(CONVERTER.convert(null));
    }

    @Test
    void convert() {
        final Object result = CONVERTER.convert(LocalDate.of(2020, 03, 9));
        assertNotNull(result);
        assertThat(result, instanceOf(String.class));
        assertEquals("2020-03-09", result);
    }

    @Test
    void unconvert_null() {
        assertNull(CONVERTER.unconvert(null));
    }

    @Test
    void unconvert() {
        final Object result = CONVERTER.unconvert("2020-03-09");
        assertNotNull(result);
        assertThat(result, instanceOf(LocalDate.class));
        assertEquals(LocalDate.of(2020, 03, 9), result);
    }
}