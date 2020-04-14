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
 * @file PaymentMeanLevy.java
 * @brief
 * @date 2019/07/10
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PRELEVEMENT")
public class PaymentMeanLevy extends AbstractPaymentMean {

    /** . */
    private static final long serialVersionUID = 3613218925591610714L;

    @Column
    private String accountHolder;

    @Column(length = 34)
    private String iban;

    @Column(length = 11)
    private String bic;


    // -------------------------------------------------------------------------------------------------------------------------------------
    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(final String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(final String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(final String bic) {
        this.bic = bic;
    }
}
