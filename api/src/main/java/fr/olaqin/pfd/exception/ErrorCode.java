package fr.olaqin.pfd.exception;

public final class ErrorCode {

    private static final IllegalStateException utilityClass = new IllegalStateException("utility class");

    private ErrorCode() {
        throw utilityClass;
    }

    /**
     * InvalidDefinitionException
     */
    public static final class DEFINITION {
        public static final String CODE_ERREUR_INVALID_DEFINITION = "code.erreur.invalid.definition";
        public static final String MESSAGE_ERREUR_INVALID_DEFINITION = "message.erreur.invalid.definition";

        public static final String MESSAGE_GLOBAL_ERREUR_INVALID_DEFINITION = "message.global.erreur.invalid.definition";

        private DEFINITION() {
            throw utilityClass;
        }
    }

    /**
     * Erreur de surface
     */
    public static final class SURFACE {
        public static final String CODE_ERREUR_DE_SURFACE_MULTI_CHAMPS = "code.erreur.de.surface.multi.champs";
        public static final String MESSAGE_ERREUR_DE_SURFACE_MULTI_CHAMPS = "message.erreur.de.surface.multi.champs";

        public static final String MESSAGE_GLOBAL_ERREUR_DE_SURFACE_MULTI_CHAMPS = "message.global.erreur.de.surface.multi.champs";

        private SURFACE() {
            throw utilityClass;
        }
    }

    /**
     * NotFoundException
     */
    public static final class NOTFOUNDEXCEPTION {
        public static final String CODE_ERREUR_PROFESSIONNEL_SANTE_AVEC_IDENTIFIANT = "code.erreur.professionnel.sante.introuvable.id";
        public static final String MESSAGE_ERREUR_PROFESSIONNEL_SANTE_AVEC_IDENTIFIANT = "message.erreur.professionnel.sante.introuvable.id";

        public static final String CODE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT = "code.erreur.utilisateur.avec.introuvable.id";
        public static final String MESSAGE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT = "message.erreur.utilisateur.avec.introuvable.id";

        public static final String CODE_ERREUR_UTILISATEUR_AVEC_EMAIL = "code.erreur.utilisateur.introuvable.avec.email";
        public static final String MESSAGE_ERREUR_UTILISATEUR_AVEC_EMAIL = "message.erreur.utilisateur.introuvable.avec.email";

        public static final String CODE_ERREUR_PAGE = "code.erreur.page.introuvable";
        public static final String MESSAGE_ERREUR_PAGE = "message.erreur.page.introuvable";

        private NOTFOUNDEXCEPTION() {
            throw utilityClass;
        }
    }

    /**
     * InvalidException
     */
    public static final class INVALIDEXCEPTION {
        public static final String CODE_ERREUR_EMAIL = "code.erreur.email.invalide";
        public static final String MESSAGE_ERREUR_EMAIL = "message.erreur.email.invalide";

        public static final String CODE_ERREUR_NOM_PRENOM_OBLIGATOIRES = "code.erreur.nom.prenom.obligatoires";
        public static final String MESSAGE_ERREUR_NOM_PRENOM_OBLIGATOIRES = "message.erreur.nom.prenom.obligatoires";

        private INVALIDEXCEPTION() {
            throw utilityClass;
        }
    }

    /**
     * ANOTHEREXCEPTION
     */
    public static final class ANOTHEREXCEPTION {
        public static final String CODE_ERREUR_INTERNE = "code.erreur.interne";
        public static final String MESSAGE_ERREUR_INTERNE = "message.erreur.interne";

        private ANOTHEREXCEPTION() {
            throw utilityClass;
        }
    }

}
