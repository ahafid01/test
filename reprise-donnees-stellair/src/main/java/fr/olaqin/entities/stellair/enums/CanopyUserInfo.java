/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 *
 * Copyright (c) 2017, Ingenico Healthcare/e-ID.
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
 * Module : AppDI
 *
 * @file CanopyUserEnum.java
 * @brief
 * @date 2017/05/10
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Enum contenant les informations sur les utilisateurs devant avoir des données fixes (car ils sont utilisés dans les tests ou les
 * traitements automatiques).
 */
public enum CanopyUserInfo {

    /** Canopy system user. */
    SYSTEM(1L, UUID.fromString("9ef585c3-65dd-4c6b-ad66-e1cefcec03f2"), "SYSTEM",
           "vital.central.dev+system@gmail.com",
           false,
           Arrays.asList(),
           Arrays.asList(),
           Arrays.asList(GlobalRole.SYSTEM),
           false),

    /** Canopy Super user. */
    SUPER_USER(2L, UUID.fromString("ea4bdc07-4e9e-4718-a368-0a9ba4fafa58"), "superUser",
               "vital.central.dev+superuser@gmail.com",
               true,
               Arrays.asList(),
               Arrays.asList(),
               Arrays.asList(GlobalRole.SUPER_UTILISATEUR),
               false),

    /** Canopy toto user (admin). */
    ADMIN_TEST(3L, UUID.fromString("381dc5be-c191-4ff6-90df-0946766af61a"), "toto",
               "vital.central.dev+toto@gmail.com",
               true,
               Arrays.asList(),
               Arrays.asList(InternalRole.ADMIN),
               Arrays.asList(GlobalRole.ADMIN_CANOPY, GlobalRole.ADMIN_DI, GlobalRole.ADMIN_LV, GlobalRole.ADMIN_TLSI,
                             GlobalRole.EXPLOIT_CANOPY, GlobalRole.EXPLOIT_DI, GlobalRole.EXPLOIT_LV, GlobalRole.EXPLOIT_TLSI),
               false),

    /** Canopy tata user. */
    DO_NOT_USE(4L, UUID.fromString("dba2f743-a48f-431b-8e2a-2d0d0636041e"), "tata", "vital.central.dev+tata@gmail.com",
               false,
               Arrays.asList(CustomerRole.ADMIN, CustomerRole.SOUSCRIPTEUR, CustomerRole.USER),
               Arrays.asList(),
               Arrays.asList(),
               false),

    /** Canopy titi user. */
    DO_NOT_USE_2(5L, UUID.fromString("491e5c5a-0e54-4238-92a0-353b82600a82"), "titi",
                 "vital.central.dev+titi@gmail.com",
                 false,
                 Arrays.asList(CustomerRole.ADMIN, CustomerRole.SOUSCRIPTEUR, CustomerRole.USER),
                 Arrays.asList(),
                 Arrays.asList(),
                 false),

    /** Test user for regles (regles, table evaluation, table forcage), no canopy user corresponding. */
    USER_TEST(6L, UUID.fromString("a139ceec-3f3d-4435-8818-9d50e0154678"), "userTestRegles",
              "vital.central.dev+userTestRegles@gmail.com",
              false,
              Arrays.asList(CustomerRole.ADMIN, CustomerRole.SOUSCRIPTEUR, CustomerRole.USER),
              Arrays.asList(),
              Arrays.asList(),
              true),

    /** Canopy delegate user. */
    USER_TEST_REMPLA(7L, UUID.fromString("7998adc3-d635-4aff-bc8e-ca4a9ac7975a"), "remplaTestRegles",
                     "vital.central.dev+remplaTestRegles@gmail.com",
                     false,
                     Arrays.asList(CustomerRole.DELEGUE),
                     Arrays.asList(),
                     Arrays.asList(),
                     true),

    /** Test user for regles (regles, table evaluation, table forcage), no canopy user corresponding. */
    USER_TEST_2(8L, UUID.fromString("3b4d550a-c05f-4771-94c6-073edd16b4ff"), "userTestRegles2",
                "vital.central.dev+userTestRegles2@gmail.com",
                false,
                Arrays.asList(CustomerRole.ADMIN, CustomerRole.SOUSCRIPTEUR, CustomerRole.USER),
                Arrays.asList(),
                Arrays.asList(),
                true);

    private final Long               id;
    private final UUID               uuid;
    private final String             login;
    private final String             email;
    private final boolean            toBeCreatedOnSso;
    private final List<CustomerRole> customerRoles;
    private final List<InternalRole> internalRoles;
    private final List<GlobalRole>   globalRoles;
    private final boolean            subscribeToBeta;

    private CanopyUserInfo(final Long id, final UUID uuid, final String login, final String email, final boolean toBeCreatedOnSso,
        final List<CustomerRole> customerRoles,
        final List<InternalRole> internalRoles, final List<GlobalRole> rolesGlobaux, final boolean subscribeToBeta) {
        this.id = id;
        this.uuid = uuid;
        this.login = login;
        this.email = email;
        this.toBeCreatedOnSso = toBeCreatedOnSso;
        this.customerRoles = customerRoles;
        this.internalRoles = internalRoles;
        this.globalRoles = rolesGlobaux;
        this.subscribeToBeta = subscribeToBeta;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }


    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public boolean isToBeCreatedOnSso() {
        return toBeCreatedOnSso;
    }

    public Set<CustomerRole> getCustomerRoles() {
        return new HashSet<>(customerRoles);
    }

    public Set<InternalRole> getInternalRoles() {
        return new HashSet<>(internalRoles);
    }

    public Set<GlobalRole> getGlobalRoles() {
        return new HashSet<>(globalRoles);
    }

    public boolean isSubscribeToBeta() {
        return subscribeToBeta;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Fonction permettant de calculer la liste des couples [Role, Etat] à mettre dans le jeton de sécurité du DI.
     *
     * @return la liste des paires [Role, Etat].
     */
    public Map<SubscriptionRole, RoleState> computeRoles() {
        final Map<SubscriptionRole, RoleState> map = new HashMap<>();

        if (subscribeToBeta) {
            for (final SubscriptionRole role : CommercialOfferDetails.STELLAIR_INTEGRAL_BETA.getRoles()) {
                map.put(role, RoleState.OPEN);
            }
        }
        return map;
    }


    /**
     * Get CanopyUserInfo by id.
     *
     * @param userId .
     * @return CanopyUserInfo
     */
    public static CanopyUserInfo fromId(final Long userId) {

        CanopyUserInfo canopyUserInfo = null;

        for (final CanopyUserInfo info : values()) {
            if (info.getId().equals(userId)) {
                canopyUserInfo = info;
                break;
            }
        }

        return canopyUserInfo;
    }

    /**
     * Get CanopyUserInfo by login.
     *
     * @param userLogin .
     * @return CanopyUserInfo
     */
    public static CanopyUserInfo fromLogin(final String userLogin) {

        CanopyUserInfo canopyUserInfo = null;

        for (final CanopyUserInfo info : values()) {
            if (info.getLogin().equals(userLogin)) {
                canopyUserInfo = info;
                break;
            }
        }

        return canopyUserInfo;
    }
}
