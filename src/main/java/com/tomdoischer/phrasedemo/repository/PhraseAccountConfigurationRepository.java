package com.tomdoischer.phrasedemo.repository;

import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseAccountConfigurationRepository extends JpaRepository<PhraseAccountConfiguration,Long> {
    PhraseAccountConfiguration findByUsername(String username);
}
