package org.september.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.datanucleus.api.jpa.NucleusJPAHelper;
import org.september.entity.ConexionAnual;
import org.september.service.EMFService;

public enum DaoConexionAnual {

    INSTANCE;

    public List<ConexionAnual> getConexionsAnual() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from ConexionAnual m");
        List<ConexionAnual> conexionAnuals = q.getResultList();
        return conexionAnuals;
    }

    public void add(Long year, Long yearContador, Long yearConnexion) {
        EntityManager em = EMFService.get().createEntityManager();
        ConexionAnual conexionAnual = new ConexionAnual(year, yearContador,
                yearConnexion);
        em.persist(conexionAnual);
        em.close();
    }

    public void update(ConexionAnual conexionAnual) {
        EntityManager em = NucleusJPAHelper.getEntityManager(conexionAnual);
        em.merge(conexionAnual);
        em.close();
    }

}
