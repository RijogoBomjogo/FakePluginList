package tr.donsuzturk.fakepluginlist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener
{
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCommandPreproccess(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String message = e.getMessage();
        final String[] params = message.split(" ");
        if (params[0].startsWith("/bukkit:") || params[0].equalsIgnoreCase("/pl") || params[0].equalsIgnoreCase("/plugins") || params[0].equalsIgnoreCase("/?")) {
            p.sendMessage(Objects.requireNonNull(this.getConfig().getString("mesaj")).replace("&", "§"));
            e.setCancelled(true);
        }
    }
}
