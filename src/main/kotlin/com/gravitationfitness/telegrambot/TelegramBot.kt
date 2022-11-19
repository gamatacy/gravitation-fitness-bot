package com.gravitationfitness.telegrambot

import com.gravitationfitness.telegrambot.config.BotConfig
import com.gravitationfitness.telegrambot.model.User
import com.gravitationfitness.telegrambot.model.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.sql.Timestamp

@Component
class TelegramBot(private val botConfig: BotConfig): TelegramLongPollingBot() {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun getBotToken(): String {
        return botConfig.token
    }

    override fun getBotUsername(): String {
        return botConfig.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage() != null){
            registerUser(update.message)
            println("Got message: ${update.message.text} by ${update.message.from.firstName}")
        }
        else{
            println("Error!")
        }
    }

    private fun registerUser(message: Message){
        if(userRepository.findById(message.from.id).isEmpty){

            val user = User(
                message.from.id,
                message.from.firstName,
                message.from.lastName,
                Timestamp(System.currentTimeMillis())
            )

            userRepository.save(user)
        }
    }

}