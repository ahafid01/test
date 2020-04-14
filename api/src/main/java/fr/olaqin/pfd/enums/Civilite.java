package fr.olaqin.pfd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Civilite {
    MADAME("Madame"),
    MONSIEUR("Monsieur");

    private String libelle;
}
