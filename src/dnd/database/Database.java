package dnd.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;
import dnd.character.Hero;

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
                rs = stmt.executeUpdate("INSERT INTO hero(name, life, strength, max_life, max_strength, type) VALUES ('" + name + "', " + life + ", " + strength + ", " + maxLife + ", " + maxStrength + ",'" + character.getClass().getSimpleName() + "');", Statement.RETURN_GENERATED_KEYS);
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        character.setId(generatedKeys.getLong(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                } catch (Exception e) {

                }
            } else {
                rs = stmt.executeUpdate("UPDATE hero SET name = '" +name +"', life ="+ life+", strength = "+strength+", max_life ="+maxLife+", max_strength=" +maxStrength+" WHERE id="+character.getId()+";");
            }
            } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignore) {
            }
        }
    }

    public List<Long> getCharacters(Menu menu) {
        List<Long> listId = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM hero");
            int i = 1;
            while(rs.next()) {
                String name = rs.getString("name");
                String life = rs.getString("life");
                String strength = rs.getString("strength");
                menu.displayCharacterDatabase(name, life, strength, i);
                listId.add(rs.getLong("id"));
                i++;
            }
        } catch (SQLException e) {
            menu.displayDatabaseError();
        }
        return listId;
    }

    public void removeCharacter(Long integer, Menu menu) {
        Statement stmt = null;
        int rs;
        try {
            stmt = this.connection.createStatement();
            rs = stmt.executeUpdate("DELETE FROM hero WHERE id="+integer);
        } catch (SQLException e) {
            menu.displayDatabaseError();
        }
    }

    public Hero getCharacter(Long integer, Menu menu) {
        Hero character = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = this.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM hero WHERE id="+integer);
            try {
                while (rs.next()){
                    String type = rs.getString("type");
                    Class<?> characterType = Class.forName("dnd.character.hero."+type);
                    character = (Hero) characterType.getDeclaredConstructor().newInstance();
                    character.setId(integer);
                    character.setName(rs.getString("name"));
                    character.setLife(rs.getInt("life"));
                    character.setStrength(rs.getInt("strength"));
                    character.setMaxLife(rs.getInt("max_life"));
                    character.setMaxStrength(rs.getInt("max_strength"));
                }
            } catch (Exception e) {
                menu.displayWrongType();
            }
        } catch (SQLException e) {
            menu.displayDatabaseError();
        }
        return character;
    }
}
