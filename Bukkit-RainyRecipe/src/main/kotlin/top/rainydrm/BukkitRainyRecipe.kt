package top.rainydrm

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BukkitRainyRecipe :JavaPlugin() {
    override fun onEnable() {
        Bukkit.getLogger().info("插件启动成功");
    }

    override fun onDisable() {

    }

}