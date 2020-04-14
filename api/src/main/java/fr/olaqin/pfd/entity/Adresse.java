package fr.olaqin.pfd.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@DynamoDBDocument
public class Adresse {

    @DynamoDBAttribute(attributeName = "NomComplet")
    private String nomComplet;
    @DynamoDBAttribute(attributeName = "Ligne1")
    private String ligne1;
    @DynamoDBAttribute(attributeName = "Ligne2")
    private String ligne2;
    @DynamoDBAttribute(attributeName = "CodePostal")
    private String codePostal;
    @DynamoDBAttribute(attributeName = "Ville")
    private String ville;
}
