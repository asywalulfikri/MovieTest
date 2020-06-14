package asywalul.movie.test.utils

class Constant {


    interface API{
        companion object {
            const val HOST = "https://api.themoviedb.org/3/"
            const val HOST_IMAGE = "https://image.tmdb.org/t/p/w500/"
        }
    }

    interface TABLE{
        companion object{
            const val tbl_popular = "popular"
            const val tbl_upcoming = "upcoming"
            const val tbl_review = "reviews"
            const val tbl_detail = "detail"
            const val tbl_genre ="genre"
            const val tbl_genres = "genres"
        }
    }

    interface KEY{
        companion object{
            const val key ="a443169a3a91f8c3a0959954f1203c5b"
        }
    }
}