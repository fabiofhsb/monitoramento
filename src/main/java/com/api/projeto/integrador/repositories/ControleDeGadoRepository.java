package com.api.projeto.integrador.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.projeto.integrador.models.ControleDeGado;

public interface ControleDeGadoRepository extends
        JpaRepository<ControleDeGado, Integer> {

    List<ControleDeGado> findByNumeroProdutor(String numeroProdutor);

    // List<ControleDeGado> findByDataNascimentoBefore(Date dataNascimento);

    List<ControleDeGado> findByNumeroIdentificacao(String numeroIdentificacao);

    // void deleteById(UUID id);

    // boolean existsById(UUID id);

    // int countByClassificacao(String classificacao);
}
