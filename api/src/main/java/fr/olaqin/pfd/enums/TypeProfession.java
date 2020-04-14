package fr.olaqin.pfd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeProfession {
    MEDECIN_GENERALISTE("Médecin Généraliste"),
    MEDECIN_SPECIALISTE("Médecin Spécialiste"),
    AUTRE("Autre");

    private String libelle;
}
