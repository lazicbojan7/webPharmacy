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
@Table(name = "proizvodjac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvodjac.findAll", query = "SELECT p FROM Proizvodjac p")
    , @NamedQuery(name = "Proizvodjac.findByProId", query = "SELECT p FROM Proizvodjac p WHERE p.proId = :proId")
    , @NamedQuery(name = "Proizvodjac.findByProNaziv", query = "SELECT p FROM Proizvodjac p WHERE p.proNaziv = :proNaziv")
    , @NamedQuery(name = "Proizvodjac.findByProTelefon", query = "SELECT p FROM Proizvodjac p WHERE p.proTelefon = :proTelefon")
    , @NamedQuery(name = "Proizvodjac.findByProEmail", query = "SELECT p FROM Proizvodjac p WHERE p.proEmail = :proEmail")})
public class Proizvodjac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_id")
    private Integer proId;
    @Size(max = 45)
    @Column(name = "pro_naziv")
    private String proNaziv;
    @Size(max = 45)
    @Column(name = "pro_telefon")
    private String proTelefon;
    @Size(max = 45)
    @Column(name = "pro_email")
    private String proEmail;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;
    @OneToMany(mappedBy = "proId")
    private List<Artikal> artikalList;

    public Proizvodjac() {
    }

    public Proizvodjac(String proNaziv, String proTelefon, String proEmail, Grad graId) {
        this.proNaziv = proNaziv;
        this.proTelefon = proTelefon;
        this.proEmail = proEmail;
        this.graId = graId;
    }
    

    public Proizvodjac(Integer proId) {
        this.proId = proId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProNaziv() {
        return proNaziv;
    }

    public void setProNaziv(String proNaziv) {
        this.proNaziv = proNaziv;
    }

    public String getProTelefon() {
        return proTelefon;
    }

    public void setProTelefon(String proTelefon) {
        this.proTelefon = proTelefon;
    }

    public String getProEmail() {
        return proEmail;
    }

    public void setProEmail(String proEmail) {
        this.proEmail = proEmail;
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
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvodjac)) {
            return false;
        }
        Proizvodjac other = (Proizvodjac) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Proizvodjac[ proId=" + proId + " ]";
    }
    
}
