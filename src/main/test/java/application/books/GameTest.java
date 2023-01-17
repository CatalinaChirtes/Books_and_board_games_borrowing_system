package application.books;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testBorrowGame() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        game.borrowGame(1);
        assertEquals("borrowed", game.getStatus());
        assertTrue(game.borrowedGamesMap.containsKey(1));
    }

    @Test
    public void testReturnGame() {
        Game game = new Game(1, "Monopoly", "2-6", "borrowed");
        game.returnGame(1);
        assertEquals("available", game.getStatus());
        assertFalse(game.borrowedGamesMap.containsKey(1));
    }

    @Test
    public void testIsBorrowableGame() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        assertTrue(game.isBorrowableGame(game));
        game.setStatus("borrowed");
        assertFalse(game.isBorrowableGame(game));
    }

    @Test
    public void testIsReturnableGame() {
        Game game = new Game(1, "Monopoly", "2-6", "borrowed");
        assertTrue(game.isReturnableGame(game));
        game.setStatus("available");
        assertFalse(game.isReturnableGame(game));
    }

    @Test
    public void testIsRemovableGame() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        assertTrue(game.isRemovableGame(game));
        game.setStatus("borrowed");
        assertFalse(game.isRemovableGame(game));
    }

    @Test
    public void testGetName() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        assertEquals("Monopoly", game.getName());
    }

    @Test
    public void testSetName() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        game.setName("Poker");
        assertEquals("Poker", game.getName());
    }

    @Test
    public void testGetGameId() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        assertEquals(1, game.getGame_id().intValue());
    }

    @Test
    public void testSetGameId() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        game.setGame_id(2);
        assertEquals(2, game.getGame_id().intValue());
    }

    @Test
    public void testGetPlayers() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        assertEquals("2-6", game.getPlayers());
    }

    @Test
    public void testSetPlayers() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        game.setPlayers("3-4");
        assertEquals("3-4", game.getPlayers());
    }

    @Test
    public void testBorrowedGamesMap() {
        Game game = new Game(1, "Monopoly", "2-6", "available");
        game.borrowGame(1);
        assertTrue(game.borrowedGamesMap.containsKey(1));
        assertEquals(1, game.borrowedGamesMap.get(1).intValue());
        game.returnGame(1);
        assertFalse(game.borrowedGamesMap.containsKey(1));
    }
}