package me.afatcookie.cookcaptcha.listeners;

import me.afatcookie.cookcaptcha.CookCaptcha;
import me.afatcookie.cookcaptcha.captchainfo.CaptchaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Player join listener, enforces captcha if player is not on whitelist
 */
public class PJoinEvent implements Listener {

    private final CaptchaManager captchaManager;

    private final CookCaptcha cookCaptcha;

    public PJoinEvent(CaptchaManager captchaManager, CookCaptcha cookCaptcha) {
        this.captchaManager = captchaManager;
        this.cookCaptcha = cookCaptcha;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (cookCaptcha.getWlc().getWhitelist().contains(p.getUniqueId())) return;
        captchaManager.displayCaptcha(p);
    }

}
