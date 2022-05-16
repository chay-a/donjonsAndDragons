package dnd.inventory;

import dnd.menu.Menu;

import java.util.List;

public interface IIventory {
    void fullInventory(Menu menu, List<IIventory> list);
}
