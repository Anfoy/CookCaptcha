package me.afatcookie.cookcaptcha.gui;

import me.afatcookie.cookcaptcha.captchainfo.CaptchaInvDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {

    @EventHandler
    public static void onClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof GUI)) return;
        if (e.getCurrentItem() == null) return;
        if (e.getClickedInventory() == null) return;
        final GUI getGUI = (GUI) e.getInventory().getHolder();
        e.setCancelled(true);
        if (getGUI instanceof CaptchaInvDisplay) {
            if (e.getClickedInventory().toString().contains("Custom")) {
                getGUI.onClick((Player) e.getWhoClicked(), e.getInventory(), e.getCurrentItem(), e.getClick(), e.getSlot(),
                        e.getCursor());
            }
        }
    }
    @EventHandler
    public static void onClose(InventoryCloseEvent e){
        if (!(e.getInventory().getHolder() instanceof GUI)) return;
        final GUI getGUI = (GUI) e.getInventory().getHolder();
        getGUI.onClose((Player) e.getPlayer(), e.getInventory());

    }
}
