package com.gravitationfitness.telegrambot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("application.properties")
class BotConfig(
    @Value("\${bot.name}")
    val name: String,
    @Value("\${bot.token}")
    val token: String)
{}