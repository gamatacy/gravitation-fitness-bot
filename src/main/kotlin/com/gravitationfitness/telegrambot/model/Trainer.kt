package com.gravitationfitness.telegrambot.model

import com.gravitationfitness.telegrambot.enums.TrainersZones
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "trainersGravitation")
@NoArgsConstructor
open class Trainer(
    @Id
    open var name: String? = null,

    @Column(length = 1024)
    open var description: String? = null,

    open var zone: String? = null,

    @Column(length = 1024)
    open var imageLink: String? = null
) {
}