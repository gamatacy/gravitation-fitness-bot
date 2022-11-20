package com.gravitationfitness.telegrambot.contentHandler

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class KeyboardFactory() {

    companion object{
        fun getKeyboard(buttonsCount: Int, buttonsText: MutableList<String>, createBackButton: Boolean): ReplyKeyboardMarkup{
            val keyboardMarkup = ReplyKeyboardMarkup()
            val keyboardRows = mutableListOf<KeyboardRow>()

            for (i in 1..buttonsCount){
                val row = KeyboardRow()
                row.add(buttonsText[i-1])
                keyboardRows.add(row)
            }

            if (createBackButton){
                val row = KeyboardRow()
                row.add("Назад")
                keyboardRows.add(row)
            }

            keyboardMarkup.keyboard = keyboardRows
            return keyboardMarkup
        }

    }

}