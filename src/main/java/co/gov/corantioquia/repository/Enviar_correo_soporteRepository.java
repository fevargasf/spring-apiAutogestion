package co.gov.corantioquia.repository;

import co.gov.corantioquia.models.dto.SoporteDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
public class Enviar_correo_soporteRepository {

    private final EntityManager entityManager;

    private static final String SOPORTE = "sirena.pks_autogestion_sgmto.enviar_correo_soporte";

    public Enviar_correo_soporteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public String soporte(SoporteDTO soporteDTO){

        String viError="Error Interno";
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(SOPORTE);
        storedProcedureQuery.registerStoredProcedureParameter("niSecExpe", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("viError", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("viError", soporteDTO.getViError());
        storedProcedureQuery.setParameter("niSecExpe", soporteDTO.getNiSecExpe());

        storedProcedureQuery.execute();



        entityManager.close();
        return viError ;
    }
}
