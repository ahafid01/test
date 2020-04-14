package fr.olaqin.pfd.entity.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(LocalDate object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return object.toString();
    }

    @Override
    public LocalDate unconvert(String object) {
        if (StringUtils.isBlank(object)) {
            return null;
        }
        return LocalDate.parse(object);
    }
}
