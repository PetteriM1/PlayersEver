package me.petterim1.playersever;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class Main extends PluginBase {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("playersever") && sender.hasPermission("playersever.command")) {
            CompletableFuture.runAsync(() -> {
                sender.sendMessage("Searching player data files...");
                int count = 0;
                try {
                    File[] files = new File(getServer().getDataPath() + "players/").listFiles();
                    for (File file : files) {
                        String name = file.getName();
                        if (name.endsWith(".dat") && !name.endsWith(".bak.dat")) {
                            count++;
                        }
                    }
                    sender.sendMessage("Found player data for " + count + " players");
                } catch (Exception ignore) {
                    sender.sendMessage("Error! Failed to access the 'players' folder");
                }
            });
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }
}
