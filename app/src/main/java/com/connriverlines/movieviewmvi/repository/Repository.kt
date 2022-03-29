package com.connriverlines.movieviewmvi

import androidx.lifecycle.LiveData

object Repository {

    fun getMovies(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Movie>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Movie>>) {
                result.value = DataState.data(null, response.body)
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Movie>>> {
                return MyRetrofitBuilder.apiService.getMovies()
            }

        }.asLiveData()
    }
}




























