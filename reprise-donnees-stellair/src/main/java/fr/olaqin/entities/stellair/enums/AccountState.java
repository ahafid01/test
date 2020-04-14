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
 * @file AccountState.java
 * @brief
 * @date 2019/07/19
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

public enum AccountState {
    /** password expired for search. */
    PWD_EXPIRED,
    /** Creation in progress, has not been registered on SSO yet. */
    CREATING,
    /** Created, never logged. */
    CREATED,
    /** Creation has been brought to the end, user has to confirm his/her personal info. */
    CONNECTED_ONCE_WAITING_FOR_USER_CONFIRMATION,
    /** User has confirmed his/her user info, he/she has to confirm payment meaning info. */
    USER_INFOS_CONFIRMED_WAITING_FOR_PAYMENT_MEAN,
    /** User has confirmed his/her payment meaning info. */
    ACTIVATED,
    /** Account is closed. */
    DEACTIVATED,
}
