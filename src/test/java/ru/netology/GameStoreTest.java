package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class GameStoreTest {

    List<Game> games = new ArrayList<>();

    @Test
    public void shouldNotAddGamePublishedAlready() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Assertions.assertThrows(RuntimeException.class, () -> {
            store.publishGame("Нетология Баттл Онлайн", "Аркады");
        });
    }

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddNonGame() {

        GameStore store = new GameStore();
        Game game = null;

        assertFalse(store.containsGame(game));
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
        games.remove(game1);
        Game game2 = store.publishGame("Не уволься", "Аркады");

        assertTrue(store.containsGame(game1));
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

        assertTrue(store.getMostPlayer() == "Ivan");
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

    @Test
    public void getSumPlayedTimeFivePlayer() {
        GameStore store = new GameStore();

        store.addPlayTime("Ivan", 2);
        store.addPlayTime("Jules", 1);
        store.addPlayTime("Kolya", 0);
        store.addPlayTime("Jules", 1);
        store.addPlayTime("Petya", 5);


        int expected = 9;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSumPlayedTimeOnePlayer() {
        GameStore store = new GameStore();

        store.addPlayTime("Jules", 1);

        int expected = 1;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSumPlayedTimeOnePlayerNonPlayedTime() {
        GameStore store = new GameStore();

        store.addPlayTime("Jules", 0);

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }
}

