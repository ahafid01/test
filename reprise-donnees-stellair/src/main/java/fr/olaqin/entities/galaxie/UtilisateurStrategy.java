package fr.olaqin.entities.galaxie;

import fr.olaqin.entities.stellair.AbstractUserEntity;
import fr.olaqin.entities.stellair.Address;
import fr.olaqin.entities.stellair.Customer;

import java.util.Objects;

/**
 * factory utilisateur
 *
 * @author galaxie
 * @version 1.0
 */
public enum UtilisateurStrategy {
    CUSTOMER {
        @Override
        public Utilisateur creer(AbstractUserEntity item) {
            final Customer customer = (Customer) item;

            final Adresse adresseFacturation = getAdresse(customer.getBillingAddress());
            final Adresse adresseLivraison = getAdresse(customer.getDeliveryAddress());

            return new Utilisateur.UtilisateurBuilder()
                    .nom(customer.getLastName())
                    .prenom(customer.getFirstName())
                    .email(customer.getEmail())
                    .telephonePrincipal(customer.getPhoneNumber1())
                    .telephoneSecondaire(customer.getPhoneNumber2())
                    .adresseLivraison(adresseLivraison)
                    .adresseFacturation(adresseFacturation)
                    .build();
        }
    },
    VENDOR {
        @Override
        public Utilisateur creer(AbstractUserEntity item) {
            return new Utilisateur.UtilisateurBuilder()
                    .nom(item.getLastName())
                    .prenom(item.getFirstName())
                    .email(item.getEmail())
                    .build();
        }
    },
    INTERNAL {
        @Override
        public Utilisateur creer(AbstractUserEntity item) {
            return new Utilisateur.UtilisateurBuilder()
                    .nom(item.getLastName())
                    .prenom(item.getFirstName())
                    .email(item.getEmail())
                    .build();
        }
    };

    private static Adresse getAdresse(Address address) {
        if (Objects.nonNull(address) && Objects.nonNull(address.getCustomer())) {
            final int codePostal = address.getZipCode();
            return new Adresse.AdresseBuilder()
                    .nomComplet(address.getFullName())
                    .ligne1(address.getLine1())
                    .ligne2(address.getLine2())
                    .codePostal(codePostal == 0 ? null : String.valueOf(codePostal))
                    .ville(address.getCity())
                    .build();
        }
        return null;
    }

    public abstract Utilisateur creer(AbstractUserEntity item);
}
