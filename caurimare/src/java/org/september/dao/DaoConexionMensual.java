package org.september.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.datanucleus.api.jpa.NucleusJPAHelper;
import org.september.entity.ConexionMensual;
import org.september.service.EMFService;

public enum DaoConexionMensual {

    INSTANCE;

    public List<ConexionMensual> getConexionsMensual() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from ConexionMensual m");
        List<ConexionMensual> conexionMensuals = q.getResultList();
        return conexionMensuals;
    }

    public void add(Long id, Long mesContador, Long mesConnexion) {
        EntityManager em = EMFService.get().createEntityManager();
        ConexionMensual conexionMensual = new ConexionMensual(id, mesContador,
                mesConnexion);
        em.persist(conexionMensual);
        em.close();
    }

    public void update(ConexionMensual conexionMensual) {
        EntityManager em = NucleusJPAHelper.getEntityManager(conexionMensual);
        em.merge(conexionMensual);
        em.close();
    }

}
