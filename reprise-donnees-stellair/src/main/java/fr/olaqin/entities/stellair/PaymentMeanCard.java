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
 * @file PaymentMeanCard.java
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
@DiscriminatorValue("CB")
public class PaymentMeanCard extends AbstractPaymentMean {

    /** . */
    private static final long serialVersionUID = -8324599633485466273L;

    @Column
    private String cardNumber;

    @Column
    private int ccv2;

    @Column
    private int expirationDate;


    // -------------------------------------------------------------------------------------------------------------------------------------
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCcv2() {
        return ccv2;
    }

    public void setCcv2(final int ccv2) {
        this.ccv2 = ccv2;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final int expirationDate) {
        this.expirationDate = expirationDate;
    }


}
