package com.gravitationfitness.telegrambot.model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository: CrudRepository<Trainer, String> {
}