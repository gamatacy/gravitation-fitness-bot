package com.gravitationfitness.telegrambot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.gravitationfitness.telegrambot.model"])
class TelegramBotApplication

fun main(){
    runApplication<TelegramBotApplication>()
}

