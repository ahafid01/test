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
 * @file PairingTerminal.java
 * @brief
 * @date 2019/08/05
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity for pairing the terminal.
 *
 * @author KJOELISON
 *
 */
@Entity
public class PairingTerminalRequest implements Serializable {

    /** . */
    private static final long serialVersionUID = -8954228163495756410L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @Column
    private int identifier;

    @Column
    private LocalDateTime dateTime;

    @Column
    private int challenge;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,
            foreignKey = @ForeignKey(name = "FK__PAIRING_TERMINAL_REQUEST__CUSTOMER"),
            referencedColumnName = "userId")
    private Customer customer;


    // -------------------------------------------------------------------------------------------------------------------------------------
    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final int identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getChallenge() {
        return challenge;
    }

    public void setChallenge(final int challenge) {
        this.challenge = challenge;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public UUID getPubId() {
        return pubId;
    }
}
