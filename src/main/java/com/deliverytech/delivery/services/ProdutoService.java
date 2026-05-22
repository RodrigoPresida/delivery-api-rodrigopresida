package com.deliverytech.delivery.services;

import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.models.Produto;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repositories.ProdutoRepository;
import com.deliverytech.delivery.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByRestaurante(Long restauranteId) {
        List<Produto> list = repository.findByRestauranteId(restauranteId);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Produto entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return convertToDTO(entity);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Produto entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto entity) {
        return new ProdutoDTO(
            entity.getId(), 
            entity.getNome(), 
            entity.getDescricao(), 
            entity.getPreco(), 
            entity.getDisponivel(), 
            entity.getRestaurante().getId()
        );
    }

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setDisponivel(dto.getDisponivel());
        
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        entity.setRestaurante(restaurante);
    }
}
