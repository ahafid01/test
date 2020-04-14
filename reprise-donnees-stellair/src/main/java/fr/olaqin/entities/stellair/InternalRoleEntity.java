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
 * @file OrganizationalRole.java
 * @brief
 * @date 2019/11/12
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.InternalRole;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "InternalRole",
       indexes = { @Index(name = "index_internal_role__user__organization__role", columnList = "user_id,organization_pub_id,role") },
       uniqueConstraints = @UniqueConstraint(name = "UK__INTERNAL_ROLE__USER__ROLE__ORG",
                                             columnNames = { "user_id", "organization_pub_id", "role" }))
public class InternalRoleEntity implements Serializable { //, Comparable<InternalRoleEntity> {

    /** . */
    private static final long serialVersionUID = -1800395528880950258L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__INTERNAL_ROLE__INTERNAL_USER"),
                referencedColumnName = "userId",
                name = "user_id")
    private InternalUser user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__INTERNAL_ROLE__INTERNAL_ORGANIZATION"),
                name = "organization_pub_id")
    private InternalOrganization organization;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private InternalRole role;

    // -------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "InternalRoleEntity [user=" + user.getLogin() + ", organization=" + organization.getName() + ", role=" + role + "]";
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public int compareTo(final InternalRoleEntity other) {
//        int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getUser(), other.getUser());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getOrganization(), other.getOrganization());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getRole(), other.getRole());
//        }
//        return ret;
//    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    public UUID getPubId() {
        return pubId;
    }

    public InternalUser getUser() {
        return user;
    }

    public void setUser(final InternalUser user) {
        this.user = user;
    }

    public InternalOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(final InternalOrganization organization) {
        this.organization = organization;
    }

    public InternalRole getRole() {
        return role;
    }

    public void setRole(final InternalRole role) {
        this.role = role;
    }


}
