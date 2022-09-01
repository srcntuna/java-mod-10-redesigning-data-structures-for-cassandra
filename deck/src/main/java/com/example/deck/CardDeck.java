package com.example.deck;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table
public class CardDeck implements Serializable {

    @PrimaryKey
    private Long id;

    private Long deckNumber;

    private Long position;

    private String suit;

    private String name;

    private Long points;

    private UUID uuid;

    public CardDeck(Long deckNumber, Long position, String suit, String name, Long points, UUID uuid, Long id) {
        this.deckNumber = deckNumber;
        this.position = position;
        this.suit = suit;
        this.name = name;
        this.points = points;
        this.uuid = uuid;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(Long deckNumber) {
        this.deckNumber = deckNumber;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

