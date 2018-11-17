package com.bolivariano.tarifas.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.bolivariano.tarifas.entities.TarifaEntity;
import com.bolivariano.tarifas.modelos.Tarifa;
import com.bolivariano.tarifas.repository.TarifaRepository;

@Service("tarifaService")
public class TarifaService {

	@Value("${application.topic-name}")
    private String topicName;
	
	@Autowired
	@Qualifier("tarifaRepository")
	private TarifaRepository tarifaRepository;
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	public ResponseEntity<Object> crear(Tarifa tarifa) {
		try {
			TarifaEntity tarifaEntity = modelToEntity(tarifa);
			tarifaEntity.setFechaCreacion(new Date());
			tarifaEntity.setFechaModificacion(new Date());
			
			TarifaEntity tarifaEntity2 = tarifaRepository.save(tarifaEntity);

			return new ResponseEntity<>(entityToModel(tarifaEntity2), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean eliminar (Tarifa tarifa) {
		try {
			TarifaEntity tarifaEntity = tarifaRepository.findByItinerarioIdAndTipoViajero(tarifa.getItinerarioId(), tarifa.getTipoViajero());
			if (tarifaEntity != null) {
				tarifaRepository.delete(tarifaEntity);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public ResponseEntity<Object> actualizar (Tarifa tarifa) {
		try {
			TarifaEntity tarifaEntity = modelToEntity(tarifa);
			
			TarifaEntity tarifaEntity2 = tarifaRepository.findByItinerarioIdAndTipoViajero(tarifaEntity.getItinerarioId(), tarifaEntity.getTipoViajero());
		
			if (tarifaEntity2 != null) {
				tarifaEntity.setFechaCreacion(tarifaEntity2.getFechaCreacion());
				tarifaEntity.setFechaModificacion(new Date());
				tarifaEntity.setId(tarifaEntity2.getId());
				TarifaEntity tarifaEntity3 = tarifaRepository.save(tarifaEntity);
				
				Tarifa tarifa2 = entityToModel(tarifaEntity3);
				
				System.out.println("Nombre topico: " + topicName);
				jmsTemplate.convertAndSend(topicName, tarifa2);
				
				return new ResponseEntity<>(tarifa2, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Object> buscarTodos () {
		try {
			List<TarifaEntity> tarifaEntities = tarifaRepository.findAll();
			
			List<Tarifa> tarifas = new ArrayList<>();
			for (TarifaEntity tarifaEntity : tarifaEntities) {
				tarifas.add(entityToModel(tarifaEntity));
			}
			return new ResponseEntity<>(tarifas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Tarifa entityToModel(TarifaEntity tarifaEntity) {
		Tarifa tarifa = new Tarifa();
		
		tarifa.setEstado(tarifaEntity.getEstado());
		tarifa.setItinerarioId(tarifaEntity.getItinerarioId());
		tarifa.setPrecio(tarifaEntity.getPrecio());
		tarifa.setTipoViajero(tarifaEntity.getTipoViajero());
		
		return tarifa;
	}
	
	private TarifaEntity modelToEntity(Tarifa tarifa) {
		TarifaEntity tarifaEntity = new TarifaEntity();
		
		tarifaEntity.setEstado(tarifa.getEstado());
		tarifaEntity.setItinerarioId(tarifa.getItinerarioId());
		tarifaEntity.setPrecio(tarifa.getPrecio());
		tarifaEntity.setTipoViajero(tarifa.getTipoViajero());
		
		return tarifaEntity;
	}
	
}
