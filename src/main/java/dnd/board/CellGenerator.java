package dnd.board;

import dnd.character.Enemy;
import dnd.character.enemy.*;
import dnd.equipment.*;
import dnd.equipment.animal.Cat;
import dnd.equipment.animal.Falcon;
import dnd.equipment.distance.Arbalest;
import dnd.equipment.distance.Bow;
import dnd.equipment.potion.LargePotion;
import dnd.equipment.potion.SmallPotion;
import dnd.equipment.spell.Fireball;
import dnd.equipment.spell.Lightning;
import dnd.equipment.weapon.Club;
import dnd.equipment.weapon.Sword;

public class CellGenerator {

    public static Enemy createEnemy() {
        double random =  (Math.random()) * 7;
        if (random < 1) {
            return new Goblin();
        } else if (random < 2) {
            return new Dragon();
        } else if(random < 3) {
            return new BadSpirit();
        } else if (random < 4) {
            return new CursedTree();
        } else if (random < 5) {
            return new Hunter();
        } else if (random < 6) {
            return new Orc();
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
