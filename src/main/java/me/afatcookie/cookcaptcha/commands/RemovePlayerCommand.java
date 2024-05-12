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
 * RemovePlayer Command Manager
 */
public class RemovePlayerCommand extends CommandBuilder{


    private final CookCaptcha instance;
    public RemovePlayerCommand(CookCaptcha instance){
        this.instance = instance;
    }

    @Override
    public String getName() {
        return "removePlayer";
    }

    @Override
    public String getDescription() {
        return "Removes player from whitelist.";
    }

    @Override
    public String getSyntax() {
        return "/CookCap removePlayer {player name}";
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof Player)) return;
        Player player = (Player) commandSender;
        if (strings.length > 1){
            Player target = Bukkit.getPlayer(strings[1]);
            if (target != null){
                instance.getWlc().getWhitelist().remove(target.getUniqueId());
                player.sendMessage(ChatColor.GREEN + " " + "Successfully removed player from whitelist!");
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
