package top.rainydrm

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import top.rainydrm.command.RainyRecipeCommand
import top.rainydrm.config.RecipeConfig
import top.rainydrm.util.MaterialMap

class BukkitRainyRecipe : JavaPlugin() {
    override fun onEnable() {
        instance = this
        Bukkit.getLogger().info("配方插件启动成功")
        RecipeConfig.initRecipeConfig()
        Bukkit.getPluginCommand("RainyRecipe")?.setExecutor(RainyRecipeCommand())
    }

    override fun onDisable() {
        Bukkit.getLogger().info("插件关闭成功")

    }

    companion object {
        private var instance: BukkitRainyRecipe? = null

        fun getInstance(): BukkitRainyRecipe? {
            return instance
        }
    }

}