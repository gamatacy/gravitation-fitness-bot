package com.gravitationfitness.telegrambot

import com.gravitationfitness.telegrambot.config.BotConfig
import com.gravitationfitness.telegrambot.contentHandler.ContentHistory
import com.gravitationfitness.telegrambot.contentHandler.RequestHandler
import com.gravitationfitness.telegrambot.contentHandler.contents.AbstractContent
import com.gravitationfitness.telegrambot.contentHandler.contents.ContentWithImage
import com.gravitationfitness.telegrambot.model.TrainerRepository
import com.gravitationfitness.telegrambot.model.User
import com.gravitationfitness.telegrambot.model.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.File
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.net.URL
import java.sql.Timestamp
import javax.imageio.ImageIO

@Component
class TelegramBot(private val botConfig: BotConfig): TelegramLongPollingBot() {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var trainerRepository: TrainerRepository
    private val requestHandler = RequestHandler()

    override fun getBotToken(): String {
        return botConfig.token
    }

    override fun getBotUsername(): String {
        return botConfig.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update?.hasMessage() != null){

            registerUser(update.message)

            val content = requestHandler.handleRequest(
                update.message,
                checkAdmin(update.message),
                trainerRepository
            )

            ContentHistory.updateHistory(content)

            sendMessage(update.message.chatId, content)
        }
    }

    private fun registerUser(message: Message){
        if(userRepository.findById(message.from.id).isEmpty){

            val user = User(
                message.from.id,
                message.chatId,
                message.from.firstName,
                message.from.lastName,
                Timestamp(System.currentTimeMillis()),
                false
            )

            userRepository.save(user)
        }
    }

    private fun sendMessage(chatId: Long, content: AbstractContent){
        val sendMessage = SendMessage()

        if (content is ContentWithImage){
            val sendPhoto = SendPhoto()
            val inputFile = InputFile()

            inputFile.setMedia(content.getImageUrl())

            sendPhoto.chatId = chatId.toString()
            sendPhoto.photo = inputFile

            execute(sendPhoto)
        }

        sendMessage.chatId = chatId.toString()
        sendMessage.replyMarkup = content.getContentKeyboard()
        sendMessage.text = content.text

        execute(sendMessage)
    }

    private fun checkAdmin(message: Message): Boolean{
        return userRepository.findById(message.from.id).get().isAdmin == true
    }

}