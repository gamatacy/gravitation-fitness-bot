package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import com.gravitationfitness.telegrambot.enums.TrainersZones
import com.gravitationfitness.telegrambot.model.TrainerRepository
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class TrainerListContent(
    override val text: String = "Выберите тренера",
    private val zone: TrainersZones,
    private val trainerRepository: TrainerRepository
): AbstractContent("Тренеры в выбранной зоне", 0)
{

    private val listOfTrainers = mutableListOf<String>()

    override fun getContentKeyboard(): ReplyKeyboardMarkup {

        for (name in trainerRepository.findAll()){
            if (name.zone == zone.zoneType){
                name.name?.let { listOfTrainers.add(it) }
            }
        }

        val keyboard =  KeyboardFactory.getKeyboard(
            listOfTrainers.size,
            listOfTrainers,
            true
        )

        listOfTrainers.clear()

        return keyboard
    }

}