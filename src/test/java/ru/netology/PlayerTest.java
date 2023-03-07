package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void shouldNotChangeIfGameAlreadyInstalled() {

        GameStore store = new GameStore();
        Game game = store.publishGame("CS", "Шутеры");
        Player player = new Player("Will");
        player.installGame(game);
        player.play(game, 3);


        assertThrows(RuntimeException.class, () ->
                player.installGame(game));
    }

    @Test
    public void shouldSumPlayTimeIfOneGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Kolya");
        player.installGame(game);
        player.play(game, 2);

        int expected = 7;
        int actual = player.play(game, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionGameNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("CS", "Шутеры");
        Player player = new Player("Will");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game, 6);
        });
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Морской бой", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 2);

        int expected = 5;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void getMostPLayerByGenre1() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("CS", "Шутеры");

        Player player = new Player("Will");
        player.installGame(game1);
        player.play(game1, 3);

        assertEquals(game1, player.mostPlayerByGenre("Шутеры"));
    }

    @Test
    public void getMostPlayerByGenreFewGames() {
        GameStore store = new GameStore();

        Game game1 = store.publishGame("CS", "Шутеры");
        Game game2 = store.publishGame("Half-Life", "Шутеры");
        Game game3 = store.publishGame("GTA", "Шутеры");

        Player player = new Player("Will");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game3, 2);
        player.play(game1, 3);
        player.play(game2, 1);
        player.play(game2, 4);


        assertEquals(game2, player.mostPlayerByGenre("Шутеры"));
    }

    @Test
    public void getMostPlayerByGenreIfNone() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("CS", "Шутеры");
        Game game2 = store.publishGame("Half-Life", "Шутеры");
        Player player = new Player("Faina");
        player.installGame(game1);
        player.play(game1, 9);

        assertEquals(null, player.mostPlayerByGenre("Аркады"));
    }


}
