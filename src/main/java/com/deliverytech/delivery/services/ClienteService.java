package com.deliverytech.delivery.services;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> list = repository.findAll();
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return convertToDTO(entity);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return convertToDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ClienteDTO convertToDTO(Cliente entity) {
        return new ClienteDTO(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getEndereco());
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setEndereco(dto.getEndereco());
    }
}
