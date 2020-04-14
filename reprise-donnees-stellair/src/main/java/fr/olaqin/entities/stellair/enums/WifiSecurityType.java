/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 *
 * Copyright (c) 2018, Ingenico Healthcare/e-ID.
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
 * @file WifiSecurityType.java
 * @brief
 * @date 2018/08/30
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum WifiSecurityType {
    /** . */
    AUCUNE("AUCUNE"),
    /** . */
    WEP("WEP"),
    /** . */
    WPA("WPA"),
    /** . */
    WPA2("WPA2");

    private String label;

    private WifiSecurityType(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
