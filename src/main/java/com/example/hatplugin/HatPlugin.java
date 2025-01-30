package com.example.hatplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HatPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("hat").setExecutor(this);
        getLogger().info("[HatPlugin] 已加载，适配 1.20.4！");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "只有玩家可以使用此命令！");
            return true;
        }

        Player player = (Player) sender;
        ItemStack handItem = player.getInventory().getItemInMainHand();

        if (handItem.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "你手上没有物品！");
            return true;
        }

        // 交换头盔和主手物品
        ItemStack helmet = player.getInventory().getHelmet();
        player.getInventory().setHelmet(handItem);
        player.getInventory().setItemInMainHand(helmet != null ? helmet : new ItemStack(Material.AIR));

        player.sendMessage(ChatColor.GREEN + "物品已戴在头上！");
        return true;
    }
}
