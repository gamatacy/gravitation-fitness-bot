package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import com.gravitationfitness.telegrambot.enums.TrainersZones
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class TrainerChoiceContent(override val text: String = "Выберите зону"): AbstractContent(
    "Тренеры",
    TrainersZones.values().size
) {

    private val listOfZones = mutableListOf<String>()

    companion object{

        fun getListOfZones(): MutableList<String>{
            val list = mutableListOf<String>()
            for (zone in TrainersZones.values()){
                list.add(zone.zoneType)
            }
            return list
        }

    }

    override fun getContentKeyboard(): ReplyKeyboardMarkup {
        for (zone in TrainersZones.values()){
            listOfZones.add(zone.zoneType)
        }
        return KeyboardFactory.getKeyboard(
            TrainersZones.values().size,
            listOfZones,
            true
        )
    }


}