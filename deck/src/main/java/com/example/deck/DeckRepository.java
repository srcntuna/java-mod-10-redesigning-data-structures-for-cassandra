package com.example.deck;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeckRepository extends CrudRepository<Deck, Long> {
    Optional<Deck> findFirstByOrderByPositionDesc();
}
