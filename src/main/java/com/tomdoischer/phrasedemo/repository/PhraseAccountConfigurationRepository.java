package com.tomdoischer.phrasedemo.repository;

import com.tomdoischer.phrasedemo.entity.PhraseAccountConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseAccountConfigurationRepository extends JpaRepository<PhraseAccountConfiguration, Long> {
    /**
     * Find an account configuration.
     *
     * @param username
     * @return
     */
    PhraseAccountConfiguration findByUsername(String username);

    /**
     * Update an account configuration.
     *
     * @param username
     * @param password
     * @param apiKey
     * @param userId
     */
    @Modifying
    @Query("update PhraseAccountConfiguration c set c.username = ?1, c.password = ?2, c.apiKey = ?3 where c.id = ?4")
    void update(String username, String password, String apiKey, Long userId);
}
