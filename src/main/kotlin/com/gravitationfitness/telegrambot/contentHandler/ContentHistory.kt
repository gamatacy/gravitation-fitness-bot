package com.gravitationfitness.telegrambot.contentHandler

import com.gravitationfitness.telegrambot.contentHandler.contents.AbstractContent
import com.gravitationfitness.telegrambot.contentHandler.contents.MainContent
import java.util.ArrayDeque

class ContentHistory private constructor(){

    companion object{
        private val historyStack = ArrayDeque<AbstractContent>()

        fun newHistory(): ContentHistory{
            return ContentHistory()
        }

        fun updateHistory(content: AbstractContent){
            historyStack.push(content)
            if (historyStack.size > 4){
                historyStack.removeLast()
                println(historyStack.toString())
            }
        }

        fun getPreviousContent(): AbstractContent {

            if (historyStack.size < 2){
                return MainContent()
            }

            historyStack.pop()
            return historyStack.pop()
        }

    }

}