package me.afatcookie.cookcaptcha.listeners;

import me.afatcookie.cookcaptcha.captchainfo.CaptchaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PJoinEvent implements Listener {

    private final CaptchaManager captchaManager;

    public PJoinEvent(CaptchaManager captchaManager) {
        this.captchaManager = captchaManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        captchaManager.displayCaptcha(p);
    }

}
