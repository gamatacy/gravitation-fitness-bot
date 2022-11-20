package com.gravitationfitness.telegrambot.contentHandler.contents

import lombok.Getter
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

@Getter
abstract class AbstractContent(
    val name: String,
    val buttonsCount: Int
) {
    abstract val text: String
    abstract fun getContentKeyboard(): ReplyKeyboardMarkup
}