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
 * @file CustomerRoleEntity.java
 * @brief
 * @date 2019/11/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CustomerRole;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CustomerRole",
       uniqueConstraints = @UniqueConstraint(name = "UK__CUSTOMER_ROLE__USER__ROLE__ORG",
                                             columnNames = { "user_id", "organization_pub_id", "role" }))
public class CustomerRoleEntity implements Serializable { //}, Comparable<CustomerRoleEntity> {

    /** . */
    private static final long serialVersionUID = -1800395528880950258L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__CUSTOMER_ROLE__CUSTOMER"),
                referencedColumnName = "userId",
                name = "user_id")
    private Customer user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__CUSTOMER_ROLE__CUSTOMER_ORGANIZATION"),
                name = "organization_pub_id")
    private CustomerOrganization organization;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private CustomerRole role;


    // -------------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public int compareTo(final CustomerRoleEntity other) {
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

    public Customer getUser() {
        return user;
    }

    public void setUser(final Customer user) {
        this.user = user;
    }

    public CustomerOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(final CustomerOrganization organization) {
        this.organization = organization;
    }

    public CustomerRole getRole() {
        return role;
    }

    public void setRole(final CustomerRole role) {
        this.role = role;
    }
}