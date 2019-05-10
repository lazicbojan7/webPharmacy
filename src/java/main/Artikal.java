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
import javax.persistence.Lob;
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
@Table(name = "artikal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a")
    , @NamedQuery(name = "Artikal.findByArtId", query = "SELECT a FROM Artikal a WHERE a.artId = :artId")
    , @NamedQuery(name = "Artikal.findByArtNaziv", query = "SELECT a FROM Artikal a WHERE a.artNaziv = :artNaziv")
    , @NamedQuery(name = "Artikal.findByArtTip", query = "SELECT a FROM Artikal a WHERE a.artTip = :artTip")
    , @NamedQuery(name = "Artikal.findByArtCena", query = "SELECT a FROM Artikal a WHERE a.artCena = :artCena")})
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "art_id")
    private Integer artId;
    @Size(max = 45)
    @Column(name = "art_naziv")
    private String artNaziv;
    @Size(max = 45)
    @Column(name = "art_tip")
    private String artTip;
    @Lob
    @Size(max = 65535)
    @Column(name = "art_opis")
    private String artOpis;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "art_cena")
    private Double artCena;
    @OneToMany(mappedBy = "artId")
    private List<Stanje> stanjeList;
    @JoinColumn(name = "dob_id", referencedColumnName = "dob_id")
    @ManyToOne
    private Dobavljac dobId;
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    @ManyToOne
    private Proizvodjac proId;

    public Artikal() {
    }

    public Artikal(String artNaziv, String artTip, String artOpis, Double artCena, Dobavljac dobId, Proizvodjac proId) {
        this.artNaziv = artNaziv;
        this.artTip = artTip;
        this.artOpis = artOpis;
        this.artCena = artCena;
        this.dobId = dobId;
        this.proId = proId;
    }
    

    public Artikal(Integer artId) {
        this.artId = artId;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public String getArtNaziv() {
        return artNaziv;
    }

    public void setArtNaziv(String artNaziv) {
        this.artNaziv = artNaziv;
    }

    public String getArtTip() {
        return artTip;
    }

    public void setArtTip(String artTip) {
        this.artTip = artTip;
    }

    public String getArtOpis() {
        return artOpis;
    }

    public void setArtOpis(String artOpis) {
        this.artOpis = artOpis;
    }

    public Double getArtCena() {
        return artCena;
    }

    public void setArtCena(Double artCena) {
        this.artCena = artCena;
    }

    @XmlTransient
    public List<Stanje> getStanjeList() {
        return stanjeList;
    }

    public void setStanjeList(List<Stanje> stanjeList) {
        this.stanjeList = stanjeList;
    }

    public Dobavljac getDobId() {
        return dobId;
    }

    public void setDobId(Dobavljac dobId) {
        this.dobId = dobId;
    }

    public Proizvodjac getProId() {
        return proId;
    }

    public void setProId(Proizvodjac proId) {
        this.proId = proId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artId != null ? artId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.artId == null && other.artId != null) || (this.artId != null && !this.artId.equals(other.artId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Artikal[ artId=" + artId + " ]";
    }
    
}
