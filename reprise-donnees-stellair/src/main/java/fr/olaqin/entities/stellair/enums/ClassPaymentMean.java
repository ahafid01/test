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
 * @file ClassPaymentMean.java
 * @brief
 * @date 2019/07/10
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;


public enum ClassPaymentMean {

    /** Payment card. */
    CB,//(PaymentMeanCardDto.class),
    /** Bank levy. */
    LEVY;//(PaymentMeanLevyDto.class);

//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Properties
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    private final Class<? extends AbstractPaymentMeanDto> associatedDtoClass;
//
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Constructor
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    private ClassPaymentMean(final Class<? extends AbstractPaymentMeanDto> associatedDtoClass) {
//        this.associatedDtoClass = associatedDtoClass;
//    }
//
//
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Public functions
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    public Class<? extends AbstractPaymentMeanDto> getAssociatedDtoClass() {
//        return associatedDtoClass;
//    }
}
