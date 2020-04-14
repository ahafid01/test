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
 * @file Customer.java
 * @brief
 * @date 2019/03/18
 *
 ***************************************************************************
 */

package fr.olaqin.entities.stellair;

import fr.olaqin.entities.stellair.enums.CanopyUserInfo;
import fr.olaqin.entities.stellair.enums.Tutorial;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.UniqueConstraint;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("CUSTOMER")
public class Customer extends AbstractUserEntity implements Serializable {

    /** . */
    private static final long serialVersionUID = -9086147763978793060L;

    @Column
    private LocalDate birthDate;

    @Column(length = 13)
    private String phoneNumber1;

    @Column(length = 13)
    private String phoneNumber2;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CustomerTerminaux",
               joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
               inverseJoinColumns = @JoinColumn(name = "terminal_id", referencedColumnName = "id"),
               foreignKey = @ForeignKey(name = "FK__UTILISATION_TERMINAUX__USER"),
               inverseForeignKey = @ForeignKey(name = "FK__UTILISATION_TERMINAUX__TERMINAUX"),
               uniqueConstraints = @UniqueConstraint(name = "UK_UTILISATION_TERMINAUX", columnNames = { "userId", "terminal_id" }),
               indexes = { @Index(name = "index_utilisation_terminal", columnList = "terminal_id") })
    @OrderBy("numeroSerie")
    private Set<Terminal> terminaux = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__CUSTOMER__TERMINAL_SELECTED"))
    private Terminal terminalSelected;

    @Column
    private String urlCallBack;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "billing_address_uuid",
                foreignKey = @ForeignKey(name = "FK__CUSTOMER__BILLING_ADDRESS"))
    private Address billingAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "delivery_address_uuid",
                foreignKey = @ForeignKey(name = "FK__CUSTOMER__DELIVERY_ADDRESS"))
    private Address deliveryAddress;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_mean_uuid",
                foreignKey = @ForeignKey(name = "FK__CUSTOMER__PAYMENT_MEAN"))
    private AbstractPaymentMean paymentMean;


    @ElementCollection(targetClass = Tutorial.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "tutorialToShow",
                     joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, updatable = false,
                                               foreignKey = @ForeignKey(name = "FK__TUTO__USER")),
                     uniqueConstraints = @UniqueConstraint(name = "UK_TUTO", columnNames = { "userId", "tutorial" }),
                     indexes = { @Index(name = "index_tutorials", columnList = "userId") })
    @Column(name = "tutorial", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Set<Tutorial> tutorialsToShow = new LinkedHashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("organization,role")
    private final Set<CustomerRoleEntity> roles = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK__CUSTOMER__CURRENT_ORG"),
                name = "current_organization_pub_id",
                referencedColumnName = "pubId")
    private CustomerOrganization currentOrganization;

    // -------------------------------------------------------------------------------------------------------------------------------------
    public Customer() {
        super();
    }

    public Customer(final CanopyUserInfo user) {
        super(user);
    }

    // -------------------------------------------------------------------------------------------------------------------------------------
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(final String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(final String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Set<Terminal> getTerminaux() {
        return terminaux;
    }

    public void setTerminaux(final Set<Terminal> terminaux) {
        this.terminaux = terminaux;
    }

    /**
     * .
     *
     * @param terminal .
     */
    public void addTerminal(final Terminal terminal) {
        if (this.terminaux.stream().noneMatch(t -> t.getId().equals(terminal.getId()))) {
            final List<Terminal> list = new ArrayList<>(terminaux);
            list.add(terminal);

            list.sort((term1, term2) -> term1.getNumeroSerie().compareTo(term2.getNumeroSerie()));
            terminaux.clear();
            terminaux.addAll(list);
        }
        if (terminal.getUsers().stream().noneMatch(c -> c.getId().equals(this.getId()))) {
            terminal.getUsers().add(this);
        }
    }

    public Terminal getTerminalSelected() {
        return terminalSelected;
    }

    public void setTerminalSelected(final Terminal terminalSelected) {
        this.terminalSelected = terminalSelected;
    }


    public String getUrlCallBack() {
        return urlCallBack;
    }

    public void setUrlCallBack(final String urlCallBack) {
        this.urlCallBack = urlCallBack;
    }


    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * Function allowing to bind an address and a customer.
     *
     * @param address the address
     */
    public void setBillingAddress(final Address address) {
        this.billingAddress = address;
        if (address != null) {
            address.setCustomer(this);
        }
    }

    /**
     * Function allowing to set the deliveryAddress to null.
     */
    public void removeBillingAddress() {
        if (this.billingAddress != null) {
            this.billingAddress.setCustomer(null);
        }
        this.billingAddress = null;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Function allowing to bind an address and a customer.
     *
     * @param address the address
     */
    public void setDeliveryAddress(final Address address) {
        this.deliveryAddress = address;
        if (address != null) {
            address.setCustomer(this);
        }
    }

    /**
     * Function allowing to set the deliveryAddress to null.
     */
    public void removeDeliveryAddress() {
        if (this.deliveryAddress != null) {
            this.deliveryAddress.setCustomer(null);
        }
        this.deliveryAddress = null;
    }


    public AbstractPaymentMean getPaymentMean() {
        return paymentMean;
    }

    /**
     * Function allowing to bind a payment mean and a customer.
     *
     * @param paymentMean the payment Mean
     */
    public void setPaymentMean(final AbstractPaymentMean paymentMean) {
        this.paymentMean = paymentMean;
        if (paymentMean != null) {
            paymentMean.setCustomer(this);
        }
    }

    /**
     * Function allowing to set the paymentMean to null.
     */
    public void removePaymentMean() {
        if (this.paymentMean != null) {
            this.paymentMean.setCustomer(null);
        }
        this.paymentMean = null;
    }


    public Set<Tutorial> getTutorialsToShow() {
        return tutorialsToShow;
    }


    public Set<CustomerRoleEntity> getRoles() {
        return roles;
    }


    public void addRole(final CustomerRoleEntity role) {
        role.setUser(this);
        roles.add(role);
    }


    public void removeRole(final CustomerRoleEntity role) {
        role.setUser(null);
        roles.remove(role);
    }

    //@Override
    public CustomerOrganization getCurrentOrganization() {
        return currentOrganization;
    }

    //@Override
    public void refreshCurrentOrganization() {
        // Ces lignes causent un org.springframework.orm.ObjectOptimisticLockingFailureException
        if (roles.stream().noneMatch(r -> r.getOrganization().equals(currentOrganization))) {
            if (roles.isEmpty()) {
                setCurrentOrganization(null);
            } else {
                setCurrentOrganization(roles.iterator().next().getOrganization());
            }
        }
    }

    public void setCurrentOrganization(final CustomerOrganization currentOrganization) {
        this.currentOrganization = currentOrganization;
    }
}
