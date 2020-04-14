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
 * @file SimStatus.java
 * @brief
 * @date 2019/08/30
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum SimStatus {

    /** The initial state when file loaded. */
    INCONNU,

    /** The inventory. */
    INVENTORY,

    /** We asked for activating. */
    ACTIVATING,

    /** The active. */
    ACTIVE,

    /** The test ready. */
    TEST_READY,

    /** The suspended. */
    SUSPENDED,

    /** The retired. */
    RETIRED;
}