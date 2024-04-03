package me.afatcookie.cookcaptcha.captchainfo;

import me.afatcookie.cookcaptcha.gui.GUI;
import me.afatcookie.cookcaptcha.gui.InventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class CaptchaInvDisplay implements GUI {

    private Inventory inv;
    private final ArrayList<Material> correctPattern;

    private final ArrayList<Material> currentPattern;

    private ItemStack[] contents;

    private final Player player;

    public CaptchaInvDisplay(Player player) {
        this.correctPattern = new ArrayList<>();
        this.currentPattern = new ArrayList<>();
        this.player = player;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, ClickType clickType, int slot, ItemStack cursor) {
        if (inventory.toString().contains("Custom")) {
            currentPattern.add(clickedItem.getType());
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 5,10);
            if (currentPattern.size() == correctPattern.size()) {
               player.closeInventory();
            } else {
                updateInv(currentPattern.size());
            }
        }

    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        comparePatterns();
    }

    @Override
    public void onDrag(Player player, Inventory inventory, ItemStack itemOnCursor) {

    }

    @Override
    public Inventory getInventory() {
         inv = new InventoryBuilder("&3Captcha", 36, this).build();
        contents = createRandomizedInventory(36);
        initiateCorrectPattern();
        updateInv(0);
        inv.setContents(contents);
        return inv;
    }


    private void updateInv(int index){
        for (ItemStack itemStack : contents){
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(ChatColor.GREEN + "Click: " + correctPattern.get(index).toString().replace("_", " "));
            itemStack.setItemMeta(im);
        }
        for (int i = 0; i < inv.getSize(); i++){
            inv.setItem(i, contents[i]);
        }

    }



    private ItemStack[] createRandomizedInventory(int size){
        Material[] materials = Material.values();
        List<Material> materialList = new ArrayList<>();
        Collections.addAll(materialList, materials);
        materialList.remove(Material.AIR);
        materialList.removeIf(mat -> !mat.isItem());
        Collections.shuffle(materialList);

        ItemStack[] inventory = new ItemStack[size];

        for (int i = 0; i < size; i++) {
            Material randomMaterial = materialList.get(i % materialList.size());
            inventory[i] = new ItemStack(randomMaterial, 1);// Assuming each ItemStack has quantity 1
        }
        return inventory;
    }

    private void initiateCorrectPattern(){
        while (correctPattern.size() != 3){
            correctPattern.add(contents[generatePotentialRandomNum()].getType());
        }
    }

    private int generatePotentialRandomNum(){
        Random random = new Random();
        int randomVal = random.nextInt(contents.length);
        if (randomVal != 0){
            randomVal--;
        }
        for (Material mat : correctPattern){
            if (mat.equals(contents[randomVal].getType())){
                generatePotentialRandomNum();
                break;
            }
        }
        return randomVal;
    }

    private void comparePatterns(){
        if (player.isOnline()) {
            if (currentPattern.size() != correctPattern.size()) {
                removePlayer();
                return;
            }
        }
        for (int i = 0; i < correctPattern.size(); i++){
            if (correctPattern.get(i).toString().equals(currentPattern.get(i).toString())) continue;
            removePlayer();
            break;
        }
        player.closeInventory();
    }

    private void removePlayer(){
        player.kickPlayer("Failed Captcha");
    }
}
