package fr.olaqin.pfd.enums.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import fr.olaqin.pfd.enums.Civilite;

public class CiviliteConverter implements DynamoDBTypeConverter<String, Civilite> {
    @Override
    public String convert(Civilite object) {
        return object.name();
    }

    @Override
    public Civilite unconvert(String object) {
        return Civilite.valueOf(object);
    }
}
