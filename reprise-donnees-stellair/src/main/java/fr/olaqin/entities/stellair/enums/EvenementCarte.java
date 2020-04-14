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
 * @file EvenementCarte.java
 * @brief
 * @date 2019/07/05
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum EvenementCarte {
    /** . */
    INSERTION_CPS(0),
    /** . */
    RETRAIT_CPS(1),
    /** . */
    INSERTION_VITALE(2),
    /** . */
    RETRAIT_VITALE(3);

    private int value;

    private EvenementCarte(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Get enum from value.
     *
     * @param value the value.
     * @return the associated TypeConnexion. Null if there ain't
     */
    public static EvenementCarte fromValue(final int value) {
        for (final EvenementCarte type : EvenementCarte.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }
}
