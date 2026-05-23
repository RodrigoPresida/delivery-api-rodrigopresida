package com.deliverytech.delivery.services;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.models.Cliente;
import com.deliverytech.delivery.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private ClienteDTO clienteDTO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO(1L, "Rodrigo", "rodrigo@email.com", "11999999999", "Rua A");
        cliente = new Cliente(1L, "Rodrigo", "rodrigo@email.com", "11999999999", "Rua A", true);
    }

    @Test
    void should_SaveCliente_When_ValidData() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(cliente);

        ClienteDTO result = service.insert(clienteDTO);

        assertNotNull(result);
        assertEquals(clienteDTO.getEmail(), result.getEmail());
        verify(repository, times(1)).save(any());
    }

    @Test
    void should_ThrowException_When_EmailExists() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(cliente));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.insert(clienteDTO);
        });

        assertEquals("Email já cadastrado", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void should_FindClienteById_When_Exists() {
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void should_ThrowException_When_IdDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.findById(1L);
        });
    }
}
