package com.corpcazorla.epidemiological.infrastructure.adapter.out.persistence;

import com.corpcazorla.epidemiological.application.model.DataPage;
import com.corpcazorla.epidemiological.application.model.SearchMedicationsQuery;
import com.corpcazorla.epidemiological.domain.model.EpidemiologicalMedications;
import com.corpcazorla.epidemiological.domain.ports.out.EpidemiologicalMedicationsRepository;
import com.corpcazorla.epidemiological.infrastructure.adapter.out.persistence.mapper.EpidemiologicalMedicationsPersistenceMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Default // Asegura que este sea el principal
@jakarta.annotation.Priority(10) // Un número alto le da más importancia que al Mock
public class EpidemiologicalMedicationsJpaAdapter implements EpidemiologicalMedicationsRepository {

    private final EntityManager entityManager;
    private final EpidemiologicalMedicationsPersistenceMapper mapper;

    public EpidemiologicalMedicationsJpaAdapter(EntityManager entityManager, EpidemiologicalMedicationsPersistenceMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    @Transactional // Asegura la gestión de la transacción
    public DataPage<EpidemiologicalMedications> listEpidemiologicalAttentionRepository(SearchMedicationsQuery request) {
    	try {
    		// 1. Definición de la consulta nativa
            Query query = entityManager.createNativeQuery("EXEC epidemiological.usp_get_epidemiological_attention_report :startDate,:endDate");
            // 2. Seteo de parámetros

            query.setParameter("startDate", request.getStartDate()); 
            query.setParameter("endDate", request.getEndDate()); 
            
            // 3. Obtención y mapeo de resultados
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.getResultList();
         // Estándar DataPage: Si es un registro exitoso, el total es el tamaño de la lista (normalmente 1)
            
            if (rows == null || rows.isEmpty()) {
            	return new DataPage<>(java.util.Collections.emptyList(), 0);
            }
            
            return new DataPage<>(mapper.fromRows(rows), rows.size());
    } catch (PersistenceException e) {
            // Captura errores de SQL Server (RAISERROR / THROW)
            String rootMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
            throw new PersistenceAdapterException("Error en base de datos: " + rootMsg, e, 409);

    } catch (Exception e) {
        // Pasamos 'e' como tercer argumento para mantener la causa raíz
        throw new PersistenceAdapterException("El servicio no está disponible actualmente.", e, 500);
    }
    	
    }
}