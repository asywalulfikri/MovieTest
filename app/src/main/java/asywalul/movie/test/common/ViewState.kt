package asywalul.movie.test.common


data class ViewState<T>(val data: T?, val err: Throwable?, val currentState: State) {
    companion object {
        fun <T> success(data: T?): ViewState<T> = ViewState(data, null, State.SUCCESS)

        fun <T> error(err: Throwable?): ViewState<T> = ViewState(null, err, State.FAILED)

        fun <T> loading(): ViewState<T> = ViewState(null, null, State.LOADING)
    }

    enum class State(value: Int) {
        LOADING(0), SUCCESS(1), FAILED(-1);

        var value: Int? = value
    }


}