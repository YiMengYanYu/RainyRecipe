package top.rainydrm.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe


class RecipeUtil {
    /**
     * 第一行的物品
     */
    lateinit var one: List<String>

    /**
     * 第二行的物品
     */
    lateinit var two: List<String>

    /**
     * 第二行的物品
     */
    lateinit var three: List<String>


    constructor(str: String) {
        one = getBracketContent(str, 1)?.split(",")!!
        two = getBracketContent(str, 2)?.split(",")!!
        three = getBracketContent(str, 3)?.split(",")!!



    }

    fun verifyItems() {
        if (one.size != 3 || two.size != 3 || three.size != 3) {


            throw Exception("配方的参数不符合要求")
        }

        one.forEach{
            if (it!=""&&!MaterialMap.existMaterial(it)){

                throw Exception("${ChatColor.RED}[${it}]${ChatColor.YELLOW}这个物品不存在或者输入错误")
            }
        }

        two.forEach{
            if (it!=""&&!MaterialMap.existMaterial(it)){

                throw Exception("${ChatColor.RED}[${it}]${ChatColor.YELLOW}这个物品不存在或者输入错误")
            }
        }
        three.forEach{
            if (it!=""&&!MaterialMap.existMaterial(it)){

                throw Exception("${ChatColor.RED}[${it}]${ChatColor.YELLOW}这个物品不存在或者输入错误")
            }
        }
    }


    /**
     * 生成配方
     */
    fun generateRecipe(recipeName: String, item: String): ShapedRecipe {
        if (item == null) {
            throw Exception("物品不能为空")
        }

        var namespacedKey = NamespacedKey("rainyrecipe", recipeName)
        var shapedRecipe =
            ShapedRecipe(namespacedKey, ItemStack(MaterialMap.getMaterial(item))).shape("123", "456", "789")

        setIngredient(shapedRecipe)

        return shapedRecipe

    }

    private fun setIngredient(shapedRecipe: ShapedRecipe) {
        var count: Int = 1
        one.forEach {
            shapedRecipe.setIngredient(count.toString()[0], MaterialMap.getMaterial(it))
            count++

        }
        two.forEach {
            shapedRecipe.setIngredient(count.toString()[0], MaterialMap.getMaterial(it))
            count++

        }
        three.forEach {
            shapedRecipe.setIngredient(count.toString()[0], MaterialMap.getMaterial(it))
            count++

        }

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


}