package database;

import java.sql.*;
import java.util.List;
import List.CharacterInGame;
import Menu.Menu;
import character.Character;
import character.Hero;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class Database {
    private Connection connection;


    public Database() {
       this.loadDatabase();
    }

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonjonsAndDragons", "chay-a", "KUntR%ZNt#3^r#9i58iq*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCharacters(List<CharacterInGame> characters, Menu menu) {
        for (CharacterInGame characterInGame : characters) {
            saveCharacter(characterInGame.getCharacter(), menu);
        }
    }

    private void saveCharacter(Hero character, Menu menu) {
        Statement stmt = null;
        int rs;
        try {
            stmt = this.connection.createStatement();
            String name = character.getName();
            int life = character.getLife();
            int strength = character.getStrength();
            int maxLife = character.getMaxLife();
            int maxStrength = character.getMaxStrength();
            if (character.getId() == null) {
                rs = stmt.executeUpdate("INSERT INTO hero(name, life, strength, max_life, max_strength, type) VALUES ('" + name + "', " + life + ", " + strength + ", " + maxLife + ", " + maxStrength + ",'" + character.getClass().getSimpleName() + "');");
            } else {
                rs = stmt.executeUpdate("UPDATE hero SET name = '" +name +"', life ="+ life+", strength = "+strength+", maxLife ="+maxLife+", maxStrength=" +maxStrength+" WHERE id="+character.getId()+";");
            }
            } catch (SQLException e) {
            menu.displayDatabaseError();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignore) {
            }
        }
    }
}
