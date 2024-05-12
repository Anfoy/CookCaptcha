package me.afatcookie.cookcaptcha.commands;

import me.afatcookie.cookcaptcha.CookCaptcha;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ReloadConfigsCommand extends CommandBuilder{

    private final CookCaptcha instance;
    public ReloadConfigsCommand(CookCaptcha instance){
        this.instance = instance;
    }
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads configs.";
    }

    @Override
    public String getSyntax() {
        return "/CookCap reload";
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length > 0){
            try {
                instance.getWlc().reload();
                commandSender.sendMessage(ChatColor.GREEN + " " + "Successfully reloaded CookCaptcha!");
            }catch (NullPointerException exception){
                commandSender.sendMessage("Failed to reload CookCaptcha");
                commandSender.sendMessage("Printing stackTrace...");
                exception.printStackTrace();
            }
        }
    }

    @Override
    List<String> getSubCommandArgs(Player player, String[] strings) {
        ArrayList<String> subbies = new ArrayList<>();

        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], adminSubbiesPass, subbies);
        }

        return null;
    }
}
