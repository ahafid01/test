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
 * @file Airvantage.java
 * @brief
 * @date 2019/08/14
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;


import fr.olaqin.entities.stellair.enums.OperationStatus;
import fr.olaqin.entities.stellair.enums.SimStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Airvantage implements Serializable {

    /** . */
    private static final long serialVersionUID = -5297643540830015634L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @Column
    private String imei;

    @Column
    private String sim;

    @Enumerated(EnumType.STRING)
    @Column
    private SimStatus status;

    @Column
    private String operation;

    @Enumerated(EnumType.STRING)
    @Column
    private OperationStatus operationStatus;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public UUID getPubId() {
        return pubId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(final String imei) {
        this.imei = imei;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(final String sim) {
        this.sim = sim;
    }

    public SimStatus getStatus() {
        return status;
    }

    public void setStatus(final SimStatus status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(final OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
