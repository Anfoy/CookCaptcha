package me.afatcookie.cookcaptcha.commands;

import me.afatcookie.cookcaptcha.CookCaptcha;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AddPlayer Command Manager
 */
public class AddPlayerCommand extends CommandBuilder{


    private final CookCaptcha instance;
    public AddPlayerCommand(CookCaptcha instance){
        this.instance = instance;
    }
    @Override
    public String getName() {
        return "addPlayer";
    }

    @Override
    public String getDescription() {
        return "adds player to captcha whitelist";
    }

    @Override
    public String getSyntax() {
        return "/CookCap addPlayer {player name}";
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof Player)) return;
        Player player = (Player) commandSender;
        if (strings.length > 1){
            Player target = Bukkit.getPlayer(strings[1]);
            if (target != null){
                instance.getWlc().getWhitelist().add(target.getUniqueId());
                player.sendMessage(ChatColor.GREEN + " " + "Successfully added player to whitelist!");
                return;
            }
        }
        player.sendMessage(ChatColor.RED + " " + "Not enough arguments, please you the command like this: " + getSyntax());
    }

    @Override
    List<String> getSubCommandArgs(Player player, String[] strings) {
        ArrayList<String> subs = new ArrayList<>();

        if (strings.length == 1){
            StringUtil.copyPartialMatches(strings[0], adminSubbiesPass, subs);
        }
        return null;
    }
}
