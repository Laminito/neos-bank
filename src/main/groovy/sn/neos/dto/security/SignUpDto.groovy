package sn.neos.dto.security

class SignUpDto {
        private String nom;
        private String prenom;
        private String username;
        private String email;
        private String password;
        private String intitule
        private String adresse
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
        private byte[] photo
        private String photoType
        public SignUpDto() {}

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

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
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
}
