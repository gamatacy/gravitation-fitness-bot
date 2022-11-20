package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import com.gravitationfitness.telegrambot.enums.TrainersZones
import com.gravitationfitness.telegrambot.enums.UrlEnums
import com.gravitationfitness.telegrambot.model.Trainer
import com.gravitationfitness.telegrambot.model.TrainerRepository
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class AddTrainerContent(override val text: String, private val trainerRepository: TrainerRepository) : AbstractContent("Добавить тренера",0) {

    private fun addTrainer(str: String): String {
        val parsedString = str.split(";")
        if (parsedString.size != 4){
            return "Не 4 аргумента!"+
                    "Нужно вводить данные по шаблону:\n" +
                    "add;имя тренера;url на изображение;зона\n" +
                    "Пример:\n" +
                    "add;Сергей Васильевич Рампампам;${UrlEnums.IMAGE_NOT_FOUND.url};зона"
        }

        val list = mutableListOf<String>()

        for (zone in TrainersZones.values()){
            list.add(zone.zoneType)
        }

        if (parsedString[3] !in list){
            return "Неправильное название зоны!"
        }

        return try{
            val trainer = Trainer()
            trainer.name = parsedString[1]
            trainer.imageLink = parsedString[2]
            trainer.zone = parsedString[3]
            trainerRepository.save(trainer)
            "Тренер успешно добавлен!"
        }catch (e: Exception){
            "Ошибка! Проверьте правильность введенных данных."
        }

    }

    override fun getContentKeyboard(): ReplyKeyboardMarkup {
        addTrainer(text)
        return KeyboardFactory.getKeyboard(
            0,
            mutableListOf(),
            true
        )
    }

}