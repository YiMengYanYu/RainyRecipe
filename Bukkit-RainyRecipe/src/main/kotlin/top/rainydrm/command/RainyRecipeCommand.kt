package top.rainydrm.command

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import top.rainydrm.config.RecipeConfig
import top.rainydrm.util.MaterialMap
import top.rainydrm.util.RecipeUtil
import java.util.*

class RainyRecipeCommand : TabExecutor {
    /**
     * 命令提示
     */
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {

        val tips = LinkedList<String>()
        //处理第一个参数
        if (args.size == 1) {
            //如果只输入一个空格时
            var mutableList = ArrayList<String>()
            addPermissionList(mutableList, sender)
            if (args[0].isEmpty()) {
                //添加所有信息
                tips.addAll(mutableList)
                return tips
            } else {
                //已经开始输入字符了，则遍历已有信息，并将 信息的小写toLowerCase()通过startWith()检查该arg[0]的小写是否匹配
                for (firstArg in mutableList) {
                    if (firstArg.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        tips.add(firstArg)
                    }
                }
                return tips
            }
        } else if (args.size == 2) {

            tips.add("配方名称(必须为英文)")
        } else if (args.size == 3) {

            tips.add("{[,,],[,,],[,,]}")
            return tips

        } else if (args.size == 4) {
            tips.addAll(MaterialMap.getKeySet(args[3]))

            return tips

        }

        return tips
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (args.isEmpty()) {
            Bukkit.getLogger().info("args.isEmpty()")
            return true
        }

        if (args[0] == null) {
            Bukkit.getLogger().info("args[0] == null")
            return true
        }

        if (args[0] == "add") {
            if (!sender.hasPermission("RainyRecipe.add")) {
                sender.sendMessage("你没有执行命令的权限")
                return true
            }
            if (args.size != 4) {
                sender.sendMessage("参数错误")
                return true
            }

            if (!isValidKey(args[1])) {
                sender.sendMessage("配方名称不符合要求")
                return true
            }

            var namespacedKey = NamespacedKey("rainyrecipe", args[1])
            if (Bukkit.getRecipe(namespacedKey) != null) {

                sender.sendMessage("这个配方名称已经存在")
                return true
            }
            if (!MaterialMap.existMaterial(args[3])) {

                sender.sendMessage("你要合成的物品不存在或者输入的名称错误")
                return true

            }


            var recipe = try {
                RecipeUtil(args[2]).generateRecipe(args[1], args[3])
            } catch (e: Exception) {
                sender.sendMessage(e.message)
               return true
            }


            Bukkit.addRecipe(recipe)
            RecipeConfig.setRecipe(args[1], args[2], args[3])

            sender.sendMessage("${ChatColor.YELLOW}添加新配方${args[1]}成功")

        }

        if (args[0] == "delete") {
            if (!sender.hasPermission("RainyRecipe.delete")) {
                return true
            }

        }
        if (args[0] == "list") {
            if (!sender.hasPermission("RainyRecipe.list")) {
                return true
            }

        }



        return true
    }

    /**
     * 验证配方名称是否符合邀请
     */
    fun isValidKey(keyName: String): Boolean {
        val regex = """^[a-zA-Z0-9._/-]+$""".toRegex()
        return regex.matches(keyName)
    }

    /**
     * 添加集合权限
     */
    private fun addPermissionList(list: MutableList<String>, sender: CommandSender) {

        if (sender.hasPermission("RainyRecipe.add")) {
            list.add("add")
            Bukkit.getLogger().info("add")

        }
        if (sender.hasPermission("RainyRecipe.list")) {
            list.add("list")
            Bukkit.getLogger().info("list")
        }
        if (sender.hasPermission("RainyRecipe.delete")) {
            list.add("delete")
            Bukkit.getLogger().info("delete")
        }
    }

}