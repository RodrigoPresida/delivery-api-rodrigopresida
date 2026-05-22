package com.deliverytech.delivery.repositories;

import com.deliverytech.delivery.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNomeContainingIgnoreCase(String nome);
    List<Restaurante> findByTipoCozinhaIgnoreCase(String categoria);
    List<Restaurante> findByAtivoTrue();
}
