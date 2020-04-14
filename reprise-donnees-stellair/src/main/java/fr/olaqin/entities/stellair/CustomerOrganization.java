/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 * <p>
 * Copyright (c) 2019, Ingenico Healthcare/e-ID.
 * "Horizon Défense" - 13-17 Rue Pagès - 92150 Suresnes - France
 * All rights reserved.
 * <p>
 * This source program is the property of INGENICO Company and may not be
 * copied in any form or by any means, whether in part or in whole, except
 * under license expressly granted by INGENICO company
 * <p>
 * All copies of this program, whether in part or in whole, and whether
 * modified or not, must display this and all other embedded copyright
 * and ownership notices in full.
 * --------------------------------------------------------------------------
 * <p>
 * Project : Vital'Central
 * Module : AppCanopy
 *
 * @file OrganizationClient.java
 * @brief
 * @date 2019/11/12
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.SpecialOrganization;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


@Entity
@Table(name = "customer_organization")
@DiscriminatorValue("CUSTOMER")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "FK_CUSTOMER_ORGANIZATION__ABSTRACT_ORG"))
public class CustomerOrganization extends AbstractOrganization implements Serializable {

    /** . */
    private static final long serialVersionUID = -7233519585663955069L;

    /** Default maximum number of accounts that can have simultaneously roles in the organization. */
    private static final int DEFAULT_MAX_ACCOUNTS = 5;


    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("user,role")
    private final SortedSet<CustomerRoleEntity> roles = new TreeSet<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscribedOffers = new TreeSet<>();

    @Column
    private int nbMaxLinkedAccounts = DEFAULT_MAX_ACCOUNTS;

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @deprecated : the CustomerOrganization UUID shall be equal to the Customer UUID (for data migration purpose). so use
     *             CustomerOrganization(final Customer customer)
     */
    @Deprecated
    public CustomerOrganization() {
        super();
    }

    public CustomerOrganization(final SpecialOrganization org) {
        super(org);
    }

    public CustomerOrganization(final Customer customer) {
        super(customer);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Set<CustomerRoleEntity> getRoles() {
        return roles;
    }

    public void addRole(final CustomerRoleEntity role) {
        role.setOrganization(this);
        roles.add(role);
    }

    public void removeRole(final CustomerRoleEntity role) {
        role.setOrganization(null);
        roles.remove(role);
    }

    public Set<SubscribedOffer> getSubscribedOffers() {
        return subscribedOffers;
    }

    public void addSubscribedOffer(final SubscribedOffer subscribedOffer) {
        subscribedOffer.setOrganization(this);
        this.subscribedOffers.add(subscribedOffer);
    }

    public int getNbMaxLinkedAccounts() {
        return nbMaxLinkedAccounts;
    }

    public void setNbMaxLinkedAccounts(final int nbMaxLinkedAccounts) {
        this.nbMaxLinkedAccounts = nbMaxLinkedAccounts;
    }
}
