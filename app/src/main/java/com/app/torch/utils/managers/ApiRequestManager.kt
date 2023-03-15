package com.app.torch.utils.managers

import android.content.res.Resources
import android.util.Log
import com.app.torch.R
import com.app.torch.models.response.Message
import com.app.torch.utils.ResponseResult
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Headers
import retrofit2.Response
import java.net.HttpURLConnection


interface ApiRequestManagerInterface {
    fun <T : Any> execute(
        request: suspend () -> Response<T>,
        onSuccess: ((T, Headers) -> Unit)? = null,
        onFailure: ((Message) -> Unit)? = null,
        onUnAuthorized: ((Message) -> Unit)?,
        finally: (() -> Unit)? = null
    ): Job
}

class ApiRequestManager(private val resources: Resources) : ApiRequestManagerInterface {

    override fun <T : Any> execute(
        request: suspend () -> Response<T>,
        onSuccess: ((T, Headers) -> Unit)?,
        onFailure: ((Message) -> Unit)?,
        onUnAuthorized: ((Message) -> Unit)?,
        finally: (() -> Unit)?
    ): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = request.invoke()
                val result = verifyResponse(response)

                withContext(Dispatchers.Main) {
                    when (result) {
                        is ResponseResult.Success -> onSuccess?.invoke(
                            result.data,
                            response.headers()
                        )
                        is ResponseResult.Failure -> onFailure?.invoke(result.message)
                        is ResponseResult.UnAuthorized -> onUnAuthorized?.invoke(result.message)
                    }
                }
            } catch (e: Exception) {
                Log.e("OkHttp", e.message + "")
                withContext(Dispatchers.Main) {
                    onFailure?.invoke(Message(resources.getString(R.string.general_error)))
                }
            } finally {
                withContext(Dispatchers.Main) {
                    finally?.invoke()
                }
            }
        }
    }

    private fun <T : Any> verifyResponse(response: Response<T>): ResponseResult<T> {
        return try {
            val isResponseSuccessful = response.isSuccessful
            val statusCode = response.code()
            val responseBody = response.body()
            val rawMessage = response.raw().message()
            val errorBodyMessage = response.errorBody()?.string()

            if (isResponseSuccessful) {
                if (response.code() == 204) {
                    @Suppress("UNCHECKED_CAST")
                    (ResponseResult.Success(rawMessage as T))
                } else {
                    ResponseResult.Success(responseBody!!)
                }
            }  else if(response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                ResponseResult.UnAuthorized(Message("UnAuthorized"))
            } else {
                val message = Gson().fromJson(errorBodyMessage, Message::class.java)
                message.statusCode = statusCode
                ResponseResult.Failure(message)
            }
        } catch (ex: Exception) {
            ResponseResult.Failure(Message(ex.localizedMessage))
        }
    }
}