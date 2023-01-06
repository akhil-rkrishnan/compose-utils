package lib.composeutils.networkUtils

import retrofit2.HttpException

/**
* Method to execute the Network call.
 * @param block lambada defined for the user api call block
 * @return ApiResult<Type> The response body / exception
*/
suspend fun <M> initApiCall(block: suspend () -> ApiResult<M>): ApiResult<M> =
    try {
        block()
    } catch (ex: Exception) {
        ApiResult.Failed(ex)
    }

private suspend fun <T> initApiCall(
    onSuccess: (data: T) -> Unit,
    onFailed: (code: ResponseCode?, message: String?) -> Unit,
    block: suspend () -> ApiResult<T>
) {
    try {
        val result = block()
        if (result is ApiResult.Success) {
            onSuccess(result.data)
        }
    } catch (e: Exception) {
        val cause = e.getCause()
        onFailed(cause, e.message)
    }
}

sealed class ApiResult<out Model> {
    object isLoading : ApiResult<Nothing>()
    data class Success<out Model>(val data: Model) : ApiResult<Model>()
    data class Failed(val exception: Exception) : ApiResult<Nothing>()
}

inline fun <reified Model> ApiResult<Model>.ifSuccess(block: (data: Model) -> Unit) {
    if (this is ApiResult.Success) {
        block(data)
    }
}

inline fun <reified Model> ApiResult<Model>.ifFailed(cause: (code: ResponseCode?, message: String?) -> Unit) {
    if (this is ApiResult.Failed) {
        if (exception is HttpException) {
            cause(responseCodeMap[exception.response()?.code()], exception.message)
        } else {
            cause(ResponseCode.Unknown, exception.message)
        }
    }
}

fun Exception.getCause(): ResponseCode? {
    return if (this is HttpException) {
        responseCodeMap[response()?.code()]
    } else {
        responseCodeMap[null]
    }
}

inline fun <reified Model> ApiResult<Model>.ifLoading(loading: () -> Unit) {
    if (this is ApiResult.isLoading) {
        loading()
    }
}

val responseCodeMap = hashMapOf<Int?, ResponseCode>().apply {
    put(200, ResponseCode.ResponseOk)
    put(201, ResponseCode.ResponseCreated)
    put(202, ResponseCode.ResponseAccepted)
    put(400, ResponseCode.BadRequest)
    put(401, ResponseCode.Unauthorized)
    put(402, ResponseCode.PaymentRequired)
    put(403, ResponseCode.Forbidden)
    put(404, ResponseCode.NotFound)
    put(500, ResponseCode.ServerNotFound)
    put(null, ResponseCode.Unknown)
}

sealed class ResponseCode {
    object ResponseOk : ResponseCode() //200
    object ResponseCreated : ResponseCode() //201
    object ResponseAccepted : ResponseCode() //202
    object ServerNotFound : ResponseCode() //500
    object BadRequest : ResponseCode() //400
    object Unauthorized : ResponseCode() //401
    object PaymentRequired : ResponseCode() //402
    object Forbidden : ResponseCode() //403
    object NotFound : ResponseCode() //404

    object Unknown : ResponseCode()
}
