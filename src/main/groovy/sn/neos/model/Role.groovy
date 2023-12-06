package sn.neos.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name="roles")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String intitule;

    private String userCreate
    private String userUpdate

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date",nullable = false, updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date lastModifiedDate;

    Role() {
    }

    String getId() {
        return id
    }

    String getIntitule() {
        return intitule
    }

    void setIntitule(String intitule) {
        this.intitule = intitule
    }

    String getUserCreate() {
        return userCreate
    }

    void setUserCreate(String userCreate) {
        this.userCreate = userCreate
    }

    String getUserUpdate() {
        return userUpdate
    }

    void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate
    }

    Date getCreatedDate() {
        return createdDate
    }

    void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate
    }

    Date getLastModifiedDate() {
        return lastModifiedDate
    }

    void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate
    }
}