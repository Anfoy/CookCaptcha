package me.afatcookie.cookcaptcha.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Hierachy Class For Commands
 */
public abstract class CommandBuilder {


    protected static List<String> adminSubbiesPass = new ArrayList<>();

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();

    public abstract void execute(CommandSender commandSender, String[] strings);

    abstract List<String> getSubCommandArgs(Player player, String[] strings);

}
