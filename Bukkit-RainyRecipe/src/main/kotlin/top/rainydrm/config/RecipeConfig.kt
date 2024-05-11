package top.rainydrm.config

import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import top.rainydrm.BukkitRainyRecipe
import top.rainydrm.util.RecipeUtil

/**
 * @author YiMeng
 * @DateTime: 2024/5/10 下午10:29
 * @Description: TODO
 */
object RecipeConfig {

    var recipeyml: YamlConfiguration? = null


    fun initRecipeConfig() {

        recipeyml = ConfigUtil.createConfig("recipe.yml")

        loadRecipe()
    }

    private fun loadRecipe() {

        val recipesSection = recipeyml!!.getConfigurationSection("recipes")

        if (recipesSection!=null){
            recipesSection.getKeys(false).forEach { recipeKey ->
                val recipe = recipesSection.getConfigurationSection(recipeKey)
                val recipeValue = recipe!!.getString("recipe")
                val item = recipe.getString("item")
                Bukkit.getLogger().info("正在加载配方: $recipeKey")
                var   generateRecipe = RecipeUtil(recipeValue!!).generateRecipe(recipeKey, item!!)
                Bukkit.addRecipe(generateRecipe)

            }
        }





    }


    fun setRecipe(recipeName: String, recipe: String, item: String) {

        var map = HashMap<String, String>(4)
        map["recipe"] = recipe
        map["item"] = item

        recipeyml!!.set("recipes.$recipeName", map)
        var instance = BukkitRainyRecipe.getInstance()

        recipeyml!!.save("${instance!!.dataFolder}\\recipe.yml")

    }

}
