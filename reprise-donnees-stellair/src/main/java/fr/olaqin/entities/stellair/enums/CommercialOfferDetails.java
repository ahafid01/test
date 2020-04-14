/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 *
 * Copyright (c) 2019, Ingenico Healthcare/e-ID.
 * "Horizon Défense" - 13-17 Rue Pagès - 92150 Suresnes - France
 * All rights reserved.
 *
 * This source program is the property of INGENICO Company and may not be
 * copied in any form or by any means, whether in part or in whole, except
 * under license expressly granted by INGENICO company
 *
 * All copies of this program, whether in part or in whole, and whether
 * modified or not, must display this and all other embedded copyright
 * and ownership notices in full.
 * --------------------------------------------------------------------------
 *
 * Project : Vital'Central
 * Module : _Shared
 *
 * @file CommercialOfferDetails.java
 * @brief
 * @date 2019/08/05
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CHECKSTYLE.OFF: MultipleStringLiterals
public enum CommercialOfferDetails {
    /** Test Offer. for tests purpose only. */
    TEST("Test", null,
         "0€", null,
         new ArrayList<>(),
         new ArrayList<>(),
         Arrays.asList(SubscriptionRole.UTILISATEUR_DI),
         false),

    /** Stellair integral offer. */
    STELLAIR_INTEGRAL("Stellair Intégral",
                      null,
                      "32€",
                      null,
                      Arrays.asList("Service de facturation et télétransmission SESAM-Vitale en ligne",
                                    "Multiplateforme : accessible depuis un PC, un smartphone ou une tablette",
                                    "Mises à jour réglementaires automatiques",
                                    "Mise en lots et télétransmission à tout instant",
                                    "Tiers-payant AMO et AMC"),
                      Arrays.asList("Néo",
                                    "PRIUM-4"),
                      Arrays.asList(SubscriptionRole.UTILISATEUR_DI,
                                    SubscriptionRole.UTILISATEUR_LV,
                                    SubscriptionRole.UTILISATEUR_ADRI),
                      false),

    /** Stellair integral offer for beta tester. */
    STELLAIR_INTEGRAL_BETA("Stellair Intégral Bêta",
                           "offre spéciale bêta-test",
                           "0€",
                           "32€",
                           Arrays.asList("Service de facturation et télétransmission SESAM-Vitale en ligne",
                                         "Multiplateforme : accessible depuis un PC, un smartphone ou une tablette",
                                         "Mises à jour réglementaires automatiques",
                                         "Mise en lots et télétransmission à tout instant",
                                         "Tiers-payant AMO et AMC"),
                           Arrays.asList("Néo",
                                         "PRIUM-4"),
                           Arrays.asList(SubscriptionRole.UTILISATEUR_DI,
                                         SubscriptionRole.UTILISATEUR_LV,
                                         SubscriptionRole.UTILISATEUR_ADRI),
                           false),

    /** Stellair consulteur. */
    STELLAIR_CONSULTEUR("Stellair Consulteur",
                        null,
                        "0€",
                        null,
                        Arrays.asList("Service de consultation et de collecte des données de la carte Vitale en ligne",
                                      "Centralisation des données de la carte Vitale avec ou sans CPS/CPE",
                                      "Mode offline",
                                      "Accès en ligne aux données collectées depuis son espace Stellair ou via API web"),
                        Arrays.asList("Néo",
                                      "PRIUM-4"),
                        Arrays.asList(SubscriptionRole.UTILISATEUR_LV),
                        false),

    /** Stellair consulteur Entreprises. */
    STELLAIR_CONSULTEUR_ENTREPRISES("Stellair Consulteur Entreprises",
                                    "",
                                    "0€",
                                    null,
                                    Arrays.asList("Service de consultation et de collecte des données de la carte Vitale en ligne",
                                                  "Centralisation des données de la carte Vitale avec ou sans CPS/CPE",
                                                  "Mode offline",
                                                  "Accès en ligne aux données collectées depuis son espace Stellair ou via API web",
                                                  "Administration par l'utilisateur"),
                                    Arrays.asList("Néo"),
                                    Arrays.asList(SubscriptionRole.UTILISATEUR_LV),
                                    true);



    private final String                 title;
    private final String                 subTitle;
    private final String                 price;
    private final String                 formerPrice;
    private final List<String>           includes;
    private final List<String>           compliantDevices;
    private final List<SubscriptionRole> roles;
    private final boolean                offerForEnterprise;

    // -------------------------------------------------------------------------------------------------------------------------------------
    private CommercialOfferDetails(final String title,
        final String subTitle,
        final String price,
        final String formerPrice,
        final List<String> includes,
        final List<String> compliantDevices,
        final List<SubscriptionRole> roles,
        final boolean offerForEnterprise) {
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
        this.formerPrice = formerPrice;
        this.includes = includes;
        this.compliantDevices = compliantDevices;
        this.roles = roles;
        this.offerForEnterprise = offerForEnterprise;
    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getPrice() {
        return price;
    }

    public String getFormerPrice() {
        return formerPrice;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public List<String> getCompliantDevices() {
        return compliantDevices;
    }

    public List<SubscriptionRole> getRoles() {
        return roles;
    }

    public boolean isOfferForEnterprise() {
        return offerForEnterprise;
    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Function allowing to get an enum by its name.
     *
     * @param name the name to look for.
     * @return the corresponding enum or null.
     */
    public static CommercialOfferDetails getByName(final String name) {
        CommercialOfferDetails ret = null;
        for (final CommercialOfferDetails offer : CommercialOfferDetails.values()) {
            if (offer.name().equalsIgnoreCase(name)) {
                ret = offer;
                break;
            }
        }
        return ret;
    }
}
// CHECKSTYLE.ON: MultipleStringLiterals
