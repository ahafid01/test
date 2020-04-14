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
 * @file CommercializedOffer.java
 * @brief
 * @date 2019/05/20
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CommercialOptions;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;


@Entity
public class CommercializedOffer implements Serializable { //}, Comparable<CommercializedOffer> {

    /**
     * .
     */
    private static final long serialVersionUID = 1192797645205409828L;
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();
    @ElementCollection(targetClass = CommercialOptions.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "CommercializedOfferOptions", joinColumns = @JoinColumn(name = "commercializedOfferId"))
    @Column(name = "opt", nullable = false)   // <- Nom de la colonne dans la table CommercializedOfferOptions
    @Enumerated(EnumType.STRING)
    @OrderBy("opt")
    private final SortedSet<CommercialOptions> options = new TreeSet<>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "delegatedOffers")
    @OrderBy("name")
    private final SortedSet<InternalOrganization> delegatedOrganization = new TreeSet<>();
    @OneToMany(mappedBy = "commercializedOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id")
    private final SortedSet<SubscribedOffer> subscriptions = new TreeSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commercialized_offer_seq")
    @SequenceGenerator(name = "commercialized_offer_seq", sequenceName = "commercialized_offer_seq")
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_COMMERCIAL_OFFER__ORGANIZATION"), name = "organization_pub_id",
            referencedColumnName = "pubId")
    private InternalOrganization organization;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
            foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_OFFER__COMMERCIAL_OFFER"),
            referencedColumnName = "id")
    private CommercialOffer commercialOffer;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_OFFER__DEFAULT_COMMERCIAL_PARENT"),
            name = "default_commercial_parent_pub_id",
            referencedColumnName = "pubId")
    private InternalOrganization defaultCommercialParent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_OFFER__DEFAULT_SUPPORT_PARENT"), name = "default_support_parent_pub_id",
            referencedColumnName = "pubId")
    private InternalOrganization defaultSupportParent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_OFFER__DEFAULT_ADMIN_PARENT"), name = "default_admin_parent_pub_id",
            referencedColumnName = "pubId")
    private InternalOrganization defaultAdminParent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__COMMERCIALIZED_OFFER__DEFAULT_ADV_PARENT"), name = "default_adv_parent_pub_id",
            referencedColumnName = "pubId")
    private InternalOrganization defaultAdvParent;


    // -------------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public int compareTo(final CommercializedOffer other) {
//        int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getCommercialOffer(), other.getCommercialOffer());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getId(), other.getId());
//        }
//        return ret;
//    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UUID getPubId() {
        return pubId;
    }

    public InternalOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(final InternalOrganization organization) {
        this.organization = organization;
    }

    public CommercialOffer getCommercialOffer() {
        return commercialOffer;
    }

    public void setCommercialOffer(final CommercialOffer commercialOffer) {
        this.commercialOffer = commercialOffer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<SubscribedOffer> getSubscriptions() {
        return subscriptions;
    }

    public Set<CommercialOptions> getOptions() {
        return options;
    }

    public Set<InternalOrganization> getDelegatedOrganization() {
        return delegatedOrganization;
    }

//    /**
//     * Function allowing to get the default parent corresponding to a task.
//     *
//     * @param task .
//     * @return .
//     */
//    public InternalOrganization getDefaultParent(final TaskType task) {
//        final InternalOrganization ret;
//        switch (task) {
//            case ADMIN:
//                ret = defaultAdminParent;
//                break;
//            case ADV:
//                ret = defaultAdvParent;
//                break;
//            case COMMERCIAL:
//                ret = defaultCommercialParent;
//                break;
//            case SUPPORT:
//                ret = defaultSupportParent;
//                break;
//            default:
//                ret = null;
//                break;
//        }
//        return ret;
//    }
//
//    /**
//     * Function allowing to set the default parent corresponding to a task.
//     *
//     * @param task .
//     * @param defaultParent .
//     */
//    public void setDefaultParent(final TaskType task, final InternalOrganization defaultParent) {
//        switch (task) {
//            case ADMIN:
//                defaultAdminParent = defaultParent;
//                break;
//            case ADV:
//                defaultAdvParent = defaultParent;
//                break;
//            case COMMERCIAL:
//                defaultCommercialParent = defaultParent;
//                break;
//            case SUPPORT:
//                defaultSupportParent = defaultParent;
//                break;
//            default:
//                throw new CanopyApplicativeException("Task type " + task + " not handled");
//        }
//    }
}
