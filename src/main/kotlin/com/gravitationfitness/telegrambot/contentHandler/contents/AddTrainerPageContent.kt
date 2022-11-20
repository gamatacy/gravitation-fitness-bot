package com.gravitationfitness.telegrambot.contentHandler.contents

import com.gravitationfitness.telegrambot.contentHandler.KeyboardFactory
import com.gravitationfitness.telegrambot.enums.UrlEnums
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class AddTrainerPageContent(
    override val text: String =
            "Для добавления тренера нужно:\n" +
            "1. ФИО\n" +
            "2. URL на изображение (открываете изображение в интернете, щелкаете ПКМ и нажимаете 'скопировать адрес изображения')\n" +
            "3. Зона. Обязательно указывать значение из списка: \n- Зона единоборств\n- Аква зона\n- Тренажерный зал\n- Групповые программы\n" +
            "Отклонения в написании не допускаются.\n" +
            "После чего вводите эти данные через ' ; ' , введя в начало 'add' :\n" +
            "add;имя тренера;url на изображение;зона\n"
) : AbstractContent("Инструкция как добавить тренера", 0) {

    override fun getContentKeyboard(): ReplyKeyboardMarkup {
        return KeyboardFactory.getKeyboard(
            0,
            mutableListOf(),
            true
        )
    }

}