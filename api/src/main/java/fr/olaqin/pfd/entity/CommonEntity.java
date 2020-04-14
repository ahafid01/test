package fr.olaqin.pfd.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import fr.olaqin.pfd.enums.converter.LocalDateTimeConverter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.ZoneId;

@DynamoDBDocument
public abstract class CommonEntity {

    @CreatedDate
    @DynamoDBAttribute(attributeName = "CreatedDate")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    protected LocalDateTime createdDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));

    @CreatedBy
    @DynamoDBAttribute(attributeName = "CreatedBy")
    protected String createdBy;

    @LastModifiedBy
    @DynamoDBAttribute(attributeName = "LastModifiedBy")
    protected String lastModifiedBy;

    @LastModifiedDate
    @DynamoDBAttribute(attributeName = "LastModifiedDate")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    protected LocalDateTime lastModifiedDate = LocalDateTime.now(ZoneId.of("Europe/Paris"));

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public CommonEntity setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CommonEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public CommonEntity setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public CommonEntity setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }
}