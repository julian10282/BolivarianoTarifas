package com.bolivariano.tarifas.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolivariano.tarifas.entities.TarifaEntity;

@Repository("tarifaRepository")
public interface TarifaRepository extends JpaRepository<TarifaEntity, Serializable> {
	public TarifaEntity findByItinerarioIdAndTipoViajero(long itinerarioId, long tipoViajero);
}
