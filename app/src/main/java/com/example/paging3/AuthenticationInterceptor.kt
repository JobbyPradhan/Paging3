package com.example.paging3

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthenticationInterceptor(private val token:String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token =  token
        Log.d("TADSFASFASFAS", "intercept: $token")
        return try {
            if (token != "null") {
                Log.d("TADSFASFASFAS", "intercept: yes plz")
                val builder = original.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                /*       .addHeader("Accept", "application/json")
                       .addHeader("Content-Type", "application/json")*/
                //    .addHeader("lang",langValue)

                val request = builder.build()
                chain.proceed(request)
            } else {
                Log.d("TADSFASFASFAS", "intercept: no plz")
                chain.proceed(original)
            }
        }catch (e:Exception){
            Log.d("TADSFASFASFAS", "intercept: Let me die")
            e.printStackTrace()
            chain.proceed(original)
        }
    }

}