package com.connriverlines.movieviewmvi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainRecyclerAdapter.Interaction
{
    lateinit var viewModel: MainViewModel
    lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // use RxView and prevent another click for 3 seconds
        RxView.clicks(btnLoad)
            .throttleFirst(3, TimeUnit.SECONDS)
            .subscribe {

                // on a click send the "get movies" intent/event
                triggerGetMoviesEvent()
            }

        // create the viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // setup the recycler - layout/adapter
        initRecyclerView()

        //setup observers for the states
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        mainRecyclerAdapter = MainRecyclerAdapter(this, this)
        recycler_view.adapter = mainRecyclerAdapter
    }

    private fun triggerGetMoviesEvent() {
        viewModel.setStateEvent(MainStateEvent.GetMoviesEvent())
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this) { dataState ->

            // we got a new DataState - do required updates

            // Loading - show or hide the spinner
            val utils = Utils()
            utils.showProgressBar(progress_bar, dataState.loading)

            // got a message - toast it
            if (dataState.message != null) {
                utils.showToast(this, dataState.message.toString())
            }

            // got movie data - set in vm
            if (dataState.data != null) {
                viewModel.setMovieData(dataState.data!!)
            }
        }

        viewModel.viewState.observe(this) { viewState ->
            // the viewState changed - send to the list
            viewState.movies?.let { movies ->
                mainRecyclerAdapter.submitList(movies)
            }
        }
    }

    override fun onItemSelected(position: Int, movie: Movie) {
        launchDetails(movie)
    }

    // just launch the details activity with the movie data
    private fun launchDetails(movie: Movie?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", movie?.name)
        intent.putExtra("category", movie?.category)
        intent.putExtra("desc", movie?.desc)
        intent.putExtra("imgUrl", movie?.imageUrl)
        startActivity(intent)
    }
}