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
@Table(name = "apoteka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apoteka.findAll", query = "SELECT a FROM Apoteka a")
    , @NamedQuery(name = "Apoteka.findByApoId", query = "SELECT a FROM Apoteka a WHERE a.apoId = :apoId")
    , @NamedQuery(name = "Apoteka.findByApoNaziv", query = "SELECT a FROM Apoteka a WHERE a.apoNaziv = :apoNaziv")
    , @NamedQuery(name = "Apoteka.findByApoTelefon", query = "SELECT a FROM Apoteka a WHERE a.apoTelefon = :apoTelefon")
    , @NamedQuery(name = "Apoteka.findByApoEmail", query = "SELECT a FROM Apoteka a WHERE a.apoEmail = :apoEmail")
    , @NamedQuery(name = "Apoteka.findByApoAdresa", query = "SELECT a FROM Apoteka a WHERE a.apoAdresa = :apoAdresa")})
public class Apoteka implements Serializable {

    @OneToMany(mappedBy = "apoId")
    private List<Zaposleni> zaposleniList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apo_id")
    private Integer apoId;
    @Size(max = 45)
    @Column(name = "apo_naziv")
    private String apoNaziv;
    @Size(max = 45)
    @Column(name = "apo_telefon")
    private String apoTelefon;
    @Size(max = 45)
    @Column(name = "apo_email")
    private String apoEmail;
    @Size(max = 45)
    @Column(name = "apo_adresa")
    private String apoAdresa;
    @OneToMany(mappedBy = "apoId")
    private List<Stanje> stanjeList;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;

    public Apoteka() {
    }

    public Apoteka(String apoNaziv, String apoTelefon, String apoEmail, String apoAdresa, Grad graId) {
        this.apoNaziv = apoNaziv;
        this.apoTelefon = apoTelefon;
        this.apoEmail = apoEmail;
        this.apoAdresa = apoAdresa;
        this.graId = graId;
    }
    

    public Apoteka(Integer apoId) {
        this.apoId = apoId;
    }

    public Integer getApoId() {
        return apoId;
    }

    public void setApoId(Integer apoId) {
        this.apoId = apoId;
    }

    public String getApoNaziv() {
        return apoNaziv;
    }

    public void setApoNaziv(String apoNaziv) {
        this.apoNaziv = apoNaziv;
    }

    public String getApoTelefon() {
        return apoTelefon;
    }

    public void setApoTelefon(String apoTelefon) {
        this.apoTelefon = apoTelefon;
    }

    public String getApoEmail() {
        return apoEmail;
    }

    public void setApoEmail(String apoEmail) {
        this.apoEmail = apoEmail;
    }

    public String getApoAdresa() {
        return apoAdresa;
    }

    public void setApoAdresa(String apoAdresa) {
        this.apoAdresa = apoAdresa;
    }

    @XmlTransient
    public List<Stanje> getStanjeList() {
        return stanjeList;
    }

    public void setStanjeList(List<Stanje> stanjeList) {
        this.stanjeList = stanjeList;
    }

    public Grad getGraId() {
        return graId;
    }

    public void setGraId(Grad graId) {
        this.graId = graId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apoId != null ? apoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apoteka)) {
            return false;
        }
        Apoteka other = (Apoteka) object;
        if ((this.apoId == null && other.apoId != null) || (this.apoId != null && !this.apoId.equals(other.apoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Apoteka[ apoId=" + apoId + " ]";
    }

    @XmlTransient
    public List<Zaposleni> getZaposleniList() {
        return zaposleniList;
    }

    public void setZaposleniList(List<Zaposleni> zaposleniList) {
        this.zaposleniList = zaposleniList;
    }
    
}
