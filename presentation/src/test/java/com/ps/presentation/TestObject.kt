package com.ps.presentation

import com.ps.domain.model.Actor
import com.ps.domain.model.ActorDetailModel
import com.ps.domain.model.ActorsModel

object TestObject {
    const val ID = 1
    const val FIRST_NAME = "Gaurav"
    const val LAST_NAME = "Dhingra"
    const val FULL_NAME = "Gaurav Dhingra"
    const val TITLE = "Rock"
    const val FAMILY = "Dhingra"
    const val IMAGE_URL = "url/image.jpg"


    val actorDetailModel = ActorDetailModel(
        id = ID,
        firstName = FIRST_NAME,
        lastName = LAST_NAME,
        fullName = FULL_NAME,
        title = TITLE,
        family = FAMILY,
        imageUrl = IMAGE_URL
    )

    private val actor = Actor(
        id = ID,
        fullName = FULL_NAME,
        family = FAMILY,
        imageUrl = IMAGE_URL
    )
    val actorsModel = ActorsModel(
        list = listOf(actor)
    )

}