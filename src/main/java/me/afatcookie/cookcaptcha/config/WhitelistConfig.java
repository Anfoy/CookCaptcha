package me.afatcookie.cookcaptcha.config;

import me.afatcookie.cookcaptcha.CookCaptcha;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * WhiteList Config Manager
 */
public class WhitelistConfig extends AFCConfig{


    private final ArrayList<UUID> whitelist;


    public WhitelistConfig(CookCaptcha instance, String filePath, String mainPath) {
        super(instance, filePath, mainPath);
        whitelist = getWhiteList();
    }



    @Override
    public void establishDefaults(){
        getConfig().addDefault("whitelist", new ArrayList<String>());
        super.establishDefaults();
    }


    private ArrayList<UUID> getWhiteList(){
        List<String> names = getConfig().getStringList("whitelist");
        ArrayList<UUID> whitelistedNames = new ArrayList<>();
        for (String name : names){
            whitelistedNames.add(UUID.fromString(name));
        }
        return whitelistedNames;
    }


    public ArrayList<UUID> getWhitelist() {
        return whitelist;
    }
}
