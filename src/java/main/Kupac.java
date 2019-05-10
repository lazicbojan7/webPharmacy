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
@Table(name = "kupac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupac.findAll", query = "SELECT k FROM Kupac k")
    , @NamedQuery(name = "Kupac.findByKupId", query = "SELECT k FROM Kupac k WHERE k.kupId = :kupId")
    , @NamedQuery(name = "Kupac.findByKupNaziv", query = "SELECT k FROM Kupac k WHERE k.kupNaziv = :kupNaziv")
    , @NamedQuery(name = "Kupac.findByKupTelefon", query = "SELECT k FROM Kupac k WHERE k.kupTelefon = :kupTelefon")
    , @NamedQuery(name = "Kupac.findByKupEmail", query = "SELECT k FROM Kupac k WHERE k.kupEmail = :kupEmail")})
public class Kupac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kup_id")
    private Integer kupId;
    @Size(max = 45)
    @Column(name = "kup_naziv")
    private String kupNaziv;
    @Size(max = 45)
    @Column(name = "kup_telefon")
    private String kupTelefon;
    @Size(max = 45)
    @Column(name = "kup_email")
    private String kupEmail;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;
    @OneToMany(mappedBy = "kupId")
    private List<Racun> racunList;

    public Kupac() {
    }

    public Kupac(String kupNaziv, String kupTelefon, String kupEmail, Grad graId) {
        this.kupNaziv = kupNaziv;
        this.kupTelefon = kupTelefon;
        this.kupEmail = kupEmail;
        this.graId = graId;
    }
    
    

    public Kupac(Integer kupId) {
        this.kupId = kupId;
    }

    public Integer getKupId() {
        return kupId;
    }

    public void setKupId(Integer kupId) {
        this.kupId = kupId;
    }

    public String getKupNaziv() {
        return kupNaziv;
    }

    public void setKupNaziv(String kupNaziv) {
        this.kupNaziv = kupNaziv;
    }

    public String getKupTelefon() {
        return kupTelefon;
    }

    public void setKupTelefon(String kupTelefon) {
        this.kupTelefon = kupTelefon;
    }

    public String getKupEmail() {
        return kupEmail;
    }

    public void setKupEmail(String kupEmail) {
        this.kupEmail = kupEmail;
    }

    public Grad getGraId() {
        return graId;
    }

    public void setGraId(Grad graId) {
        this.graId = graId;
    }

    @XmlTransient
    public List<Racun> getRacunList() {
        return racunList;
    }

    public void setRacunList(List<Racun> racunList) {
        this.racunList = racunList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kupId != null ? kupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupac)) {
            return false;
        }
        Kupac other = (Kupac) object;
        if ((this.kupId == null && other.kupId != null) || (this.kupId != null && !this.kupId.equals(other.kupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Kupac[ kupId=" + kupId + " ]";
    }
    
}
