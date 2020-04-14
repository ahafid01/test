package fr.olaqin.pfd.enums.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import fr.olaqin.pfd.enums.TypeProfession;

public class TypeProfessionConverter implements DynamoDBTypeConverter<String, TypeProfession> {
    @Override
    public String convert(TypeProfession object) {
        return object.name();
    }

    @Override
    public TypeProfession unconvert(String object) {
        return TypeProfession.valueOf(object);
    }
}
