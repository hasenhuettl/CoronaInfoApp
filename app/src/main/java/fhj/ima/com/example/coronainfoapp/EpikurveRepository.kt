package fhj.ima.com.example.coronainfoapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success

object EpikurveRepository {
    private val epikurven: List<epikurve>

    init{
        epikurven = listOf(
        epikurve(
            "2021-01-05",
            71657,
        2877,
        6457,
        100,
        6454,
         51,
         334711,
         1218,
         2033,
        371,
         630,
        1001,
        3808540,
         15450,
         9741,
        173,
        8846,
        39,
         5,
        116831,
         22656,
        509,
        19438,
         123,
         13,
         175979,
        55890,
         915,
        50143,
         397,
         49,
         724823,
        74271,
         1181,
        67939,
         438,
         68,
         476011,
         30016,
        357,
         26596,
         125,
         21,
        221884,
         43834,
         1406,
         39143,
         333,
         62,
         401663,
         41329,
        528,
         38910,
         122,
         33,
        464568,
         19839,
        226,
         18581,
         58,
            11,
        243080,
        74081,
         1159,
         65115,
         398,
         109,
         983701)
        )
    }
    fun epikurveList() {
        ZahlenApi.retrofitService.epikurveList().enqueue(object: Callback<List<epikurve>> {
            override fun onFailure(call: Call<List<epikurve>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<epikurve>>, response: Response<List<epikurve>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }
}