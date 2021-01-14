package com.example.networkretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        binding.buttonRequest.setOnClickListener {
            val githubService = retrofit.create(GithubService::class.java)
            githubService.users().enqueue(object : Callback<List<RepositoryItem>> {
                override fun onResponse(
                    call: Call<List<RepositoryItem>>,
                    response: Response<List<RepositoryItem>>
                ) {
                    adapter.userList.addAll(response.body() as List<RepositoryItem>)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {
                    /* */
                }
            })
        }

        setContentView(binding.root)


    }
}

interface GithubService {
    @GET("users/Kotlin/repos")
    fun users(): Call<List<RepositoryItem>>
}