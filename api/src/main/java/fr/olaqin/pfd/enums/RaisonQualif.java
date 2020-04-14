package fr.olaqin.pfd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RaisonQualif {
    UTILISATEUR_TROUVE("Utilisateur trouvé"),
    LOCALISATION_DIFFERENTE("Localisation différente"),
    NON_TROUVE("Non trouvé"),
    LOCALISATION_LEGEREMENT_DIFFERENTE("Localisation légèrement différente"),
    AUCUNE_VILLE_RETOURNEE("Aucune ville retournée");

    private String libelle;
}
