package top.rainydrm.util

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe


class RecipeUtil {

    var one: List<String>? = null
    var two: List<String>? = null
    var three: List<String>? = null


    constructor(str: String) {
        one = getBracketContent(str, 1)?.split(",")
        two = getBracketContent(str, 2)?.split(",")
        three = getBracketContent(str, 3)?.split(",")

    }


    fun generateRecipe(recipeName: String, item: String): ShapedRecipe? {
        var namespacedKey = NamespacedKey("rainyrecipe", recipeName)
        MaterialMap.getMaterial(item)?.let { ItemStack(it) }?.let {


            var shapedRecipe = ShapedRecipe(namespacedKey, it)
            shapedRecipe.shape("123", "456", "789")
            var count = 1
            one?.forEach { arr ->
                if (arr == null) {
                    shapedRecipe.setIngredient(count.toChar(), Material.AIR)

                } else {
                    MaterialMap.getMaterial(arr)?.let { it1 -> shapedRecipe.setIngredient(count.toChar(), it1) }
                }
                count++
            }

            two?.forEach { arr ->
                if (arr == null) {
                    shapedRecipe.setIngredient(count.toChar(), Material.AIR)

                } else {
                    MaterialMap.getMaterial(arr)?.let { it1 -> shapedRecipe.setIngredient(count.toChar(), it1) }
                }
                count++
            }
            three?.forEach { arr ->
                if (arr == null) {
                    shapedRecipe.setIngredient(count.toChar(), Material.AIR)

                } else {
                    MaterialMap.getMaterial(arr)?.let { it1 -> shapedRecipe.setIngredient(count.toChar(), it1) }
                }
                count++
            }
            return shapedRecipe
        }
        return null

    }


    /**
     * 获取括号内的内容
     */
    private fun getBracketContent(text: String, index: Int): String? {
        var openCount = 0
        var closeCount = 0
        var currentIndex = 0
        var targetIndex = 0

        while (currentIndex < text.length) {
            when (text[currentIndex]) {
                '[' -> {
                    openCount++
                    if (openCount == index) {
                        targetIndex = currentIndex + 1
                    }
                }

                ']' -> {
                    closeCount++
                    if (openCount == closeCount && targetIndex > 0) {
                        return text.substring(targetIndex, currentIndex)
                    }
                }
            }
            currentIndex++
        }

        return null // 如果没有找到指定索引的括号对
    }


    constructor()
}