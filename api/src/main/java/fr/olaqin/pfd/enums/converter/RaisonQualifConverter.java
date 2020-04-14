package fr.olaqin.pfd.enums.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import fr.olaqin.pfd.enums.RaisonQualif;

public class RaisonQualifConverter implements DynamoDBTypeConverter<String, RaisonQualif> {
    @Override
    public String convert(RaisonQualif object) {
        return object.name();
    }

    @Override
    public RaisonQualif unconvert(String object) {
        return RaisonQualif.valueOf(object);
    }
}
