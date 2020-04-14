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
 * @file SubscribedCommercialOffer.java
 * @brief
 * @date 2019/03/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CommercialOptions;
import fr.olaqin.entities.stellair.enums.RoleState;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SubscribedCommercialOffer",
       indexes = {
           @Index(name = "index_subscribed_commercial_offer__offer__organization",
                  columnList = "commercialized_offer_id,organization_pub_id"),
           @Index(name = "index_subscribed_commercial_offer__commercial_org__organization",
                  columnList = "organization_commercial_pub_id,organization_pub_id"),
           @Index(name = "index_subscribed_commercial_offer__suppport_org__organization",
                  columnList = "organization_support_pub_id,organization_pub_id"),
           @Index(name = "index_subscribed_commercial_offer__admin_org__organization",
                  columnList = "organization_admin_pub_id,organization_pub_id"),
           @Index(name = "index_subscribed_commercial_offer__adv_org__organization",
                  columnList = "organization_adv_pub_id,organization_pub_id")
       })
public class SubscribedOffer implements Serializable { //}, Comparable<SubscribedOffer> {

    /** . */
    private static final long serialVersionUID = 8354232740162888035L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscribed_commercial_offer_seq")
    @SequenceGenerator(name = "subscribed_commercial_offer_seq", sequenceName = "subscribed_commercial_offer_seq")
    private Long id;

    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__ORGANIZATION"),
                name = "organization_pub_id")
    private CustomerOrganization organization;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__COMMERCIALIZED_OFFER"))
    private CommercializedOffer commercializedOffer;


    @ElementCollection(targetClass = CommercialOptions.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "subscribtionOptions",
                     joinColumns = @JoinColumn(name = "subscribtionId"),
                     foreignKey = @ForeignKey(name = "FK__SUBSCRIBTION_OPTION__SUBSCRIBED_OFFER"))
    @Column(name = "options", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Set<CommercialOptions> options = new LinkedHashSet<>();

    @Column
    private boolean confirmedByCustomer;

    @Column
    private String contractNumber;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private RoleState state;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__COMMERCIAL_ORG"),
                name = "organization_commercial_pub_id",
                referencedColumnName = "pubId")
    private InternalOrganization organizationCommercial;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__SUPPORT_ORG"),
                name = "organization_support_pub_id",
                referencedColumnName = "pubId")
    private InternalOrganization organizationSupport;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__ADMIN_ORG"),
                name = "organization_admin_pub_id",
                referencedColumnName = "pubId")
    private InternalOrganization organizationAdmin;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false,
                foreignKey = @ForeignKey(name = "FK__SUBSCRIBED_COMMERCIAL_OFFER__ADV_ORG"),
                name = "organization_adv_pub_id",
                referencedColumnName = "pubId")
    private InternalOrganization organizationAdv;



//    // -------------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public int compareTo(final SubscribedOffer other) {
//        int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getOrganization(), other.getOrganization());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getCommercializedOffer(), other.getCommercializedOffer());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getStartDate(), other.getStartDate());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getEndDate(), other.getEndDate());
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

    public CustomerOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(final CustomerOrganization organization) {
        this.organization = organization;
    }

    public CommercializedOffer getCommercializedOffer() {
        return commercializedOffer;
    }

    public void setCommercializedOffer(final CommercializedOffer commercializedOffer) {
        this.commercializedOffer = commercializedOffer;
    }

    public Set<CommercialOptions> getOptions() {
        return options;
    }

    public boolean isConfirmedByCustomer() {
        return confirmedByCustomer;
    }

    public void setConfirmedByCustomer(final boolean confirmedByCustomer) {
        this.confirmedByCustomer = confirmedByCustomer;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(final String contractNumber) {
        this.contractNumber = contractNumber;
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


    public RoleState getState() {
        return state;
    }

    public void setState(final RoleState state) {
        this.state = state;
    }

    public InternalOrganization getOrganizationCommercial() {
        return organizationCommercial;
    }

    public void setOrganizationCommercial(final InternalOrganization organizationCommercial) {
        this.organizationCommercial = organizationCommercial;
    }

    public InternalOrganization getOrganizationSupport() {
        return organizationSupport;
    }

    public void setOrganizationSupport(final InternalOrganization organizationSupport) {
        this.organizationSupport = organizationSupport;
    }

    public InternalOrganization getOrganizationAdmin() {
        return organizationAdmin;
    }

    public void setOrganizationAdmin(final InternalOrganization organizationAdmin) {
        this.organizationAdmin = organizationAdmin;
    }

    public InternalOrganization getOrganizationAdv() {
        return organizationAdv;
    }

    public void setOrganizationAdv(final InternalOrganization organizationAdv) {
        this.organizationAdv = organizationAdv;
    }
}
