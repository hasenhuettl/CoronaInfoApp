package fhj.ima.com.example.coronainfoapp

import android.database.Observable
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

object ZahlenApi {
    val retrofit: Retrofit
    val retrofitService: ZahlenApiService
    init{
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://raw.githubusercontent.com/")
            .build()
        retrofitService = retrofit.create(ZahlenApiService::class.java)
    }
}
interface ZahlenApiService {
    @GET("/Daniel-Breuss/covid-data-austria/master/austriadata.json")
    fun epikurveList(): Call<List<epikurve>>
}