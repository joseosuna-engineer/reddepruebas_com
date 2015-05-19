package org.september.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConexionAnual implements Serializable {

    private static final long serialVersionUID = 336556685465L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long year;
    Long yearContador;
    Long yearConnexion;

    public ConexionAnual() {
    }

    public ConexionAnual(Long year, Long yearContador, Long yearConnexion) {
        this.year = year;
        this.yearContador = yearContador;
        this.yearConnexion = yearConnexion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getYearContador() {
        return yearContador;
    }

    public void setYearContador(Long yearContador) {
        this.yearContador = yearContador;
    }

    public Long getYearConnexion() {
        return yearConnexion;
    }

    public void setYearConnexion(Long yearConnexion) {
        this.yearConnexion = yearConnexion;
    }      

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (year != null ? year.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ConexionAnual)) {
            return false;
        }
        ConexionAnual other = (ConexionAnual) object;
        if ((this.year == null && other.year != null) || (this.year != null && !this.year.equals(other.year))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.september.entity.ConexionAnual[ year=" + year + " ]";
    }

}
