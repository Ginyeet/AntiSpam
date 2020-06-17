package mydeacy.antispam

import cn.nukkit.Player
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerChatEvent


class EventListener(private val main: AntiSpam) : Listener{

    @EventHandler
    fun onChat(event: PlayerChatEvent){
        val player: Player = event.player
        val name: String = player.name
        if(main.count[name] == null){
            main.count[name] = 0
        }
        if(main.flag[name] == true) {
            event.setCancelled()
            return
        }
        main.count[name] = main.count[name]!! + 1
        if(main.isSpam(name)){
            event.setCancelled()
            main.flag[name] = true
            main.muteTask(name)
        }else{
            main.chatTask(name)
        }
    }
}
