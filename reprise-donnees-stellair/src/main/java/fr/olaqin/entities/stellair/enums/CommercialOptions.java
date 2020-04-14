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
 * @file CommercialOptions.java
 * @brief
 * @date 2019/07/10
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum CommercialOptions {
    /** Option connectivité 3G à 0€. */
    NEO__CONNECTIVITE_3G_0(TypeTerminal.PIRANHA, "Option connectivité 3G : ", "gratuite", true),
    /** Option connectivité 3G à 10€. */
    NEO__CONNECTIVITE_3G_10(TypeTerminal.PIRANHA, "Option connectivité 3G : ", "+10€ / mois", true),
    /** Option TMAJ gratuite. */
    PRIUM_4__TMAJ_0(TypeTerminal.PRIUM4, "TMAJ : actualisation des droits en carte Vitale : ", "gratuite", false),
    /** Option TMAJ à 15€. */
    PRIUM_4__TMAJ_15(TypeTerminal.PRIUM4, "TMAJ : actualisation des droits en carte Vitale : ", "+15€ / mois", false);


    private TypeTerminal typeTerminal;
    private String       label;
    private String       price;
    private boolean      activateSim;

    private CommercialOptions(final TypeTerminal typeTerminal, final String label, final String price, final boolean activateSim) {
        this.typeTerminal = typeTerminal;
        this.label = label;
        this.price = price;
        this.activateSim = activateSim;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public TypeTerminal getTypeTerminal() {
        return typeTerminal;
    }

    public String getLabel() {
        return label;
    }

    public String getPrice() {
        return price;
    }

    public boolean isActivateSim() {
        return activateSim;
    }

}
