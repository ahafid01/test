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
 * @file JiraTicketField.java
 * @brief
 * @date 2019/01/22
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum JiraTicketFieldType {

    /** Nom. */
    NOM("customfield_10201"),
    /** Prénom. */
    PRENOM("customfield_10202"),
    /** Email. */
    EMAIL("customfield_10203"),
    /** Numéro de série de terminal. */
    NS("customfield_10204"),
    /** Resumé. */
    RESUME("summary"),
    /** Descriptif. */
    DESCRIPTIF("description"),
    /** Pièce jointe. */
    PJ("attachment");

    private String id;

    JiraTicketFieldType(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
