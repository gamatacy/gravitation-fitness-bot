package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class MainContent(override val text: String= "Фитнес клуб Гравитация") : AbstractContent(
    "Главная страница",
    2
) {

    private val listOfContent = mutableListOf<String>(
        "Тренеры", "Нормальные чубзы"
    )

    override fun getContentKeyboard(): ReplyKeyboardMarkup {
        return KeyboardFactory.getKeyboard(
            2,
            listOfContent,
            false
        )
    }
}