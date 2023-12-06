package sn.neos.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener.class)
class Utilisateur {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String email;
    private String intitule
    private String adresse   // ou siege sociale pour une entreprise
    private String sexe
    private Date dateDeNaissance
    private String lieuNaissance
    private String nationalite
    private String paysNaissance
    private String paysResidence
    private String professionPersonne
    private String description
    private String ville
    private String telMobile
    private String telBureau
    private String telDomicile
    private String fax

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;

    private byte[] photo // ou logo pour une entreprise
    private String photoType // png,jepg,jpg...etc

    /*  Informations Informations Informations  */
    private boolean enabled = true
    private boolean accountExpired = false
    private boolean accountLocked = false
    private boolean passwordExpired = false
    private Boolean reinitPassword = false

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

    String getId() {
        return id
    }

    String getNom() {
        return nom
    }

    void setNom(String nom) {
        this.nom = nom
    }

    String getPrenom() {
        return prenom
    }

    void setPrenom(String prenom) {
        this.prenom = prenom
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getIntitule() {
        return intitule
    }

    void setIntitule(String intitule) {
        this.intitule = intitule
    }

    String getAdresse() {
        return adresse
    }

    void setAdresse(String adresse) {
        this.adresse = adresse
    }

    String getSexe() {
        return sexe
    }

    void setSexe(String sexe) {
        this.sexe = sexe
    }

    Date getDateDeNaissance() {
        return dateDeNaissance
    }

    void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance
    }

    String getLieuNaissance() {
        return lieuNaissance
    }

    void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance
    }

    String getNationalite() {
        return nationalite
    }

    void setNationalite(String nationalite) {
        this.nationalite = nationalite
    }

    String getPaysNaissance() {
        return paysNaissance
    }

    void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance
    }

    String getPaysResidence() {
        return paysResidence
    }

    void setPaysResidence(String paysResidence) {
        this.paysResidence = paysResidence
    }

    String getProfessionPersonne() {
        return professionPersonne
    }

    void setProfessionPersonne(String professionPersonne) {
        this.professionPersonne = professionPersonne
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getVille() {
        return ville
    }

    void setVille(String ville) {
        this.ville = ville
    }

    String getTelMobile() {
        return telMobile
    }

    void setTelMobile(String telMobile) {
        this.telMobile = telMobile
    }

    String getTelBureau() {
        return telBureau
    }

    void setTelBureau(String telBureau) {
        this.telBureau = telBureau
    }

    String getTelDomicile() {
        return telDomicile
    }

    void setTelDomicile(String telDomicile) {
        this.telDomicile = telDomicile
    }

    String getFax() {
        return fax
    }

    void setFax(String fax) {
        this.fax = fax
    }

    byte[] getPhoto() {
        return photo
    }

    void setPhoto(byte[] photo) {
        this.photo = photo
    }

    String getPhotoType() {
        return photoType
    }

    void setPhotoType(String photoType) {
        this.photoType = photoType
    }

    boolean getEnabled() {
        return enabled
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled
    }

    boolean getAccountExpired() {
        return accountExpired
    }

    void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired
    }

    boolean getAccountLocked() {
        return accountLocked
    }

    void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked
    }

    boolean getPasswordExpired() {
        return passwordExpired
    }

    void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired
    }

    Boolean getReinitPassword() {
        return reinitPassword
    }

    void setReinitPassword(Boolean reinitPassword) {
        this.reinitPassword = reinitPassword
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

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
