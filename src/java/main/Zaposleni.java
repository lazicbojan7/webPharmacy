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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z")
    , @NamedQuery(name = "Zaposleni.findByZapId", query = "SELECT z FROM Zaposleni z WHERE z.zapId = :zapId")
    , @NamedQuery(name = "Zaposleni.findByZapIme", query = "SELECT z FROM Zaposleni z WHERE z.zapIme = :zapIme")
    , @NamedQuery(name = "Zaposleni.findByZapPrezime", query = "SELECT z FROM Zaposleni z WHERE z.zapPrezime = :zapPrezime")
    , @NamedQuery(name = "Zaposleni.findByZapEmail", query = "SELECT z FROM Zaposleni z WHERE z.zapEmail = :zapEmail")
    , @NamedQuery(name = "Zaposleni.findByZapLozinka", query = "SELECT z FROM Zaposleni z WHERE z.zapLozinka = :zapLozinka")
    , @NamedQuery(name = "Zaposleni.findByZapTip", query = "SELECT z FROM Zaposleni z WHERE z.zapTip = :zapTip")})
public class Zaposleni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zap_id")
    private Integer zapId;
    @Size(max = 45)
    @Column(name = "zap_ime")
    private String zapIme;
    @Size(max = 45)
    @Column(name = "zap_prezime")
    private String zapPrezime;
    @Size(max = 45)
    @Column(name = "zap_email")
    private String zapEmail;
    @Size(max = 45)
    @Column(name = "zap_lozinka")
    private String zapLozinka;
    @Size(max = 45)
    @Column(name = "zap_tip")
    private String zapTip;
    @JoinColumn(name = "apo_id", referencedColumnName = "apo_id")
    @ManyToOne
    private Apoteka apoId;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;

    public Zaposleni() {
    }

    public Zaposleni(String zapIme, String zapPrezime, String zapEmail, String zapLozinka, String zapTip, Apoteka apoId, Grad graId) {
        this.zapIme = zapIme;
        this.zapPrezime = zapPrezime;
        this.zapEmail = zapEmail;
        this.zapLozinka = zapLozinka;
        this.zapTip = zapTip;
        this.apoId = apoId;
        this.graId = graId;
    }
    

    public Zaposleni(Integer zapId) {
        this.zapId = zapId;
    }

    public Integer getZapId() {
        return zapId;
    }

    public void setZapId(Integer zapId) {
        this.zapId = zapId;
    }

    public String getZapIme() {
        return zapIme;
    }

    public void setZapIme(String zapIme) {
        this.zapIme = zapIme;
    }

    public String getZapPrezime() {
        return zapPrezime;
    }

    public void setZapPrezime(String zapPrezime) {
        this.zapPrezime = zapPrezime;
    }

    public String getZapEmail() {
        return zapEmail;
    }

    public void setZapEmail(String zapEmail) {
        this.zapEmail = zapEmail;
    }

    public String getZapLozinka() {
        return zapLozinka;
    }

    public void setZapLozinka(String zapLozinka) {
        this.zapLozinka = zapLozinka;
    }

    public String getZapTip() {
        return zapTip;
    }

    public void setZapTip(String zapTip) {
        this.zapTip = zapTip;
    }

    public Apoteka getApoId() {
        return apoId;
    }

    public void setApoId(Apoteka apoId) {
        this.apoId = apoId;
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
        hash += (zapId != null ? zapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.zapId == null && other.zapId != null) || (this.zapId != null && !this.zapId.equals(other.zapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Zaposleni[ zapId=" + zapId + " ]";
    }
    
}
