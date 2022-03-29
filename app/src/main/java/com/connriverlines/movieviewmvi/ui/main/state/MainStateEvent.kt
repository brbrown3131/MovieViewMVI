package com.connriverlines.movieviewmvi

sealed class MainStateEvent {
    // only one event for now - get the movies
    class GetMoviesEvent: MainStateEvent()
}