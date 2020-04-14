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
 * @file ApplicationVersion.java
 * @brief
 * @date 2019/09/27
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste des application qui peuvent avoir des versions.
 *
 * @author KJOELISON
 *
 */
public enum ApplicationVersion {

    /** system. */
    SYSTEM("SYSTEM"),

    /** Home. */
    HOME("HOME"),

    /** Lecture Vitale. */
    LECTURE_VITALE("LECTURE_VITALE"),

    /** stellair. */
    STELLAIR("STELLAIR"),

    /** ei96. */
    EI96("EI96"),

    /** . */
    TMAJ("TMAJ");

    private final String name;

    ApplicationVersion(final String name) {
        this.name = name;
    }

    /**
     * @param name the name of application .
     *
     * @return the ApplicationVersion object that corresponds to the name
     */
    public static ApplicationVersion fromName(final String name) {
        for (final ApplicationVersion appVersion : ApplicationVersion.values()) {
            if (appVersion.getName().equals(name)) {
                return appVersion;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    /**
     * Exemple of couple of version of application in a terminal.
     *
     * @return an exemple for test
     */
    public static Map<ApplicationVersion, String> getVersionApplicationForTest() {
        final Map<ApplicationVersion, String> appVersion = new HashMap<>();
        appVersion.put(ApplicationVersion.SYSTEM, "INGENICO_WM05_V14_DEMO21_M190910_USERDEBUG");
        appVersion.put(ApplicationVersion.STELLAIR, "2.1.3");
        appVersion.put(ApplicationVersion.LECTURE_VITALE, "0.1.6");
        appVersion.put(ApplicationVersion.HOME, "1.0.2-demo.RC1");
        return appVersion;
    }
}
