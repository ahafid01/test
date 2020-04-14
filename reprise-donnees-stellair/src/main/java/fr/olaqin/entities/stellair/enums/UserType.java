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
 * @file ClassUser.java
 * @brief
 * @date 2019/05/27
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;


public enum UserType {
    /** Customer. */
    CUSTOMER,//(CustomerDto.class, CustomerProfileDto.class),
    /** Internal user. */
    INTERNAL_USER;//(InternalUserDto.class, InternalUserProfileDto.class);

//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Properties
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    private final Class<? extends AbstractUserDto<?>>     associatedDtoClass;
//    private final Class<? extends AbstractUserProfileDto> associatedProfileDtoClass;
//
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Constructor
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    private UserType(final Class<? extends AbstractUserDto<?>> associatedDtoClass,
//        final Class<? extends AbstractUserProfileDto> associatedProfileDtoClass) {
//        this.associatedDtoClass = associatedDtoClass;
//        this.associatedProfileDtoClass = associatedProfileDtoClass;
//    }
//
//
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Public functions
//    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    public Class<? extends AbstractUserDto<?>> getAssociatedDtoClass() {
//        return associatedDtoClass;
//    }
//
//    public Class<? extends AbstractUserProfileDto> getAssociatedProfileDtoClass() {
//        return associatedProfileDtoClass;
//    }
}
