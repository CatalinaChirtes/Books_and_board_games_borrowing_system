package application.books;

import java.util.HashMap;
import java.util.Map;

public class Game implements BorrowableGame, ReturnableGame {
    Integer game_id;
    String game;
    String players;
    String status;

    static Map<Integer, Integer> borrowedGames = new HashMap<Integer, Integer>();

    public Game(Integer game_id, String game, String players, String status) {
        this.game_id = game_id;
        this.game = game;
        this.players = players;
        this.status = status;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Map<Integer, Integer> getBorrowedGames() {
        return borrowedGames;
    }

    public static void setBorrowedGames(Map<Integer, Integer> borrowedGames) {
        Game.borrowedGames = borrowedGames;
    }

    @Override
    public boolean isBorrowableGame(Game game) {
        return game.status.equals("available");
    }

    @Override
    public boolean isReturnableGame(Game game) {
        return game.status.equals("borrowed");
    }

    public void borrowGame(Integer user_id) {
        this.status = "borrowed";
        borrowedGames.put(user_id, this.game_id);
    }

    public void returnGame(Integer user_id) {
        this.status = "available";
        borrowedGames.remove(user_id, this.game_id);
    }
}
