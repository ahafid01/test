/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 *
 * Copyright (c) 2017, Ingenico Healthcare/e-ID.
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
 * @file TypeTerminal.java
 * @brief
 * @date 2017/10/16
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

/**
 * The Enum TypeTerminal.
 */
public enum TypeTerminal {

    /** The iwl250 terminal. */
    IWL250("iWL250"),

    /** The néo terminal. */
    PIRANHA("Néo"),

    /** The Prium-4 terminal. */
    PRIUM4("Prium-4");

    private String label;

    private TypeTerminal(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Get enum from label.
     *
     * @param label the value.
     * @return the associated TypeTerminal. Null if there ain't
     */
    public static TypeTerminal fromValue(final String label) {
        for (final TypeTerminal type : TypeTerminal.values()) {
            if (type.getLabel().equals(label)) {
                return type;
            }
        }

        return null;
    }
}
