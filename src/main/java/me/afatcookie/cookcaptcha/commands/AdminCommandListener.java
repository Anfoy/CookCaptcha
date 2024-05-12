package me.afatcookie.cookcaptcha.commands;

import me.afatcookie.cookcaptcha.CookCaptcha;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Command listener
 */
public class AdminCommandListener implements TabExecutor {

    public static final ArrayList<CommandBuilder> subCommands = new ArrayList<>();
    public static final ArrayList<String> onTabComplete = new ArrayList<>();
    public AdminCommandListener(CookCaptcha instance){
        subCommands.add(new AddPlayerCommand(instance));
        subCommands.add(new RemovePlayerCommand(instance));
        subCommands.add(new ReloadConfigsCommand(instance));
        for (CommandBuilder sub : subCommands){
            onTabComplete.add(sub.getName());
            CommandBuilder.adminSubbiesPass.add(sub.getName());
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            if (args[0].equals("help")){
                sender.sendMessage("---------------");
                for (CommandBuilder subCommand : getSubCommands()) {
                    sender.sendMessage(subCommand.getSyntax() + " | " + subCommand.getDescription());
                }
                sender.sendMessage("---------------");
                return true;
            }
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {

                    CommandBuilder subCommand = subCommands.get(i);
                    subCommand.execute(sender, args);
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "Invalid command");
        }
        sender.sendMessage(ChatColor.YELLOW + "Use '/CookCap help' to see a list of valid commands");

        return true;
    }

    public ArrayList<CommandBuilder> getSubCommands(){
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> subcommandsArguments = new ArrayList<>();

            StringUtil.copyPartialMatches(args[0], onTabComplete, subcommandsArguments);

            return subcommandsArguments;
        }
        else if (args.length >= 2) {
            for (CommandBuilder subcommand : subCommands) {
                if (args[0].equalsIgnoreCase(subcommand.getName())) {
                    return subcommand.getSubCommandArgs((Player) sender, args);
                }
            }
        }

        return null;
    }
    }
