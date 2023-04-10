package com.vitorthemyth.composecrashcourse.observe_connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver  {

    fun observe() : Flow<Status>

    enum class Status{
        Available, Unavailable,Losing, Lost
    }
}