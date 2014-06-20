/* This file is part of Donation Perks.
 *
 * Donation Perks is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package us.mycraft;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Donationperksmain extends JavaPlugin {
	private Logger log;
	@Override
	public void onEnable() {
		File f = new File(getDataFolder(), "config.yml");
		if (!f.exists()) {
			saveDefaultConfig();
		}
		saveResource("README.txt", false);
		this.log = getLogger();
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("donationperks")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "Ranks:");
				for (String s : getConfig().getConfigurationSection("ranks").getKeys(false)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("ranks." + s + ".name")));
					sender.sendMessage(" Perks:");
					for (String perk : getConfig().getStringList("ranks." + s + ".perks")) {
						sender.sendMessage(" - "+ ChatColor.translateAlternateColorCodes('&',perk));
					}
				}
				return true;
			} else {
				String rank = args[0];
				if (this.getConfig().get("ranks." + rank) != null) {
					for (String s : this.getConfig().getStringList("ranks." + rank + ".perks")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
					}
					return true;
				}
			}
		}
		return false;
	}
}