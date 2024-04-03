package me.afatcookie.cookcaptcha.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * This is an inventory builder which can be used for ease of access to preset, settings and more!
 * @author AFatCookie
 */
@SuppressWarnings({ "unused", "UnusedReturnValue" })
public class InventoryBuilder {


    private static InventoryBuilder instance;


    public  InventoryBuilder getInstance() {
        if (instance == null) {
            instance = new InventoryBuilder();
        }
        return instance;
    }
    private Inventory inv;

    /**
     * default constructor; can be used when creating inventories, but usually will only work with config inventories
     */
    private InventoryBuilder() {}

    /**
     * creates a default inventory with only a name
     *
     * @param name inventories name
     */
    public InventoryBuilder(String name) {
        this.inv = Bukkit.createInventory(null, 9, colorizeMessage(name));
    }

    /**
     * creates a default inventory with only a name, and a size
     *
     * @param name inventories name
     * @param size inventories size
     */
    public InventoryBuilder(String name, int size) {
        this.inv = Bukkit.createInventory(null, size, colorizeMessage(name));
    }

    /**
     * creates a default inventory with a name, size, and inventoryHolder
     *
     * @param name   inventories name
     * @param size   inventories size
     * @param player inventories holder
     */
    public InventoryBuilder(String name, int size, InventoryHolder player) {
        this.inv = Bukkit.createInventory(player, size, colorizeMessage(name));
    }

    /**
     * creates a default inventory with a name, and an inventoryType, replacing size
     *
     * @param name inventories name
     * @param type type of inventory to be created
     */
    public InventoryBuilder(String name, InventoryType type) {
        this.inv = Bukkit.createInventory(null, type, colorizeMessage(name));
    }

    /**
     * creates a default inventory with a name, an inventoryType, and an inventoryHolder
     *
     * @param name   inventories name
     * @param type   type of inventory to be created
     * @param player inventories holder
     */
    public InventoryBuilder(String name, InventoryType type, InventoryHolder player) {
        this.inv = Bukkit.createInventory(player, type, colorizeMessage(name));
    }

    /**
     * replace this instance's inventory with a specified inventory
     *
     * @param inventory inventory to replace this instance's inventory
     */
    public InventoryBuilder(Inventory inventory) {
        this.inv = inventory;
    }

    /**
     * recreate this instance's inventory
     *
     * @return this instance's new inventory
     */
    public InventoryBuilder reCreate() {
        return new InventoryBuilder(this.inv);
    }

    /**
     * set the specified itemStack at the specified slot in this instance's inventory
     *
     * @param index     slot to place itemStack in this instance's inventory
     * @param itemStack itemStack to place in the slot of this instance's inventory
     * @return set item to this instance's inventory
     */
    public InventoryBuilder setSlot(int index, ItemStack itemStack) {
        this.inv.setItem(index, itemStack);
        return this;
    }

    /**
     * fill the top of the inventory with a glass color. the parameter only needs the color.
     * ex:
     * PROPER USAGE:
     * fillTop(WHITE)
     * INVALID USAGE:
     * fillTop(MATERIAL.WHITE_STAINED_GLASS_PANE)
     *
     * @param glassColor glass color to use
     * @return applied filling to this instance's inventory
     */
    public InventoryBuilder fillTop(String glassColor) {
        for (int i = 0; i < 9; i++) {
            this.inv.setItem(i, new ItemStack(Material.valueOf(glassColor + "_STAINED_GLASS_PANE")));
        }
        return this;
    }

    /**
     * fill the top of the inventory with an itemStack.
     *
     * @param is itemStack to use
     * @return applied filling to this instance's inventory
     */
    public InventoryBuilder fillTop(ItemStack is) {
        for (int i = 0; i < 9; i++) {
            this.inv.setItem(i, is);
        }
        return this;
    }

    /**
     * fill the bottom part of the inventory with a specified glass color. This method is similar to fillTop so please read
     * that's documentation
     *
     * @param glassColor color of glass to use
     * @param rows       rows of inventory
     * @return Applied filling to this instance's inventory
     */
    public InventoryBuilder fillBottom(String glassColor, int rows) {
        ItemStack filler;
        Material material = Material.getMaterial(glassColor + "_STAINED_GLASS_PANE");
        if (material == null) {
            filler = new ItemStack(Material.BEDROCK);
        } else {
            filler = new ItemStack(Objects.requireNonNull(Material.getMaterial(glassColor + "_STAINED_GLASS_PANE")));
        }
        for (int i = (rows - 1) * 9; i < this.inv.getSize(); i++) {
            this.inv.setItem(i, filler);
        }
        return this;
    }

    /**
     * fills the bottom part of the inventory with a specified itemStack.
     *
     * @param is   itemStack to be used
     * @param rows rows of inventory
     * @return applied filling to this instance's inventory
     */
    public InventoryBuilder fillBottom(ItemStack is, int rows) {
        for (int i = (rows - 1) * 9; i < this.inv.getSize(); i++) {
            this.inv.setItem(i, is);
        }
        return this;
    }

    /**
     * fills in the sides of the inventory with the specified glass color. This method handles the parameters similar to
     * the fillTop method, so please read its documentation.
     *
     * @param glassColor color of glass to use
     * @param size       size of inventory
     * @return applied filling of this instance's inventory
     */
    public InventoryBuilder fillSides(String glassColor, int size) {
        ItemStack filler;
        Material material = Material.getMaterial(glassColor + "_STAINED_GLASS_PANE");
        if (material == null) {
            filler = new ItemStack(Material.BEDROCK);
        } else {
            filler = new ItemStack(Objects.requireNonNull(Material.getMaterial(glassColor + "_STAINED_GLASS_PANE")));
        }
        switch (size) {
            case 9:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                break;
            case 18:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                break;
            case 27:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                break;
            case 36:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                break;
            case 45:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                this.inv.setItem(36, filler);
                this.inv.setItem(44, filler);
                break;
            case 54:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                this.inv.setItem(36, filler);
                this.inv.setItem(44, filler);
                this.inv.setItem(45, filler);
                this.inv.setItem(53, filler);
                break;
        }
        return this;
    }

    public InventoryBuilder fillSides(ItemStack filler, int size){
        switch (size) {
            case 9:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                break;
            case 18:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                break;
            case 27:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                break;
            case 36:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                break;
            case 45:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                this.inv.setItem(36, filler);
                this.inv.setItem(44, filler);
                break;
            case 54:
                this.inv.setItem(0, filler);
                this.inv.setItem(8, filler);
                this.inv.setItem(9, filler);
                this.inv.setItem(17, filler);
                this.inv.setItem(18, filler);
                this.inv.setItem(26, filler);
                this.inv.setItem(27, filler);
                this.inv.setItem(35, filler);
                this.inv.setItem(36, filler);
                this.inv.setItem(44, filler);
                this.inv.setItem(45, filler);
                this.inv.setItem(53, filler);
                break;
        }
        return this;
    }

    /**
     * fills in the remaining spaces of the inventory that are either Material. AIR, or null. This method handles the parameters
     * similar to the fillTop method, so please read its documentation.
     *
     * @param glassColor color of glass to use
     * @return applied filling of this instance's inventory
     */
    public InventoryBuilder fillIn(String glassColor) {
        ItemStack filler;
        Material material = Material.getMaterial(glassColor + "_STAINED_GLASS_PANE");
        if (material == null) {
            filler = new ItemStack(Material.BEDROCK);
        } else {
            filler = new ItemStack(Objects.requireNonNull(Material.getMaterial(glassColor + "_STAINED_GLASS_PANE")));
        }
        for (int i = 0; i < this.inv.getSize(); i++) {
            this.inv.setItem(i, filler);
        }
        return this;
    }

    /**
     * fills in the remaining spaces of the inventory that are either Material. AIR, or null with the parameterized itemStack.
     *
     * @param is itemStack to use
     * @return applied filling of this instance's inventory
     */
    public InventoryBuilder fillIn(ItemStack is) {
        for (int i = 0; i < this.inv.getSize(); i++) {
            this.inv.setItem(i, is);
        }
        return this;
    }

    /**
     * replaces this instance's inventory contents with the specified array of itemStacks.
     *
     * @param itemStacks itemStack array to replace this instance's inventory with
     * @return new items of this instance's inventory
     */
    public InventoryBuilder replaceInventory(ItemStack[] itemStacks) {
        if (itemStacks.length > this.inv.getSize()) return null;
        this.inv.setStorageContents(itemStacks);
        return this;
    }


    /**
     * gets this instance's inventory
     *
     * @return this instance's inventory
     */
    public Inventory build() {
        return this.inv;
    }

    /**
     * gets this instance's inventory items
     *
     * @return the contents of this instance's inventory
     */
    public ItemStack[] getInventoryItems() {
        return this.inv.getContents();
    }

    /**
     * gets the holder of this instance's inventory
     *
     * @return the holder of this instance's inventory
     */
    public InventoryHolder getHolder() {
        return this.inv.getHolder();
    }

    /**
     * gets the size of this instance's inventory
     *
     * @return the size of this instance's inventory
     */
    public int getSize() {
        return this.inv.getSize();
    }

    private String colorizeMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
