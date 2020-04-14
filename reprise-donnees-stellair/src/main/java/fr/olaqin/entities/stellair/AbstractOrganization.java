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
 * @file AbstractOrganization.java
 * @brief
 * @date 2019/11/12
 * <p>
 * **************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.SpecialOrganization;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "abstract_organization")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractOrganization implements Serializable { //, Comparable<AbstractOrganization> {

    /** . */
    private static final long serialVersionUID = 3622958078732485206L;

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 100)
    private String alias;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public AbstractOrganization() {
        pubId = UUID.randomUUID();
    }

    public AbstractOrganization(final SpecialOrganization org) {
        pubId = org.getId();
        name = org.getName();
    }

    public AbstractOrganization(final AbstractUserEntity user) {
        pubId = user.getPubId();
        name = user.getLogin();
    }

//    @Override
//    public int compareTo(final AbstractOrganization other) {
//        final int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getName(), other.getName());
//        }
//        return ret;
//    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public UUID getPubId() {
        return pubId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public String getAlias() {
        return alias;
    }


    public void setAlias(final String alias) {
        this.alias = alias;
    }
}
