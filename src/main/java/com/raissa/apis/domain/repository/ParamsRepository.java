package com.raissa.apis.domain.repository;

import com.raissa.apis.domain.entity.Params;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParamsRepository extends JpaRepository<Params, Integer> {
    List<Params> findByProviderAndGrupo(String provider, String grupo);

    Optional<Params> findByProviderAndGrupoAndCodigo(String provider, String grupo, String codigo);
}