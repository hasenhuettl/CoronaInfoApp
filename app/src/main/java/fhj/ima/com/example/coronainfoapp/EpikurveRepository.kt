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
                "10")
        )
    }
    fun epikurveList(success:(epikurveList: List<epikurve>) -> Unit, error: (errorMessage: String) -> Unit) {
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