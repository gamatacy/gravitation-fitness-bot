package com.gravitationfitness.telegrambot.model

import lombok.NoArgsConstructor
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "usersGravitation")
@NoArgsConstructor
open class User(
    @Id
    open var id: Long? = null,

    open var chatId: Long? = null,

    open var firstName: String? = null,

    open var lastName: String? = null,

    open var registrationTime: Timestamp? = null,

    open var isAdmin: Boolean? = null
) {}