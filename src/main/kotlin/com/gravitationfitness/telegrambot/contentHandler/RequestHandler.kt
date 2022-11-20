package com.gravitationfitness.telegrambot.contentHandler

import com.gravitationfitness.telegrambot.contentHandler.contents.*
import com.gravitationfitness.telegrambot.enums.TrainersZones
import com.gravitationfitness.telegrambot.model.Trainer
import com.gravitationfitness.telegrambot.model.TrainerRepository
import org.telegram.telegrambots.meta.api.objects.Message

class RequestHandler {

    init {
        ContentHistory.newHistory()
    }

    fun handleRequest(message: Message, isAdmin: Boolean, trainerRepository: TrainerRepository): AbstractContent {
        when (message.text){
            "/start" -> return MainContent()
            "Тренеры" -> return TrainerChoiceContent()
            "Назад" -> {
                return ContentHistory.getPreviousContent()
            }
            in TrainerChoiceContent.getListOfZones() -> return TrainerListContent("Выберите тренера", TrainersZones.getByValue(message.text), trainerRepository)
            in TrainerContent.getTrainers(trainerRepository) -> return TrainerContent(message.text,trainerRepository,message.text)
        }
        return MainContent()
    }


}