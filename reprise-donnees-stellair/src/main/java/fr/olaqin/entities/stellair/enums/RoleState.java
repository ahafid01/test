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
 * @file RoleState.java
 * @brief
 * @date 2019/03/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;


public enum RoleState implements Comparable<RoleState> {
    /** Role en cours de validité. */
    OPEN,
    /** Rôle qui n'est plus ouvert. */
    CLOSED,
    /** Rôle qui est suspendu (désactivation temporaire par l'ADV (si la personne n'a pas payé par exemple)). */
    SUSPENDED,
    /** Rôle qui est supprimé (droit normalement hérité, mais desactivé par un admin ou par le parent). */
    DEACTIVATED;


//    // -------------------------------------------------------------------------------------------------------------------------------------
//    /**
//     * Fonction permettant de savoir si un etat est mieux ou moins bien qu'un autre.
//     * - Ouvert (le plus intéressant)
//     * - Fermé l'emporte sur les autres restrictions
//     * - Descativé, l'emporte sur suspendu (car pas besoin de savoir qu'il est suspendu si le parent l'a désactivé)
//     * - Suspendu (moins intéressant)
//     *
//     * @param other la valeur avec laquelle comparer.
//     * @return returne true si o1 est plus interessant que o2.
//     */
//    public boolean isBetterThan(final RoleState other) {
//
//        // Valeurs de RoleState classées par ordre du plus interessant au moins intéressant
//        final List<RoleState> sortedRoleStates = Arrays.asList(RoleState.OPEN,
//                                                               RoleState.CLOSED,
//                                                               RoleState.DEACTIVATED,
//                                                               RoleState.SUSPENDED,
//                                                               null);
//
//        if (sortedRoleStates.size() != (RoleState.values().length + 1)) {
//            throw new CanopyApplicativeException("La liste sortedRoleStates est incomplete");
//        }
//
//        return sortedRoleStates.indexOf(this) < sortedRoleStates.indexOf(other);
//    }
}
