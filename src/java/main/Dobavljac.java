/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "dobavljac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dobavljac.findAll", query = "SELECT d FROM Dobavljac d")
    , @NamedQuery(name = "Dobavljac.findByDobId", query = "SELECT d FROM Dobavljac d WHERE d.dobId = :dobId")
    , @NamedQuery(name = "Dobavljac.findByDobNaziv", query = "SELECT d FROM Dobavljac d WHERE d.dobNaziv = :dobNaziv")
    , @NamedQuery(name = "Dobavljac.findByDobTelefon", query = "SELECT d FROM Dobavljac d WHERE d.dobTelefon = :dobTelefon")
    , @NamedQuery(name = "Dobavljac.findByDobEmail", query = "SELECT d FROM Dobavljac d WHERE d.dobEmail = :dobEmail")})
public class Dobavljac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dob_id")
    private Integer dobId;
    @Size(max = 45)
    @Column(name = "dob_naziv")
    private String dobNaziv;
    @Size(max = 45)
    @Column(name = "dob_telefon")
    private String dobTelefon;
    @Size(max = 45)
    @Column(name = "dob_email")
    private String dobEmail;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;
    @OneToMany(mappedBy = "dobId")
    private List<Artikal> artikalList;

    public Dobavljac() {
    }

    public Dobavljac(String dobNaziv, String dobTelefon, String dobEmail, Grad graId) {
        this.dobNaziv = dobNaziv;
        this.dobTelefon = dobTelefon;
        this.dobEmail = dobEmail;
        this.graId = graId;
    }
    

    public Dobavljac(Integer dobId) {
        this.dobId = dobId;
    }

    public Integer getDobId() {
        return dobId;
    }

    public void setDobId(Integer dobId) {
        this.dobId = dobId;
    }

    public String getDobNaziv() {
        return dobNaziv;
    }

    public void setDobNaziv(String dobNaziv) {
        this.dobNaziv = dobNaziv;
    }

    public String getDobTelefon() {
        return dobTelefon;
    }

    public void setDobTelefon(String dobTelefon) {
        this.dobTelefon = dobTelefon;
    }

    public String getDobEmail() {
        return dobEmail;
    }

    public void setDobEmail(String dobEmail) {
        this.dobEmail = dobEmail;
    }

    public Grad getGraId() {
        return graId;
    }

    public void setGraId(Grad graId) {
        this.graId = graId;
    }

    @XmlTransient
    public List<Artikal> getArtikalList() {
        return artikalList;
    }

    public void setArtikalList(List<Artikal> artikalList) {
        this.artikalList = artikalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dobId != null ? dobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dobavljac)) {
            return false;
        }
        Dobavljac other = (Dobavljac) object;
        if ((this.dobId == null && other.dobId != null) || (this.dobId != null && !this.dobId.equals(other.dobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Dobavljac[ dobId=" + dobId + " ]";
    }
    
}
