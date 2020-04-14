package fr.olaqin.pfd.enums.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import fr.olaqin.pfd.enums.ResultatQualif;

public class ResultatQualifConverter implements DynamoDBTypeConverter<String, ResultatQualif> {
    @Override
    public String convert(ResultatQualif object) {
        return object.name();
    }

    @Override
    public ResultatQualif unconvert(String object) {
        return ResultatQualif.valueOf(object);
    }
}
