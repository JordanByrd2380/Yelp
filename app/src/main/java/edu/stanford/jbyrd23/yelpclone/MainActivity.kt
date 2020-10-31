package edu.stanford.jbyrd23.yelpclone

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "1nTINtQOSHuTIU3W88gNlnVClmI84Jqb3asHWLsk8a4ZREWG0ekzwpgT_Jv8R2hBRBPKSZ1xZ91aM0LzJQ5phUPbLVjrbpF7g5RtSbWME5RxwwjGueOFKm5-eXicX3Yx"
private const val BASE_URL = "https:/api.yelp.com/v3/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantsAdapter(this, restaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York").enqueue(object : Callback<YelpSearchResult> {
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                val body = response.body()
                if(body == null){
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {

            }

        })
    }
}