package com.gravitationfitness.telegrambot.model

import com.gravitationfitness.telegrambot.enums.TrainersZones
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "trainersGravitation")
@NoArgsConstructor
open class Trainer(
    @Id
    open var name: String? = null,

    open var description: String? = null,

    open var zone: String? = null,

    open var imageLink: String? = null
) {
}