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
 * Module : AppCanopy
 *
 * @file TaskType.java
 * @brief
 * @date 2019/11/15
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum TaskType {
    /** . */
    ADMIN(InternalRole.ADMIN),
    /** . */
    COMMERCIAL(InternalRole.VENDEUR),
    /** . */
    SUPPORT(InternalRole.SUPPORT),
    /** . */
    ADV(InternalRole.ADV);

    private final InternalRole associatedRole;

    private TaskType(final InternalRole associatedRole) {
        this.associatedRole = associatedRole;
    }

    public InternalRole getAssociatedRole() {
        return associatedRole;
    }
}
