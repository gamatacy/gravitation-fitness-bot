package com.gravitationfitness.telegrambot.config

import com.gravitationfitness.telegrambot.TelegramBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Component
class BotInitializer{

    @Autowired
    lateinit var bot: TelegramBot

    @EventListener(ContextRefreshedEvent::class)
    fun init() {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        try {
            botsApi.registerBot(bot)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}