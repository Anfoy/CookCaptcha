package me.afatcookie.cookcaptcha.captchainfo;
import org.bukkit.entity.Player;

public class CaptchaManager {

    public void displayCaptcha(Player p){
        p.openInventory(new CaptchaInvDisplay(p).getInventory());
    }


}
