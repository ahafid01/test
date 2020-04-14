package fr.olaqin.entities.galaxie;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import fr.olaqin.entities.galaxie.converters.LocalDateConverter;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@DynamoDBTable(tableName = "Utilisateur")
public class Utilisateur extends CommonEntity {

    // Commun pour les différents utilisateurs (Vendeur, Professionnel de santé, Interne)
    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute(attributeName = "Nom")
    private String nom;
    @DynamoDBAttribute(attributeName = "Prenom")
    private String prenom;
    @DynamoDBAttribute(attributeName = "Email")
    private String email;

    // Professionnel de santé
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    @DynamoDBAttribute(attributeName = "DateNaissance")
    private LocalDate dateNaissance;
    @DynamoDBAttribute(attributeName = "TelephonePrincipal")
    private String telephonePrincipal;
    @DynamoDBAttribute(attributeName = "TelephoneSecondaire")
    private String telephoneSecondaire;
    private Adresse adresseFacturation;
    private Adresse adresseLivraison;

}