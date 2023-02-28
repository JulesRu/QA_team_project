package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class GameStoreTest {

    List<Game> games = new ArrayList<>();

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddGameSome() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Не уволься", "Аркады");

        assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldNotContainGame() {

        GameStore store = new GameStore();

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Не уволься", "Аркады");
        games.remove(game2);

        assertFalse(store.containsGame(game2));

    }

    @Test
    public void getMostPlayerNone() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Не уволься", "Аркады");


        assertTrue(store.getMostPlayer() == null);

    }

    @Test
    public void getMostPlayer1Hour() {
        GameStore store = new GameStore();

        store.addPlayTime("Ivan", 1);


        assertTrue(store.getMostPlayer() == null);

    }

    @Test
    public void getMostPlayer2Hours() {
        GameStore store = new GameStore();

        store.addPlayTime("Ivan", 2);
        store.addPlayTime("Jules", 1);


        assertTrue(store.getMostPlayer() == "Ivan");
    }

    @Test
    public void getMostPlayerPlayedMoreThanOnce() {
        GameStore store = new GameStore();

        store.addPlayTime("Ivan", 3);
        store.addPlayTime("Jules", 1);
        store.addPlayTime("Jules", 4);


        assertTrue(store.getMostPlayer() == "Jules");

    }
}

