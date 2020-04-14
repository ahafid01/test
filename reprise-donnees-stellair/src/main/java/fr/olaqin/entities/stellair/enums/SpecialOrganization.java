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
 * @file SpecialOrganization.java
 * @brief
 * @date 2019/11/15
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

import java.util.UUID;

public enum SpecialOrganization {
    /** Admin Organization. That organization is used to not have NULL as current organization when user is in administration context */
    ADMIN("ADMIN", UUID.fromString("cffebc8e-28b8-4228-acd6-fb5f71f80b1d")),
    /** Root Organization, at top level. */
    ROOT("Olaqin", UUID.fromString("6440676d-13a9-452a-9f2d-5df1eba458ed")),
    /** Armatis Organization, at sub level. */
    ARMATIS("Armatis", UUID.fromString("d2b025a3-a8f5-401a-b5a4-0328fffce4f4"));

    private final String name;
    private final UUID   id;

    // -------------------------------------------------------------------------------------------------------------------------------------
    private SpecialOrganization(final String name, final UUID id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
