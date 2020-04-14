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
 * @file Adress.java
 * @brief
 * @date 2019/07/08
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "ADDRESS",
        indexes = {@Index(name = "index_address_customer", columnList = "customer_id")})
public class Address implements Serializable {

    /** . */
    private static final long serialVersionUID = -6816272285122380778L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK__ADRESS__CUSTOMER"),
            referencedColumnName = "userId")
    private Customer customer;

    @Column
    private String fullName;

    @Column
    private String line1;

    @Column
    private String line2;

    @Column
    private int zipCode;

    @Column
    private String city;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(final String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(final String line2) {
        this.line2 = line2;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(final int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public UUID getPubId() {
        return pubId;
    }

}
