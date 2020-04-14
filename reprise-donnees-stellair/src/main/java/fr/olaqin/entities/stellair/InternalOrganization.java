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
 * @file Organization.java
 * @brief
 * @date 2019/11/12
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.SpecialOrganization;
import fr.olaqin.entities.stellair.enums.TaskType;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "internal_organization",
       indexes = {
           @Index(name = "index_internal_organization__commercial_parent", columnList = "commercial_parent_id"),
           @Index(name = "index_internal_organization__support_parent", columnList = "support_parent_id"),
           @Index(name = "index_internal_organization__admin_parent", columnList = "admin_parent_id"),
           @Index(name = "index_internal_organization__adv_parent", columnList = "adv_parent_id")
       })
@DiscriminatorValue("INTERNAL")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "FK_INTERNAL_ORGANIZATION__ABSTRACT_ORG"))
public class InternalOrganization extends AbstractOrganization implements Serializable {

    /** . */
    private static final long serialVersionUID = -9048171427170766199L;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("user,role")
    private final SortedSet<InternalRoleEntity> roles = new TreeSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__ORGANIZATION__COMMERCIAL_PARENT"),
                name = "commercial_parent_id")
    private InternalOrganization commercialParent;

    @OneToMany(mappedBy = "commercialParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("name")
    private final SortedSet<InternalOrganization> commercialChildren = new TreeSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__ORGANIZATION__SUPPORT_PARENT"),
                name = "support_parent_id")
    private InternalOrganization supportParent;

    @OneToMany(mappedBy = "supportParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("name")
    private final SortedSet<InternalOrganization> supportChildren = new TreeSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__ORGANIZATION__ADMIN_PARENT"),
                name = "admin_parent_id")
    private InternalOrganization adminParent;

    @OneToMany(mappedBy = "adminParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("name")
    private final SortedSet<InternalOrganization> adminChildren = new TreeSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__ORGANIZATION__ADV_PARENT"),
                name = "adv_parent_id")
    private InternalOrganization advParent;

    @OneToMany(mappedBy = "advParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("name")
    private final SortedSet<InternalOrganization> advChildren = new TreeSet<>();


    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<CommercializedOffer> commercializedOffers = new TreeSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "organizationDelegatedOffers",
               joinColumns = @JoinColumn(name = "organization_pub_id", referencedColumnName = "pubId"),
               inverseJoinColumns = @JoinColumn(name = "commercialized_offer_id", referencedColumnName = "id"),
               foreignKey = @ForeignKey(name = "FK__DELEGATION_OFFERS__ORGANIZATION"),
               inverseForeignKey = @ForeignKey(name = "FK__DELEGATION_OFFERS__DELEGATED_OFFERS"),
               uniqueConstraints = @UniqueConstraint(name = "UK_DELEGATION_OFFER",
                                                     columnNames = { "organization_pub_id", "commercialized_offer_id" }),
               indexes = { @Index(name = "index_delegation_offer", columnList = "commercialized_offer_id") })
    @OrderBy("id")
    private final SortedSet<CommercializedOffer> delegatedOffers = new TreeSet<>();


    @OneToMany(mappedBy = "organizationCommercial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscriptionsWithCommercialRole = new TreeSet<>();


    @OneToMany(mappedBy = "organizationSupport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscriptionsWithSupportRole = new TreeSet<>();


    @OneToMany(mappedBy = "organizationAdmin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscriptionsWithAdminRole = new TreeSet<>();


    @OneToMany(mappedBy = "organizationAdv", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscriptionsWithAdvRole = new TreeSet<>();


    // -------------------------------------------------------------------------------------------------------------------------------------
    public InternalOrganization() {
        super();
    }

    public InternalOrganization(final SpecialOrganization org) {
        super(org);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    private String getError(final TaskType type) {
        return "Cas " + type + " non géré";
    }

//    /**
//     * Function to get the parent according to a task type.
//     *
//     * @param task the type.
//     * @return the parent.
//     */
//    public InternalOrganization getParent(final TaskType task) {
//        final InternalOrganization parent;
//        switch (task) {
//            case ADMIN:
//                parent = adminParent;
//                break;
//            case COMMERCIAL:
//                parent = commercialParent;
//                break;
//            case SUPPORT:
//                parent = supportParent;
//                break;
//            case ADV:
//                parent = advParent;
//                break;
//            default:
//                throw new CanopyApplicativeException(getError(task));
//        }
//        return parent;
//    }

    /**
     * Function to set the parent according to a task type.
     *
     * @param parent the parent.
     * @param task the type.
     */
//    public void setParent(final InternalOrganization parent, final TaskType task) {
//        switch (task) {
//            case ADMIN:
//                if (this.adminParent != null) {
//                    this.adminParent.removeChild(this, task);
//                }
//                this.adminParent = parent;
//                parent.addChild(this, task);
//                break;
//            case COMMERCIAL:
//                if (this.commercialParent != null) {
//                    this.commercialParent.removeChild(this, task);
//                }
//                this.commercialParent = parent;
//                parent.addChild(this, task);
//                break;
//            case SUPPORT:
//                if (this.supportParent != null) {
//                    this.supportParent.removeChild(this, task);
//                }
//                this.supportParent = parent;
//                parent.addChild(this, task);
//                break;
//            case ADV:
//                if (this.advParent != null) {
//                    this.advParent.removeChild(this, task);
//                }
//                this.advParent = parent;
//                parent.addChild(this, task);
//                break;
//            default:
//                throw new CanopyApplicativeException(getError(task));
//        }
//    }
//
//    /**
//     * Function to get the list of children according to a task type.
//     *
//     * @param task the type.
//     * @return the children list.
//     */
//    public Set<InternalOrganization> getChildren(final TaskType task) {
//        final Set<InternalOrganization> children;
//        switch (task) {
//            case ADMIN:
//                children = adminChildren;
//                break;
//            case COMMERCIAL:
//                children = commercialChildren;
//                break;
//            case SUPPORT:
//                children = supportChildren;
//                break;
//            case ADV:
//                children = advChildren;
//                break;
//            default:
//                throw new CanopyApplicativeException(getError(task));
//        }
//        return children;
//    }
//
//    /**
//     * Function to add a child in list of children according to a task type.
//     *
//     * @param child the child to add.
//     * @param task the type.
//     */
//    public void addChild(final InternalOrganization child, final TaskType task) {
//        switch (task) {
//            case ADMIN:
//                adminChildren.add(child);
//                break;
//            case COMMERCIAL:
//                commercialChildren.add(child);
//                break;
//            case SUPPORT:
//                supportChildren.add(child);
//                break;
//            case ADV:
//                advChildren.add(child);
//                break;
//            default:
//                throw new CanopyApplicativeException(getError(task));
//        }
//    }
//
//    /**
//     * Function to remove a child from list of children according to a task type.
//     *
//     * @param child the child to remove.
//     * @param task the type.
//     */
//    public void removeChild(final InternalOrganization child, final TaskType task) {
//        switch (task) {
//            case ADMIN:
//                adminChildren.remove(child);
//                break;
//            case COMMERCIAL:
//                commercialChildren.remove(child);
//                break;
//            case SUPPORT:
//                supportChildren.remove(child);
//                break;
//            case ADV:
//                advChildren.remove(child);
//                break;
//            default:
//                throw new CanopyApplicativeException(getError(task));
//        }
//    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    public Set<InternalRoleEntity> getRoles() {
        return roles;
    }


    public void addRole(final InternalRoleEntity role) {
        role.setOrganization(this);
        roles.add(role);
    }

    public void removeRole(final InternalRoleEntity role) {
        role.setOrganization(null);
        roles.remove(role);
    }

    public Set<CommercializedOffer> getCommercializedOffers() {
        return commercializedOffers;
    }


    public void addCommercializedOffer(final CommercializedOffer commercializedOffer) {
        commercializedOffers.add(commercializedOffer);
        commercializedOffer.setOrganization(this);
    }


    public Set<CommercializedOffer> getDelegatedOffers() {
        return delegatedOffers;
    }


    public void addDelegateOffer(final CommercializedOffer delegatedOffer) {
        delegatedOffer.getDelegatedOrganization().add(this);
        this.delegatedOffers.add(delegatedOffer);
    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Function to get the list of subscriptions attached to that organization according to a task.
     * 
     * @param task .
     * @return .
     */
    public Set<SubscribedOffer> getSubscriptionsWithRole(final TaskType task) {
        final Set<SubscribedOffer> ret;
        switch (task) {
            case ADMIN:
                ret = subscriptionsWithAdminRole;
                break;
            case ADV:
                ret = subscriptionsWithAdvRole;
                break;
            case COMMERCIAL:
                ret = subscriptionsWithCommercialRole;
                break;
            case SUPPORT:
                ret = subscriptionsWithSupportRole;
                break;
            default:
                ret = null;
                break;
        }
        return ret;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Function to add a subscription with a given role.
     * 
     * @param task the role
     * @param subscription the subscription entity
     */
//    public void addSubscriptionWithRole(final TaskType task, final SubscribedOffer subscription) {
//        subscription.setOrganizationCommercial(this);
//        subscriptionsWithCommercialRole.add(subscription);
//        switch (task) {
//            case ADMIN:
//                subscription.setOrganizationAdmin(this);
//                subscriptionsWithAdminRole.add(subscription);
//                break;
//            case ADV:
//                subscription.setOrganizationAdv(this);
//                subscriptionsWithAdvRole.add(subscription);
//                break;
//            case COMMERCIAL:
//                subscription.setOrganizationCommercial(this);
//                subscriptionsWithCommercialRole.add(subscription);
//                break;
//            case SUPPORT:
//                subscription.setOrganizationSupport(this);
//                subscriptionsWithSupportRole.add(subscription);
//                break;
//            default:
//                throw new CanopyApplicativeException("Task " + task + " not managed");
//        }
//    }
}
