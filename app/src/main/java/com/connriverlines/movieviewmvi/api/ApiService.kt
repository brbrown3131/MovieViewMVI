package com.connriverlines.movieviewmvi

import androidx.lifecycle.LiveData
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    fun getMovies(): LiveData<GenericApiResponse<List<Movie>>>
}

