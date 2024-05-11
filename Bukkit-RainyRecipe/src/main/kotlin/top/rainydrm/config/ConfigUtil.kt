package top.rainydrm.config

import org.bukkit.configuration.file.YamlConfiguration
import top.rainydrm.BukkitRainyRecipe.Companion.getInstance
import java.io.File


/**
 * @author YiMeng
 * @DateTime: 2024/4/12 19:20
 * @Description:
 */
object ConfigUtil {
    private val bukkitRainyRecipe = getInstance()


    /**
     * 使用Bukkit加载配置文件,如果resources(资源目录)下没有配置文件就不会加载
     *
     */
    fun createConfig(configName: String): YamlConfiguration {
        val file = File(bukkitRainyRecipe!!.dataFolder, configName)
        if (!file.exists()) {
            bukkitRainyRecipe.logger.info("$configName 文件创建成功")
            bukkitRainyRecipe.saveResource(configName, false)
        }
        val yamlConfiguration = YamlConfiguration.loadConfiguration(file)
        bukkitRainyRecipe.logger.info("$configName 文件成功加载")
        return yamlConfiguration
    }
}
