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
 * @file ParametreMaj.java
 * @brief
 * @date 2019/09/30
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

import org.springframework.util.StringUtils;

public enum ParametreMaj {

    /** FOTA. */
    FOTA(Boolean.class, 0x80),

    /** RADIO_ACCES. */
    RADIO_ACCES(Boolean.class, 0x81),

    /** SERVEUR_ADRESS. */
    SERVEUR_ADRESS(String.class, 0x82),

    /** SERVEUR_PORT. */
    SERVEUR_PORT(Integer.class, 0x83),

    /** DOWNLOAD_ID. */
    DOWNLOAD_ID(String.class, 0x84);


    private static final String TRUE  = "TRUE";
    private static final String FALSE = "FALSE";
    private static final String ERROR = "Type d'objet non géré pour ";

    // -------------------------------------------------------------------------------------------------------------------------------------
    private Class<? extends Object> assiociatedClass;
    private int                     tag;



    // -------------------------------------------------------------------------------------------------------------------------------------
    private ParametreMaj(final Class<? extends Object> assiociatedClass, final int tag) {
        this.assiociatedClass = assiociatedClass;
        this.tag = tag;
    }

    public Class<? extends Object> getAssiociatedClass() {
        return assiociatedClass;
    }

    public int getTag() {
        return tag;
    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Parse String to Boolean.
     *
     * @param val to parse
     * @return the boolean value
     */
    public static Boolean getBooleanValue(final String val) {
        final Boolean ret;
        if (TRUE.equalsIgnoreCase(val)) {
            ret = Boolean.TRUE;
        } else {
            ret = Boolean.FALSE;
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Parse boolean to String.
     *
     * @param val to parse
     * @return the string representation
     */
    public static String parseBooleanValue(final Boolean val) {
        final String ret;
        if (Boolean.TRUE.equals(val)) {
            ret = TRUE;
        } else {
            ret = FALSE;
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    private boolean checkBooleanValue(final String val) {
        return TRUE.equals(val) || FALSE.equals(val);
    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Parse String to Integer.
     *
     * @param val to parse
     * @return the Integer value
     */
    public static Integer getIntegerValue(final String val) {
        Integer ret = null;
        if (!StringUtils.isEmpty(val)) {
            ret = Integer.valueOf(val);
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Parse String to Integer.
     *
     * @param val to parse
     * @return the string representation
     */
    public static String parseIntegerValue(final Integer val) {
        String ret = "";
        if (val != null) {
            ret = val.toString();
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    private boolean checkIntegerValue(final String val) {
        return StringUtils.isEmpty(val) || val.matches("[0-9]+");
    }


//    // -------------------------------------------------------------------------------------------------------------------------------------
//    /**
//     * Function that get the typed value of a parameter.
//     *
//     * @param value String formated value.
//     *
//     * @return the typed value.
//     */
//    public Object getTypedValue(final String value) {
//        Object ret = null;
//        if (this.getAssiociatedClass().equals(Boolean.class)) {
//            ret = getBooleanValue(value);
//        } else if (this.getAssiociatedClass().equals(Integer.class)) {
//            ret = getIntegerValue(value);
//        } else if (this.getAssiociatedClass().equals(String.class)) {
//            ret = value;
//        } else {
//            throw new CanopyApplicativeException(ERROR + this.name());
//        }
//        return ret;
//    }


//
//    // -------------------------------------------------------------------------------------------------------------------------------------
//    /**
//     * Function that checks if a parameter given by the UI has a correct value.
//     *
//     * @param value the value to check.
//     * @return true or false
//     */
//    public boolean checkValue(final String value) {
//        boolean ret = true;
//        if (this.getAssiociatedClass().equals(Boolean.class)) {
//            ret = checkBooleanValue(value);
//        } else if (this.getAssiociatedClass().equals(Integer.class)) {
//            ret = checkIntegerValue(value);
//        } else if (this.getAssiociatedClass().equals(String.class)) {
//            ret = true;
//        } else {
//            throw new CanopyApplicativeException(ERROR + this.name());
//        }
//        return ret;
//    }

}
