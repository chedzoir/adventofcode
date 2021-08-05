package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day22 implements DayRunner<Long, Long> {

    static class Player {
        String name;
        List<Long> deck;
    }

    private List<Player> parsePlayers(List<String> input) {
        List<Player> res = new ArrayList<>();
        Player p = null;
        for (String s : input) {
            if (s.endsWith(":")) {
                p = new Player();
                p.name = s.replace(":", "");
                p.deck = new ArrayList<>();
            } else if (!s.isEmpty()) {
                p.deck.add(Long.parseLong(s));
            } else {
                res.add(p);
            }
        }
        res.add(p);
        return res;
    }

    @Override
    public Long runPart1(List<String> input) {
        var players = parsePlayers(input);

        var player1 = players.get(0);
        var player2 = players.get(1);

        // now play
        do {
            var card1 = player1.deck.remove(0);
            var card2 = player2.deck.remove(0);
            if (card1 > card2) {
                player1.deck.add(card1);
                player1.deck.add(card2);
            } else {
                player2.deck.add(card2);
                player2.deck.add(card1);
            }
        } while (player1.deck.size() > 0 && player2.deck.size() > 0);

        var winningDeck = player1.deck.size() > 0 ? player1.deck : player2.deck;

        return calculateScore(winningDeck);
    }

    private Long calculateScore(List<Long> winningDeck) {
        long res = 0L;
        for (int i = 0; i < winningDeck.size(); i++) {
            res += (winningDeck.size() - i) * winningDeck.get(i);
        }
        return res;
    }

    @Override
    public Long runPart2(List<String> input) {

        var players = parsePlayers(input);

        var player1 = players.get(0);
        var player2 = players.get(1);

        Set<String> playedHands = new HashSet<>();

        Player winningPlayer = null;

        subGame(player1, player2, 0);
        winningPlayer = player1.deck.size() > 0 ? player1 : player2;

        return calculateScore(winningPlayer.deck);

    }

    /**
     * Returns true if player 1 wins the game, else false if player 2 does.
     *
     * @param player1
     * @param player2
     * @return
     */
    private boolean subGame(Player player1, Player player2, int  game) {
        Set<String> playedHands = new HashSet<>();

        do {
            var currentHand = player1.deck.stream().map(i -> Long.toString(i)).collect(Collectors.joining(","))
                    + ":"
                    + player2.deck.stream().map(i -> Long.toString(i)).collect(Collectors.joining(","));

            if (playedHands.contains(currentHand)) {
                return true;
            }

            playedHands.add(currentHand);

            var card1 = player1.deck.remove(0);
            var card2 = player2.deck.remove(0);

            if (card1 <= player1.deck.size() && card2 <= player2.deck.size()) {
                // work out who wins....
                var newPlayer1 = new Player();
                var newPlayer2 = new Player();
                newPlayer1.deck = new ArrayList<>(player1.deck.subList(0, card1.intValue()));
                newPlayer2.deck = new ArrayList<>(player2.deck.subList(0, card2.intValue()));
                var doesPlayer1Win = subGame(newPlayer1, newPlayer2, game +1);
                if (doesPlayer1Win) {
                    player1.deck.add(card1);
                    player1.deck.add(card2);
                } else {
                    player2.deck.add(card2);
                    player2.deck.add(card1);
                }
            } else {
                if (card1 > card2) {
                    player1.deck.add(card1);
                    player1.deck.add(card2);
                } else {
                    player2.deck.add(card2);
                    player2.deck.add(card1);
                }
            }
        } while (player1.deck.size() > 0 && player2.deck.size() > 0);

        return player1.deck.size() > 0;
    }
}
