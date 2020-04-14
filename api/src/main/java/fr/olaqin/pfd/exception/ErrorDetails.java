package fr.olaqin.pfd.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

public class ErrorDetails {
    private String champ;

    private String message;

    private Object valeur;

    private String description;

    private String champAssocie;

    private Object[] args;

    public ErrorDetails(String champ, String message, Object valeur, String description) {
        this(champ, message, valeur, description, StringUtils.EMPTY, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

    public ErrorDetails(String champ, String message, Object valeur, String description, String champAssocie, Object[] args) {
        this.champ = champ;
        this.message = message;
        this.valeur = valeur;
        this.description = description;
        this.champAssocie = champAssocie;
        this.args = args;
    }

    public ErrorDetails() {
    }

    public String getChamp() {
        return champ;
    }

    public ErrorDetails setChamp(String champ) {
        this.champ = champ;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getValeur() {
        return valeur;
    }

    public ErrorDetails setValeur(Object valeur) {
        this.valeur = valeur;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ErrorDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getChampAssocie() {
        return champAssocie;
    }

    public ErrorDetails setChampAssocie(String champAssocie) {
        this.champAssocie = champAssocie;
        return this;
    }

    public Object[] getArgs() {
        return args;
    }

    public ErrorDetails setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ErrorDetails that = (ErrorDetails) o;

        return new EqualsBuilder()
                .append(champ, that.champ)
                .append(message, that.message)
                .append(valeur, that.valeur)
                .append(description, that.description)
                .append(champAssocie, that.champAssocie)
                .append(args, that.args)
                .isEquals();
    }

    @Override public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(champ)
                .append(message)
                .append(valeur)
                .append(description)
                .append(champAssocie)
                .append(args)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "champ='" + champ + '\'' +
                ", message='" + message + '\'' +
                ", valeur=" + valeur +
                ", description='" + description + '\'' +
                ", champAssocie='" + champAssocie + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
