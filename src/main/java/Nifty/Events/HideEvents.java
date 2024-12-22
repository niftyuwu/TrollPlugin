package Nifty.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class HideEvents implements Listener {

    private final JavaPlugin plugin;

    public HideEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void AsyncPlayerChatEvent(AsyncChatEvent event) {
        Player who = event.getPlayer();
        String message = event.signedMessage().message();

        if (message.startsWith("~hide ") || message.startsWith("~unhide ")) {
            boolean shouldHide = message.startsWith("~hide ");
            String args = message.substring(shouldHide ? 6 : 8);
            String[] subArgs = args.split(" ");
            if (subArgs.length != 2) {
                who.sendMessage("Wrong parameters. Use: ~hide <hidden-player> <viewer> or ~unhide <hidden-player> <viewer>");
                event.setCancelled(true);
                return;
            }

            Player hiddenPlayer = Bukkit.getPlayer(subArgs[0]);
            Player viewer = Bukkit.getPlayer(subArgs[1]);
            if (hiddenPlayer == null || viewer == null) {
                who.sendMessage("One or both players were not found.");
                event.setCancelled(true);
                return;
            }

            if (shouldHide) {
                viewer.hidePlayer(plugin, hiddenPlayer);
                who.sendMessage(hiddenPlayer.getName() + " is hidden from " + viewer.getName() + ".");
            } else {
                viewer.showPlayer(plugin, hiddenPlayer);
                who.sendMessage(hiddenPlayer.getName() + " is visible to " + viewer.getName() + " again.");
            }
            event.setCancelled(true);
        }
    }
}
