package com.deliverytech.delivery.services;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Transactional(readOnly = true)
    public List<RestauranteDTO> findAll() {
        List<Restaurante> list = repository.findAll();
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RestauranteDTO> findByCategoria(String categoria) {
        List<Restaurante> list = repository.findByTipoCozinhaIgnoreCase(categoria);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RestauranteDTO findById(Long id) {
        Restaurante entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        return convertToDTO(entity);
    }

    @Transactional
    public RestauranteDTO insert(RestauranteDTO dto) {
        Restaurante entity = new Restaurante();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public RestauranteDTO update(Long id, RestauranteDTO dto) {
        Restaurante entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RestauranteDTO convertToDTO(Restaurante entity) {
        return new RestauranteDTO(
            entity.getId(), 
            entity.getNome(), 
            entity.getTipoCozinha(), 
            entity.getEndereco(), 
            entity.getTelefone(),
            entity.getTaxaEntrega(),
            entity.getTempoEntregaMin()
        );
    }

    private void copyDtoToEntity(RestauranteDTO dto, Restaurante entity) {
        entity.setNome(dto.getNome());
        entity.setTipoCozinha(dto.getTipoCozinha());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity.setTaxaEntrega(dto.getTaxaEntrega());
        entity.setTempoEntregaMin(dto.getTempoEntregaMin());
    }
}
