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
 * @file InternalUser.java
 * @brief
 * @date 2019/03/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CanopyUserInfo;
import fr.olaqin.entities.stellair.enums.GlobalRole;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;


// CHECKSTYLE.OFF: Magic Number
@Entity
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("INTERNAL")
public class InternalUser extends AbstractUserEntity implements Serializable {

    /** . */
    private static final long serialVersionUID = 7362045567089633120L;

    @ElementCollection(targetClass = GlobalRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "globalRole",
                     joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
                     foreignKey = @ForeignKey(name = "FK__GLOBAL_ROLE__USER"),
                     indexes = { @Index(name = "index_global_role__user", columnList = "userId") })
    @Column(name = "role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private final Set<GlobalRole> globalRoles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("organization,role")
    private final Set<InternalRoleEntity> roles = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__INTERNAL_USER__CURRENT_ORG"),
                name = "current_organization_pub_id",
                referencedColumnName = "pubId")
    private InternalOrganization currentOrganization;


    // -------------------------------------------------------------------------------------------------------------------------------------
    public InternalUser() {
        super();
    }

    public InternalUser(final CanopyUserInfo user) {
        super(user);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Set<GlobalRole> getGlobalRoles() {
        return globalRoles;
    }


    public Set<InternalRoleEntity> getRoles() {
        return roles;
    }


    public void addRole(final InternalRoleEntity role) {
        role.setUser(this);
        roles.add(role);
    }

    public void removeRole(final InternalRoleEntity role) {
        role.setUser(null);
        roles.remove(role);
    }


    //@Override
    public InternalOrganization getCurrentOrganization() {
        return currentOrganization;
    }


//    @Override
//    public void refreshCurrentOrganization() {
//        // Ces lignes causent un org.springframework.orm.ObjectOptimisticLockingFailureException
//        if (roles.stream().noneMatch(r -> r.getOrganization().equals(currentOrganization))) {
//            if (roles.isEmpty()) {
//                setCurrentOrganization(null);
//            } else {
//                setCurrentOrganization(roles.iterator().next().getOrganization());
//            }
//        }
//    }

    /**
     * Function that set the current organization.
     *
     * @param currentOrganization organization to set.
     */
//    public void setCurrentOrganization(final InternalOrganization currentOrganization) {
//        if ((currentOrganization != null) && roles.stream().noneMatch(r -> r.getOrganization().equals(currentOrganization))) {
//            throw new CanopyApplicativeException("L'utilisateur ne peut pas avoir "
//                                                 + currentOrganization.getName()
//                                                 + " comme organisation courante");
//        }
//        this.currentOrganization = currentOrganization;
//    }
}
// CHECKSTYLE.ON: Magic Number
