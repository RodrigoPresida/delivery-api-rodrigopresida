package com.deliverytech.delivery.services;

import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.models.Pedido;
import com.deliverytech.delivery.models.Restaurante;
import com.deliverytech.delivery.repositories.ClienteRepository;
import com.deliverytech.delivery.repositories.PedidoRepository;
import com.deliverytech.delivery.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        List<Pedido> list = repository.findAll();
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> findByCliente(Long clienteId) {
        List<Pedido> list = repository.findByClienteId(clienteId);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return convertToDTO(entity);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
        copyDtoToEntity(dto, entity);
        entity.setDataHora(LocalDateTime.now());
        entity.setStatus("PENDENTE");
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public PedidoDTO updateStatus(Long id, String status) {
        Pedido entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        entity.setStatus(status);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    private PedidoDTO convertToDTO(Pedido entity) {
        return new PedidoDTO(
                entity.getId(),
                entity.getDataHora(),
                entity.getEnderecoEntrega(),
                entity.getValorTotal(),
                entity.getStatus(),
                entity.getCliente().getId(),
                entity.getRestaurante().getId()
        );
    }

    private void copyDtoToEntity(PedidoDTO dto, Pedido entity) {
        entity.setEnderecoEntrega(dto.getEnderecoEntrega());
        entity.setValorTotal(dto.getValorTotal());
        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
        
        entity.setCliente(cliente);
        entity.setRestaurante(restaurante);
    }
}
