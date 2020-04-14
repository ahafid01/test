/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 * <p>
 * Copyright (c) 2017, Ingenico Healthcare/e-ID.
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
 * @file Terminal.java
 * @brief
 * @date 2017/03/23
 * <p>
 * ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.TypeTerminal;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Terminal.
 *
 * @author kmikheeva
 */
@Entity
public class Terminal implements Serializable {


    /**
     * .
     */
    private static final long serialVersionUID = -660792061757407750L;
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "terminaux")
    private final Set<Customer> users = new LinkedHashSet<>();
    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<AppliTerminal> appliTerminals = new LinkedHashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "terminal_seq")
    @SequenceGenerator(name = "terminal_seq", sequenceName = "terminal_seq")
    private Long id;
    @Version
    private LocalDateTime version;
    @Column(nullable = false, unique = true)
    private String numeroSerie;
    @Column
    private String alias;
    @Enumerated(EnumType.STRING)
    @Column
    private TypeTerminal typeTerminal;
    @Column
    private String frontal;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(final String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Set<Customer> getUsers() {
        return users;
    }

    public LocalDateTime getVersion() {
        return version;
    }

    public void setVersion(final LocalDateTime version) {
        this.version = version;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    public TypeTerminal getTypeTerminal() {
        return typeTerminal;
    }

    public void setTypeTerminal(final TypeTerminal typeTerminal) {
        this.typeTerminal = typeTerminal;
    }

    public String getFrontal() {
        return frontal;
    }

    public void setFrontal(final String frontal) {
        this.frontal = frontal;
    }

    public UUID getPubId() {
        return pubId;
    }
}