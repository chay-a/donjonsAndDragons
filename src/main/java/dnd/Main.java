package dnd;

public class Main {
    public static void main(String[] args) {
        //   System.out.println(System.getProperty("user.language")+"-"+System.getProperty("user.country"));
        Game game;
        if (args.length > 0 && args[0].equals("nodatabase")) {
            game = new Game(false);
        } else {
            game = new Game(true);
        }
        game.start();
    }
}
