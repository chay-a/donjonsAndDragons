package board;

import character.Enemy;
import character.enemy.Dragon;
import character.enemy.Goblin;
import character.enemy.Witch;
import equipment.*;
import equipment.animal.Cat;
import equipment.animal.Falcon;
import equipment.distance.Arbalest;
import equipment.distance.Bow;
import equipment.potion.LargePotion;
import equipment.potion.SmallPotion;
import equipment.spell.Fireball;
import equipment.spell.Lightning;
import equipment.weapon.Club;
import equipment.weapon.Sword;

public class BoardGenerator {

    public static Enemy createEnemy() {
        double random =  (Math.random()) * 100;
        if (random < 50) {
            return new Goblin();
        } else if (random < 75) {
            return new Dragon();
        }
        return new Witch();
    }

    public static Equipment createEquipment() {
        double random =  (Math.random()) * 5;
        if (random <1) {
            return createWeapon();
        } else if (random < 2) {
            return createPotion();
        } else if (random < 3) {
            return createSpell();
        } else if (random < 4) {
            return createDistance();
        }
        return createAnimal();
    }

    private static Distance createDistance() {
        double random = Math.random() * 2;
        if (random < 1) {
            return new Arbalest();
        }
        return new Bow();
    }

    private static Spell createSpell() {
        double random =  (Math.random()) * 2;
        if (random <1) {
            return new Lightning();
        }
        return new Fireball();
    }

    private static Animal createAnimal() {
        double random =  (Math.random()) * 2;
        if (random <1) {
            return new Cat();
        }
        return new Falcon();
    }

    public static Weapon createWeapon() {
        double random =  (Math.random()) * 2;
        if (random <1) {
            return new Club();
        }
        return new Sword();
    }

    public static Potion createPotion() {
        double random =  (Math.random()) * 2;
        if (random < 1) {
            return new SmallPotion();
        }
        return new LargePotion();
    }
}
