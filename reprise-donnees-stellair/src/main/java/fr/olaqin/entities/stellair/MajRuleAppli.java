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
 * @file RuleUpdate.java
 * @brief
 * @date 2019/09/30
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class MajRuleAppli implements Serializable {

    /** . */
    private static final long serialVersionUID = 2847587875645099902L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @Column
    private String numeroSerie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_appli_uuid",
            foreignKey = @ForeignKey(name = "FK__MAJ_RULE__VERSION"))
    private VersionAppli versionAppli;


    // -------------------------------------------------------------------------------------------------------------------------------------
    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(final String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public VersionAppli getVersionAppli() {
        return versionAppli;
    }

    public void setVersionAppli(final VersionAppli versionAppli) {
        this.versionAppli = versionAppli;
    }

    public UUID getPubId() {
        return pubId;
    }

}
