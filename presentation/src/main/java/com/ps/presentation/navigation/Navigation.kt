package com.ps.presentation.navigation


const val SELECTED_ACTOR_ID = "SELECTED_ACTOR_ID"

enum class Route {
    ACTOR_LIST_SCREEN,
    ACTOR_DETAIL_SCREEN;
    fun getRoute(data: Any? = null): String {
        return when (this) {
            ACTOR_LIST_SCREEN -> ACTOR_LIST_SCREEN.name
            ACTOR_DETAIL_SCREEN -> "${ACTOR_DETAIL_SCREEN.name}/$data"
        }
    }

    fun getRoutePlaceHolder(): String {
        return when (this) {
            ACTOR_LIST_SCREEN -> ACTOR_LIST_SCREEN.name
            ACTOR_DETAIL_SCREEN -> "${ACTOR_DETAIL_SCREEN.name}/{$SELECTED_ACTOR_ID}"
        }
    }
}