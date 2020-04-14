/**
 * **************************************************************************
 * --------------------------------------------------------------------------
 * INGENICO HEALTHCARE DEVELOPMENT TEAM
 * --------------------------------------------------------------------------
 *
 * Copyright (c) 2017, Ingenico Healthcare/e-ID.
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
 * @file User.java
 * @brief
 * @date 2017/03/23
 *
 ****************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.AccountState;
import fr.olaqin.entities.stellair.enums.CanopyUserInfo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * Canopy User.
 *
 * @author kmikheeva
 *
 */
@Entity
@Table(name = "USERCANOPY")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractUserEntity implements Serializable { //, Comparable<AbstractUserEntity> {

    /** Serial version Id. */
    private static final long serialVersionUID = 4257290912373840543L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    private final Long id;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private final UUID pubId;

    @Version
    private LocalDateTime version;

    @Column(nullable = false, unique = true)
    private String login;

    @Enumerated(EnumType.STRING)
    private AccountState state;

    @Column(nullable = false)
    private String password;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String email;

    @Column
    private String internalEmail;

    @Column
    private String frontal;

    @Column
    private LocalDateTime lastConnexion;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public AbstractUserEntity() {
        id = null;
        pubId = UUID.randomUUID();
    }

    public AbstractUserEntity(final CanopyUserInfo user) {
        id = user.getId();
        pubId = user.getUuid();
    }

//    @Override
//    public int compareTo(final AbstractUserEntity other) {
//        int ret;
//        if (other == null) {
//            ret = 1;
//        } else {
//            ret = ObjectHelper.compare(getLastName(), other.getLastName());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getFirstName(), other.getFirstName());
//        }
//
//        if (ret == 0) {
//            ret = ObjectHelper.compare(getLogin(), other.getLogin());
//        }
//        return ret;
//    }


    // -------------------------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public UUID getPubId() {
        return pubId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public AccountState getState() {
        return state;
    }

    public void setState(final AccountState state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getInternalEmail() {
        return internalEmail;
    }

    public void setInternalEmail(final String internalEmail) {
        this.internalEmail = internalEmail;
    }

    public LocalDateTime getVersion() {
        return version;
    }

    public void setVersion(final LocalDateTime version) {
        this.version = version;
    }

    public String getFrontal() {
        return frontal;
    }

    public void setFrontal(final String frontal) {
        this.frontal = frontal;
    }

    public LocalDateTime getLastConnexion() {
        return lastConnexion;
    }

    public void setLastConnexion(final LocalDateTime lastConnexion) {
        this.lastConnexion = lastConnexion;
    }

//    public abstract AbstractOrganization getCurrentOrganization();
//
//    public abstract void refreshCurrentOrganization();

}
