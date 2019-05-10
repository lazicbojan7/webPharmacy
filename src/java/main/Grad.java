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
@Table(name = "grad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grad.findAll", query = "SELECT g FROM Grad g")
    , @NamedQuery(name = "Grad.findByGraId", query = "SELECT g FROM Grad g WHERE g.graId = :graId")
    , @NamedQuery(name = "Grad.findByGraNaziv", query = "SELECT g FROM Grad g WHERE g.graNaziv = :graNaziv")})
public class Grad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gra_id")
    private Integer graId;
    @Size(max = 45)
    @Column(name = "gra_naziv")
    private String graNaziv;
    @OneToMany(mappedBy = "graId")
    private List<Kupac> kupacList;
    @OneToMany(mappedBy = "graId")
    private List<Dobavljac> dobavljacList;
    @OneToMany(mappedBy = "graId")
    private List<Proizvodjac> proizvodjacList;
    @OneToMany(mappedBy = "graId")
    private List<Zaposleni> zaposleniList;
    @OneToMany(mappedBy = "graId")
    private List<Apoteka> apotekaList;

    public Grad() {
    }

    public Grad(Integer graId) {
        this.graId = graId;
    }

    public Integer getGraId() {
        return graId;
    }

    public void setGraId(Integer graId) {
        this.graId = graId;
    }

    public String getGraNaziv() {
        return graNaziv;
    }

    public void setGraNaziv(String graNaziv) {
        this.graNaziv = graNaziv;
    }

    @XmlTransient
    public List<Kupac> getKupacList() {
        return kupacList;
    }

    public void setKupacList(List<Kupac> kupacList) {
        this.kupacList = kupacList;
    }

    @XmlTransient
    public List<Dobavljac> getDobavljacList() {
        return dobavljacList;
    }

    public void setDobavljacList(List<Dobavljac> dobavljacList) {
        this.dobavljacList = dobavljacList;
    }

    @XmlTransient
    public List<Proizvodjac> getProizvodjacList() {
        return proizvodjacList;
    }

    public void setProizvodjacList(List<Proizvodjac> proizvodjacList) {
        this.proizvodjacList = proizvodjacList;
    }

    @XmlTransient
    public List<Zaposleni> getZaposleniList() {
        return zaposleniList;
    }

    public void setZaposleniList(List<Zaposleni> zaposleniList) {
        this.zaposleniList = zaposleniList;
    }

    @XmlTransient
    public List<Apoteka> getApotekaList() {
        return apotekaList;
    }

    public void setApotekaList(List<Apoteka> apotekaList) {
        this.apotekaList = apotekaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (graId != null ? graId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grad)) {
            return false;
        }
        Grad other = (Grad) object;
        if ((this.graId == null && other.graId != null) || (this.graId != null && !this.graId.equals(other.graId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Grad[ graId=" + graId + " ]";
    }
    
}
