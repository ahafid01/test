package fr.olaqin.pfd.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@DynamoDBTable(tableName = "AnnuaireProfessionnelSanteEnActivite")
public class AnnuaireProfessionnelSanteEnActivite extends CommonEntity {

    public static final String NOM_PRENOM_CODE_POSTAL_INDEX = "NomPrenomCodePostalIndex";

    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute(attributeName = "TypeIdentifiantPP")
    private String typeIdentifiantPP;
    @DynamoDBAttribute(attributeName = "IdentifiantPP")
    private String identifiantPP;
    @DynamoDBAttribute(attributeName = "IdentificationNationalePP")
    private String identificationNationalePP;
    @DynamoDBAttribute(attributeName = "CodeCiviliteExercice")
    private String codeCiviliteExercice;
    @DynamoDBAttribute(attributeName = "LibelleCiviliteExercice")
    private String libelleCiviliteExercice;
    @DynamoDBAttribute(attributeName = "CodeCivilite")
    private String codeCivilite;
    @DynamoDBAttribute(attributeName = "LibelleCivilite")
    private String libelleCivilite;
    @DynamoDBAttribute(attributeName = "NomExercice")
    private String nomExercice;
    @DynamoDBAttribute(attributeName = "PrenomExercice")
    private String prenomExercice;
    @DynamoDBAttribute(attributeName = "CodeProfession")
    private String codeProfession;
    @DynamoDBAttribute(attributeName = "LibelleProfession")
    private String libelleProfession;
    @DynamoDBAttribute(attributeName = "CodeCategorieProfessionnelle")
    private String codeCategorieProfessionnelle;
    @DynamoDBAttribute(attributeName = "LibelleCategorieProfessionnelle")
    private String libelleCategorieProfessionnelle;
    @DynamoDBAttribute(attributeName = "CodeTypeSavoirFaire")
    private String codeTypeSavoirFaire;
    @DynamoDBAttribute(attributeName = "LibelleTypeSavoirFaire")
    private String libelleTypeSavoirFaire;
    @DynamoDBAttribute(attributeName = "CodeSavoirFaire")
    private String codeSavoirFaire;
    @DynamoDBAttribute(attributeName = "LibelleSavoirFaire")
    private String libelleSavoirFaire;
    @DynamoDBAttribute(attributeName = "CodeModeExercice")
    private String codeModeExercice;
    @DynamoDBAttribute(attributeName = "LibelleModeExercice")
    private String libelleModeExercice;
    @DynamoDBAttribute(attributeName = "NumeroSiretSite")
    private String numeroSiretSite;
    @DynamoDBAttribute(attributeName = "NumeroSirenSite")
    private String numeroSirenSite;
    @DynamoDBAttribute(attributeName = "NumeroFinessSite")
    private String numeroFinessSite;
    @DynamoDBAttribute(attributeName = "NumeroFinessEtablissementJuridique")
    private String numeroFinessEtablissementJuridique;
    @DynamoDBAttribute(attributeName = "IdentifiantTechniqueStructure")
    private String identifiantTechniqueStructure;
    @DynamoDBAttribute(attributeName = "RaisonSocialeSite")
    private String raisonSocialeSite;
    @DynamoDBAttribute(attributeName = "EnseigneCommercialeSite")
    private String enseigneCommercialeSite;
    @DynamoDBAttribute(attributeName = "ComplementDestinataireCoordStructure")
    private String complementDestinataireCoordStructure;
    @DynamoDBAttribute(attributeName = "ComplementGeographiqueCoordStructure")
    private String complementGeographiqueCoordStructure;
    @DynamoDBAttribute(attributeName = "NumeroVoieCoordStructure")
    private String numeroVoieCoordStructure;
    @DynamoDBAttribute(attributeName = "IndiceRepetitionCoordStructure")
    private String indiceRepetitionCoordStructure;
    @DynamoDBAttribute(attributeName = "CodeTypeVoieCoordStructure")
    private String codeTypeVoieCoordStructure;
    @DynamoDBAttribute(attributeName = "LibelleTypeVoieCoordStructure")
    private String libelleTypeVoieCoordStructure;
    @DynamoDBAttribute(attributeName = "LibelleVoieCoordStructure")
    private String libelleVoieCoordStructure;
    @DynamoDBAttribute(attributeName = "MentionDistributionCoordStructure")
    private String mentionDistributionCoordStructure;
    @DynamoDBAttribute(attributeName = "BureauCedexCoordStructure")
    private String bureauCedexCoordStructure;
    @DynamoDBAttribute(attributeName = "CodePostalCoordStructure")
    private String codePostalCoordStructure;
    @DynamoDBAttribute(attributeName = "CodeCommuneCoordStructure")
    private String codeCommuneCoordStructure;
    @DynamoDBAttribute(attributeName = "LibelleCommuneCoordStructure")
    private String libelleCommuneCoordStructure;
    @DynamoDBAttribute(attributeName = "CodePaysCoordStructure")
    private String codePaysCoordStructure;
    @DynamoDBAttribute(attributeName = "LibellePaysCoordStructure")
    private String libellePaysCoordStructure;
    @DynamoDBAttribute(attributeName = "TelephoneCoordStructure")
    private String telephoneCoordStructure;
    @DynamoDBAttribute(attributeName = "Telephone2CoordStructure")
    private String telephone2CoordStructure;
    @DynamoDBAttribute(attributeName = "TelecopieCoordStructure")
    private String telecopieCoordStructure;
    @DynamoDBAttribute(attributeName = "AdresseEmailCoordStructure")
    private String adresseEmailCoordStructure;
    @DynamoDBAttribute(attributeName = "CodeDepartementStructure")
    private String codeDepartementStructure;
    @DynamoDBAttribute(attributeName = "LibelleDepartementStructure")
    private String libelleDepartementStructure;
    @DynamoDBAttribute(attributeName = "AncienIdentifiantStructure")
    private String ancienIdentifiantStructure;
    @DynamoDBAttribute(attributeName = "AutoriteEnregistrement")
    private String autoriteEnregistrement;
    @DynamoDBAttribute(attributeName = "CodeSecteurActivite")
    private String codeSecteurActivite;
    @DynamoDBAttribute(attributeName = "LibelleSecteurActivite")
    private String libelleSecteurActivite;
    @DynamoDBAttribute(attributeName = "CodeSectionTableauPharmaciens")
    private String codeSectionTableauPharmaciens;
    @DynamoDBAttribute(attributeName = "LibelleSectionTableauPharmaciens")
    private String libelleSectionTableauPharmaciens;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = NOM_PRENOM_CODE_POSTAL_INDEX)
    @DynamoDBAttribute(attributeName = "NomExerciceSearch")
    private String nomExerciceSearch;
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = NOM_PRENOM_CODE_POSTAL_INDEX)
    @DynamoDBAttribute(attributeName = "PrenomExerciceSearch")
    private String prenomExerciceSearch;

    public AnnuaireProfessionnelSanteEnActivite(String id, String typeIdentifiantPP, String identifiantPP, String identificationNationalePP, String codeCiviliteExercice, String libelleCiviliteExercice, String codeCivilite, String libelleCivilite, String nomExercice, String prenomExercice, String codeProfession, String libelleProfession, String codeCategorieProfessionnelle, String libelleCategorieProfessionnelle, String codeTypeSavoirFaire, String libelleTypeSavoirFaire, String codeSavoirFaire, String libelleSavoirFaire, String codeModeExercice, String libelleModeExercice, String numeroSiretSite, String numeroSirenSite, String numeroFinessSite, String numeroFinessEtablissementJuridique, String identifiantTechniqueStructure, String raisonSocialeSite, String enseigneCommercialeSite, String complementDestinataireCoordStructure, String complementGeographiqueCoordStructure, String numeroVoieCoordStructure, String indiceRepetitionCoordStructure, String codeTypeVoieCoordStructure, String libelleTypeVoieCoordStructure, String libelleVoieCoordStructure, String mentionDistributionCoordStructure, String bureauCedexCoordStructure, String codePostalCoordStructure, String codeCommuneCoordStructure, String libelleCommuneCoordStructure, String codePaysCoordStructure, String libellePaysCoordStructure, String telephoneCoordStructure, String telephone2CoordStructure, String telecopieCoordStructure, String adresseEmailCoordStructure, String codeDepartementStructure, String libelleDepartementStructure, String ancienIdentifiantStructure, String autoriteEnregistrement, String codeSecteurActivite, String libelleSecteurActivite, String codeSectionTableauPharmaciens, String libelleSectionTableauPharmaciens, String nomExerciceSearch, String prenomExerciceSearch) {
        this.id = id;
        this.typeIdentifiantPP = typeIdentifiantPP;
        this.identifiantPP = identifiantPP;
        this.identificationNationalePP = identificationNationalePP;
        this.codeCiviliteExercice = codeCiviliteExercice;
        this.libelleCiviliteExercice = libelleCiviliteExercice;
        this.codeCivilite = codeCivilite;
        this.libelleCivilite = libelleCivilite;
        this.nomExercice = nomExercice;
        this.prenomExercice = prenomExercice;
        this.codeProfession = codeProfession;
        this.libelleProfession = libelleProfession;
        this.codeCategorieProfessionnelle = codeCategorieProfessionnelle;
        this.libelleCategorieProfessionnelle = libelleCategorieProfessionnelle;
        this.codeTypeSavoirFaire = codeTypeSavoirFaire;
        this.libelleTypeSavoirFaire = libelleTypeSavoirFaire;
        this.codeSavoirFaire = codeSavoirFaire;
        this.libelleSavoirFaire = libelleSavoirFaire;
        this.codeModeExercice = codeModeExercice;
        this.libelleModeExercice = libelleModeExercice;
        this.numeroSiretSite = numeroSiretSite;
        this.numeroSirenSite = numeroSirenSite;
        this.numeroFinessSite = numeroFinessSite;
        this.numeroFinessEtablissementJuridique = numeroFinessEtablissementJuridique;
        this.identifiantTechniqueStructure = identifiantTechniqueStructure;
        this.raisonSocialeSite = raisonSocialeSite;
        this.enseigneCommercialeSite = enseigneCommercialeSite;
        this.complementDestinataireCoordStructure = complementDestinataireCoordStructure;
        this.complementGeographiqueCoordStructure = complementGeographiqueCoordStructure;
        this.numeroVoieCoordStructure = numeroVoieCoordStructure;
        this.indiceRepetitionCoordStructure = indiceRepetitionCoordStructure;
        this.codeTypeVoieCoordStructure = codeTypeVoieCoordStructure;
        this.libelleTypeVoieCoordStructure = libelleTypeVoieCoordStructure;
        this.libelleVoieCoordStructure = libelleVoieCoordStructure;
        this.mentionDistributionCoordStructure = mentionDistributionCoordStructure;
        this.bureauCedexCoordStructure = bureauCedexCoordStructure;
        this.codePostalCoordStructure = codePostalCoordStructure;
        this.codeCommuneCoordStructure = codeCommuneCoordStructure;
        this.libelleCommuneCoordStructure = libelleCommuneCoordStructure;
        this.codePaysCoordStructure = codePaysCoordStructure;
        this.libellePaysCoordStructure = libellePaysCoordStructure;
        this.telephoneCoordStructure = telephoneCoordStructure;
        this.telephone2CoordStructure = telephone2CoordStructure;
        this.telecopieCoordStructure = telecopieCoordStructure;
        this.adresseEmailCoordStructure = adresseEmailCoordStructure;
        this.codeDepartementStructure = codeDepartementStructure;
        this.libelleDepartementStructure = libelleDepartementStructure;
        this.ancienIdentifiantStructure = ancienIdentifiantStructure;
        this.autoriteEnregistrement = autoriteEnregistrement;
        this.codeSecteurActivite = codeSecteurActivite;
        this.libelleSecteurActivite = libelleSecteurActivite;
        this.codeSectionTableauPharmaciens = codeSectionTableauPharmaciens;
        this.libelleSectionTableauPharmaciens = libelleSectionTableauPharmaciens;
        if (StringUtils.isNoneBlank(nomExercice)) {
            this.nomExerciceSearch = nomExercice.toLowerCase();
        }
        if (StringUtils.isNoneBlank(prenomExercice)) {
            this.prenomExerciceSearch = prenomExercice.toLowerCase();
        }
    }

}
