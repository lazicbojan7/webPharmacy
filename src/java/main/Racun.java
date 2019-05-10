/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r")
    , @NamedQuery(name = "Racun.findByRacId", query = "SELECT r FROM Racun r WHERE r.racId = :racId")
    , @NamedQuery(name = "Racun.findByRacDatum", query = "SELECT r FROM Racun r WHERE r.racDatum = :racDatum")
    , @NamedQuery(name = "Racun.findByRacIznos", query = "SELECT r FROM Racun r WHERE r.racIznos = :racIznos")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rac_id")
    private Integer racId;
    @Column(name = "rac_datum")
    @Temporal(TemporalType.DATE)
    private Date racDatum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rac_iznos")
    private Double racIznos;
    @OneToMany(mappedBy = "racId")
    private List<Stavka> stavkaList;
    @JoinColumn(name = "kup_id", referencedColumnName = "kup_id")
    @ManyToOne
    private Kupac kupId;

    public Racun() {
    }

    public Racun(Date racDatum, Double racIznos, Kupac kupId) {
        this.racDatum = racDatum;
        this.racIznos = racIznos;
        this.kupId = kupId;
    }
    

    public Racun(Integer racId) {
        this.racId = racId;
    }

    public Integer getRacId() {
        return racId;
    }

    public void setRacId(Integer racId) {
        this.racId = racId;
    }

    public Date getRacDatum() {
        return racDatum;
    }

    public void setRacDatum(Date racDatum) {
        this.racDatum = racDatum;
    }

    public Double getRacIznos() {
        return racIznos;
    }

    public void setRacIznos(Double racIznos) {
        this.racIznos = racIznos;
    }

    @XmlTransient
    public List<Stavka> getStavkaList() {
        return stavkaList;
    }

    public void setStavkaList(List<Stavka> stavkaList) {
        this.stavkaList = stavkaList;
    }

    public Kupac getKupId() {
        return kupId;
    }

    public void setKupId(Kupac kupId) {
        this.kupId = kupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (racId != null ? racId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.racId == null && other.racId != null) || (this.racId != null && !this.racId.equals(other.racId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Racun[ racId=" + racId + " ]";
    }
    
}
