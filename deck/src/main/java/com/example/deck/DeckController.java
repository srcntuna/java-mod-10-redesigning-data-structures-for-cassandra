package com.example.deck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class DeckController {


    @Autowired
    private CardDeckRepository cardDeckRepository;



    @GetMapping("/new")
    public List<CardDeck> getCardDeck(@RequestParam(value = "decks", defaultValue = "1") Long decks){
        // Drop tables and start new
        cardDeckRepository.deleteAll();


        // Initialize values table
        List<String> names = new ArrayList<String>(Arrays.asList(
                "Ace",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine",
                "Ten",
                "Jack",
                "Queen",
                "King"
                ));

        List<Long> points = new ArrayList<>(Arrays.asList(
                11L,
                2L,
                3L,
                4L,
                5L,
                6L,
                7L,
                8L,
                9L,
                10L,
                10L,
                10L,
                10L
        ));


        // Initialize card table
        List<String> suits = Arrays.asList("Clubs", "Hearts", "Spades", "Diamonds");
        Long position = 1L;
        Long id = 1L;
        for (Long deck = 1L; deck <= decks; deck++) {
            for (String suit : suits) {
                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    Long point = points.get(i);
                    UUID uuid = randomUUID();


                    cardDeckRepository.save(new CardDeck(
                            deck,
                            position,
                            suit,
                            name,
                            point,
                            uuid,
                            id
                    ));
                    ++id;
                    ++position;
                }
            }
        }

        Iterable<CardDeck> iterableCardDecks = cardDeckRepository.findAll();
        List<CardDeck> cardDecks = new ArrayList<CardDeck>();
        for(CardDeck iterableCardDeck : iterableCardDecks){
            cardDecks.add(iterableCardDeck);
        }

        return cardDecks;
    }

    @GetMapping("/shuffle")
    public String shuffleDeck() {

        // Read order of cards
        Iterable<CardDeck> cardDeck = cardDeckRepository.findAll();
        List<Long> order = new ArrayList<Long>();
        for (CardDeck cardDeckItem : cardDeck) {
            order.add(cardDeckItem.getPosition());
        }

        // Shuffle order
        Collections.shuffle(order);

        // Write new order of cards
        ListIterator<Long> orderItr = order.listIterator();
        for (CardDeck cardDeckItem : cardDeck) {
            cardDeckItem.setPosition(orderItr.next());
            cardDeckRepository.save(cardDeckItem);
        }
        return "Deck shuffled.";
    }


    @GetMapping("/deal")
    public String dealCard() {
        // get all cardDecks and search for the largest position
        Iterable<CardDeck> cardDecks = cardDeckRepository.findAll();
        Long position = 0L;
        long id = 0L;
        for(CardDeck cardDeck: cardDecks){
            if(position < cardDeck.getPosition()){
                position = cardDeck.getPosition();
                id = cardDeck.getId();
            }
        }

       CardDeck cardDeckToBeDeleted = cardDeckRepository.findById(id).get();

        cardDeckRepository.delete(cardDeckToBeDeleted);

        return String.format("Dealt %s of %s: Worth %s points.", cardDeckToBeDeleted.getName(), cardDeckToBeDeleted.getSuit(),
                cardDeckToBeDeleted.getPoints());
    }

}
