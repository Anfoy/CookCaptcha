package me.afatcookie.cookcaptcha;

import me.afatcookie.cookcaptcha.captchainfo.CaptchaManager;
import me.afatcookie.cookcaptcha.gui.GUIListener;
import me.afatcookie.cookcaptcha.listeners.PJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CookCaptcha extends JavaPlugin {

    private CaptchaManager cm;
    @Override
    public void onEnable() {
        this.cm = new CaptchaManager();
        // Plugin startup logic
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new PJoinEvent(cm), this);
    }
}
