package top.rainydrm.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import java.util.*
import kotlin.collections.ArrayList

class RainyRecipeCommand : TabExecutor {
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

            tips.add("{[,,],[,,],[,,]}")
        }else if (args.size==3){


        }




        return tips
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return true
    }


    private fun addPermissionList(list: MutableList<String>, sender: CommandSender) {
        Bukkit.getLogger().info("？？？？？？？？？")
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