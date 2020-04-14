package fr.olaqin.pfdbatch.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnuaireProfessionnelSanteEnActiviteDto {

    @CsvBindByPosition(position = 0)
    private String typeIdentifiantPP;
    @CsvBindByPosition(position = 1)
    private String identifiantPP;
    @CsvBindByPosition(position = 2)
    private String identificationNationalePP;
    @CsvBindByPosition(position = 3)
    private String codeCiviliteExercice;
    @CsvBindByPosition(position = 4)
    private String libelleCiviliteExercice;
    @CsvBindByPosition(position = 5)
    private String codeCivilite;
    @CsvBindByPosition(position = 6)
    private String libelleCivilite;
    @CsvBindByPosition(position = 7)
    private String nomExercice;
    @CsvBindByPosition(position = 8)
    private String prenomExercice;
    @CsvBindByPosition(position = 9)
    private String codeProfession;
    @CsvBindByPosition(position = 10)
    private String libelleProfession;
    @CsvBindByPosition(position = 11)
    private String codeCategorieProfessionnelle;
    @CsvBindByPosition(position = 12)
    private String libelleCategorieProfessionnelle;
    @CsvBindByPosition(position = 13)
    private String codeTypeSavoirFaire;
    @CsvBindByPosition(position = 14)
    private String libelleTypeSavoirFaire;
    @CsvBindByPosition(position = 15)
    private String codeSavoirFaire;
    @CsvBindByPosition(position = 16)
    private String libelleSavoirFaire;
    @CsvBindByPosition(position = 17)
    private String codeModeExercice;
    @CsvBindByPosition(position = 18)
    private String libelleModeExercice;
    @CsvBindByPosition(position = 19)
    private String numeroSiretSite;
    @CsvBindByPosition(position = 20)
    private String numeroSirenSite;
    @CsvBindByPosition(position = 21)
    private String numeroFinessSite;
    @CsvBindByPosition(position = 22)
    private String numeroFinessEtablissementJuridique;
    @CsvBindByPosition(position = 23)
    private String identifiantTechniqueStructure;
    @CsvBindByPosition(position = 24)
    private String raisonSocialeSite;
    @CsvBindByPosition(position = 25)
    private String enseigneCommercialeSite;
    @CsvBindByPosition(position = 26)
    private String complementDestinataireCoordStructure;
    @CsvBindByPosition(position = 27)
    private String complementGeographiqueCoordStructure;
    @CsvBindByPosition(position = 28)
    private String numeroVoieCoordStructure;
    @CsvBindByPosition(position = 29)
    private String indiceRepetitionCoordStructure;
    @CsvBindByPosition(position = 30)
    private String codeTypeVoieCoordStructure;
    @CsvBindByPosition(position = 31)
    private String libelleTypeVoieCoordStructure;
    @CsvBindByPosition(position = 32)
    private String libelleVoieCoordStructure;
    @CsvBindByPosition(position = 33)
    private String mentionDistributionCoordStructure;
    @CsvBindByPosition(position = 34)
    private String bureauCedexCoordStructure;
    @CsvBindByPosition(position = 35)
    private String codePostalCoordStructure;
    @CsvBindByPosition(position = 36)
    private String codeCommuneCoordStructure;
    @CsvBindByPosition(position = 37)
    private String libelleCommuneCoordStructure;
    @CsvBindByPosition(position = 38)
    private String codePaysCoordStructure;
    @CsvBindByPosition(position = 39)
    private String libellePaysCoordStructure;
    @CsvBindByPosition(position = 40)
    private String telephoneCoordStructure;
    @CsvBindByPosition(position = 41)
    private String telephone2CoordStructure;
    @CsvBindByPosition(position = 42)
    private String telecopieCoordStructure;
    @CsvBindByPosition(position = 43)
    private String adresseEmailCoordStructure;
    @CsvBindByPosition(position = 44)
    private String codeDepartementStructure;
    @CsvBindByPosition(position = 45)
    private String libelleDepartementStructure;
    @CsvBindByPosition(position = 46)
    private String ancienIdentifiantStructure;
    @CsvBindByPosition(position = 47)
    private String autoriteEnregistrement;
    @CsvBindByPosition(position = 48)
    private String codeSecteurActivite;
    @CsvBindByPosition(position = 49)
    private String libelleSecteurActivite;
    @CsvBindByPosition(position = 50)
    private String codeSectionTableauPharmaciens;
    @CsvBindByPosition(position = 51)
    private String libelleSectionTableauPharmaciens;

}
