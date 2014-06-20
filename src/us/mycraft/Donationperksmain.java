package us.mycraft;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Donationperksmain extends JavaPlugin implements Listener{
	// this is assigning a new logger object variable called log

	Logger log;

	@Override
	public void onEnable() {
		File f = new File(getDataFolder(), "config.yml");
		if (!f.exists()) {
			saveDefaultConfig();
		}
		saveResource("README.txt", false);
		// assigning log to an actual logger object
		// this.log.info("") will log [pluginname] message
		this.log = getLogger();
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("donationperks")) {
			// this doesn't
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "Ranks:");
				for (String s : getConfig().getConfigurationSection("ranks").getKeys(false)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ranks." + s + ".name")));
					sender.sendMessage(" Perks:");
					for(String perk : getConfig().getStringList("ranks." + s + ".perks")){
						sender.sendMessage(" - " + ChatColor.translateAlternateColorCodes('&',perk));
					}
				}				
				return true;
			}else{
				String rank = args[0];
				if (this.getConfig().get("ranks." + rank) != null) {
					for (String s : this.getConfig().getStringList("ranks." + rank + ".perks")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
					}
					return true;
				}				
			}			
		}
		return false;		
	}
}