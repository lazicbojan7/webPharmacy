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
@Table(name = "stavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavka.findAll", query = "SELECT s FROM Stavka s")
    , @NamedQuery(name = "Stavka.findByStaId", query = "SELECT s FROM Stavka s WHERE s.staId = :staId")
    , @NamedQuery(name = "Stavka.findByStaKolicina", query = "SELECT s FROM Stavka s WHERE s.staKolicina = :staKolicina")
    , @NamedQuery(name = "Stavka.findByStaCena", query = "SELECT s FROM Stavka s WHERE s.staCena = :staCena")})
public class Stavka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sta_id")
    private Integer staId;
    @Column(name = "sta_kolicina")
    private Integer staKolicina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sta_cena")
    private Double staCena;
    @JoinColumn(name = "art_id", referencedColumnName = "art_id")
    @ManyToOne
    private Artikal artId;
    @JoinColumn(name = "rac_id", referencedColumnName = "rac_id")
    @ManyToOne
    private Racun racId;

    public Stavka() {
    }

    public Stavka(Integer staKolicina, Double staCena, Artikal artId, Racun racId) {
        this.staKolicina = staKolicina;
        this.staCena = staCena;
        this.artId = artId;
        this.racId = racId;
    }
    

    public Stavka(Integer staId) {
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

    public Double getStaCena() {
        return staCena;
    }

    public void setStaCena(Double staCena) {
        this.staCena = staCena;
    }

    public Artikal getArtId() {
        return artId;
    }

    public void setArtId(Artikal artId) {
        this.artId = artId;
    }

    public Racun getRacId() {
        return racId;
    }

    public void setRacId(Racun racId) {
        this.racId = racId;
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
        if (!(object instanceof Stavka)) {
            return false;
        }
        Stavka other = (Stavka) object;
        if ((this.staId == null && other.staId != null) || (this.staId != null && !this.staId.equals(other.staId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Stavka[ staId=" + staId + " ]";
    }
    
}
