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
 * @file VersionAppli.java
 * @brief
 * @date 2019/09/30
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.ApplicationVersion;
import fr.olaqin.entities.stellair.enums.ParametreMaj;
import fr.olaqin.entities.stellair.enums.TypeTerminal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class VersionAppli implements Serializable {

    /** . */
    private static final long serialVersionUID = 2057083456345635788L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId = UUID.randomUUID();
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "parametreMaj", joinColumns = @JoinColumn(name = "VersionAppliPubId"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type")
    @Column(name = "value")
    private final Map<ParametreMaj, String> parametres = new LinkedHashMap<>();
    @OneToMany(mappedBy = "versionAppli", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<MajRuleAppli> majRuleApplis = new LinkedHashSet<>();
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeTerminal typeTerminal;
    @Column(nullable = false)
    private String version;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationVersion appli;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public TypeTerminal getTypeTerminal() {
        return typeTerminal;
    }

    public void setTypeTerminal(final TypeTerminal typeTerminal) {
        this.typeTerminal = typeTerminal;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public ApplicationVersion getAppli() {
        return appli;
    }

    public void setAppli(final ApplicationVersion appli) {
        this.appli = appli;
    }

    public UUID getPubId() {
        return pubId;
    }

    public Map<ParametreMaj, String> getParametres() {
        return parametres;
    }

    public Set<MajRuleAppli> getMajRuleApplis() {
        return majRuleApplis;
    }

}
