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
 * @file OffreCommerciale.java
 * @brief
 * @date 2019/03/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CommercialOfferDetails;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UK_COMMERCIAL_OFFER_DETAILS", columnNames = { "details" }) })
public class CommercialOffer implements Serializable { //}, Comparable<CommercialOffer> {

    /** . */
    private static final long serialVersionUID = -6095026570513818103L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commercial_offer_seq")
    @SequenceGenerator(name = "commercial_offer_seq", sequenceName = "commercial_offer_seq")
    private Long id;

    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommercialOfferDetails details;


    @OneToMany(mappedBy = "commercialOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<CommercializedOffer> sales = new LinkedHashSet<>();


    // -------------------------------------------------------------------------------------------------------------------------------------
    public CommercialOffer() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
//    @Override
//    public int compareTo(final CommercialOffer other) {
//        final int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getDetails(), other.getDetails());
//        }
//
//        return ret;
//    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }


    public void setId(final Long id) {
        this.id = id;
    }


    public CommercialOfferDetails getDetails() {
        return details;
    }


    public void setDetails(final CommercialOfferDetails details) {
        this.details = details;
    }


    public Set<CommercializedOffer> getSales() {
        return sales;
    }


    public UUID getPubId() {
        return pubId;
    }
}

