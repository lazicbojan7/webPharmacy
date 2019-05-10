/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "stanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stanje.findAll", query = "SELECT s FROM Stanje s")
    , @NamedQuery(name = "Stanje.findByStaId", query = "SELECT s FROM Stanje s WHERE s.staId = :staId")
    , @NamedQuery(name = "Stanje.findByStaKolicina", query = "SELECT s FROM Stanje s WHERE s.staKolicina = :staKolicina")})
public class Stanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sta_id")
    private Integer staId;
    @Column(name = "sta_kolicina")
    private Integer staKolicina;
    @JoinColumn(name = "apo_id", referencedColumnName = "apo_id")
    @ManyToOne
    private Apoteka apoId;
    @JoinColumn(name = "art_id", referencedColumnName = "art_id")
    @ManyToOne
    private Artikal artId;

    public Stanje() {
    }

    public Stanje(Integer staId) {
        this.staId = staId;
    }

    public Integer getStaId() {
        return staId;
    }

    public void setStaId(Integer staId) {
        this.staId = staId;
    }

    public Integer getStaKolicina() {
        return staKolicina;
    }

    public void setStaKolicina(Integer staKolicina) {
        this.staKolicina = staKolicina;
    }

    public Apoteka getApoId() {
        return apoId;
    }

    public void setApoId(Apoteka apoId) {
        this.apoId = apoId;
    }

    public Artikal getArtId() {
        return artId;
    }

    public void setArtId(Artikal artId) {
        this.artId = artId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staId != null ? staId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stanje)) {
            return false;
        }
        Stanje other = (Stanje) object;
        if ((this.staId == null && other.staId != null) || (this.staId != null && !this.staId.equals(other.staId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Stanje[ staId=" + staId + " ]";
    }
    
}
