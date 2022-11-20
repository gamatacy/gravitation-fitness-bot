package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import com.gravitationfitness.telegrambot.enums.TrainersZones
import com.gravitationfitness.telegrambot.enums.UrlEnums
import com.gravitationfitness.telegrambot.model.Trainer
import com.gravitationfitness.telegrambot.model.TrainerRepository
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class TrainerContent(
    override var text: String = "Description",
    private val trainerRepository: TrainerRepository,
    private val trainerName: String) : AbstractContent("Тренер", 0), ContentWithImage {

    override fun getContentKeyboard(): ReplyKeyboardMarkup {
        findTrainer(trainerName, trainerRepository)
        return KeyboardFactory.getKeyboard(
            0,
            mutableListOf(),
            true
        )
    }

    private fun findTrainer(name: String, trainerRepository: TrainerRepository){
        for (trainer in trainerRepository.findAll()){
            if (trainer.name == name){
                text = "${trainer.name} \n ${trainer.description}"
            }
        }
    }

    companion object{

        fun getTrainers(trainerRepository: TrainerRepository): MutableList<String>{
            val list = mutableListOf<String>()
            for (trainer in trainerRepository.findAll()){
                trainer.name?.let { list.add(it) }
            }
            return list
        }

    }

    override fun getImageUrl(): String {
        val url = trainerRepository.findById(trainerName).get().imageLink

        if (url != null){
            return url
        }

        return UrlEnums.IMAGE_NOT_FOUND.url
    }

}