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
 * @file AppliTerminal.java
 * @brief
 * @date 2019/09/30
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.ApplicationVersion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_APPLI_TERMINAL", columnNames = {"nomAppli", "terminal_numeroSerie"})
        })
public class AppliTerminal implements Serializable {

    /** . */
    private static final long serialVersionUID = 5910909117493482927L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "terminal_numeroSerie",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK__APPLI_TERMINAL__TERMINAL"),
            referencedColumnName = "numeroSerie")
    private Terminal terminal;

    @Column
    private String versionAppli;

    @Enumerated(EnumType.STRING)
    @Column
    private ApplicationVersion nomAppli;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(final Terminal terminal) {
        this.terminal = terminal;
    }

    public String getVersionAppli() {
        return versionAppli;
    }

    public void setVersionAppli(final String versionAppli) {
        this.versionAppli = versionAppli;
    }

    public ApplicationVersion getNomAppli() {
        return nomAppli;
    }

    public void setNomAppli(final ApplicationVersion nomAppli) {
        this.nomAppli = nomAppli;
    }

    public UUID getPubId() {
        return pubId;
    }

}
