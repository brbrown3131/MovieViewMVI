package com.connriverlines.movieviewmvi

//https://howtodoandroid.com/movielist.json

//        "category": "Latest",
//        "imageUrl": "https://howtodoandroid.com/images/coco.jpg",
//        "name": "Coco",
//        "desc": "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"

data class Movie(
    val name: String,
    val desc: String,
    val imageUrl: String,
    val category: String
)

