# Redesigning Data Structures for Cassandra

## Learning Goals

- Hands on experience modifying queries and data structures for Cassandra

## Instructions

We will be working hands on with a simple Blackjack deck implemented in a RDBMS schema on top of Postgres, and converting it to be
backed by Cassandra.

The data structures are as follows:

Deck

| id | position | uuid |
|----|----------|------|
| 1  | 1        | aaa  |
| 2  | 2        | bbb  |
| 3  | 3        | ccc  |
| ...| ...      | ...  |
| n  | n        | nnn  |

Card

| id  | deck_number | name  | suit     | uuid |
|-----|-------------|-------|----------|------|
| 1   | 1           | one   | clubs    | aaa  |
| 2   | 1           | two   | clubs    | bbb  |
| 3   | 1           | three | clubs    | ccc  |
| ... | ...         | ...   | ...      | ...  |
| n   | n/52        | ace   | diamonds | nnn  |

Value

| id | name  | points |
|----|-------|--------|
| 1  | one   | 1      |
| 2  | two   | 2      |
| 3  | three | 3      |
|... | ...   | ...    |
| n  | ace   | 11     |


There are simple REST endpoints implemented for deck operations to `shuffle`, `deal`, and start with a `new` deck using a parameter `decks` for the amount of decks to be used.

Once complete, you can submit a working fork.
