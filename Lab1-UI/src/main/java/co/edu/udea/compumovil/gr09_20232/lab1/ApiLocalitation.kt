package co.edu.udea.compumovil.gr09_20232.lab1

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class CountryData(
    @SerializedName("country_name") val countryName: String,
    @SerializedName("cities_name") val citiesName: List<String>,
)

data class StatesData(
    @SerializedName("state_name") val stateName: String,
)

data class CitiesData(
    @SerializedName("city_name") val cityName: String,
)

interface LocalitationApi {

    @GET("countries")
    suspend fun getAllCountries(): List<CountryData>

    @GET("states")
    suspend fun getAllStates(): List<StatesData>

    @GET("states/{countryName}")
    suspend fun getStateByCountry(@Path("countryName") countryName: String): List<StatesData>

    @GET("cities")
    suspend fun getAllCities(): List<CitiesData>

    @GET("cities/{stateName}")
    suspend fun getCitiesByName(@Path("stateName") citiesName: String): List<CitiesData>


}


object LocalitationApiService {
    private const val BASE_URL = "https://private-63af11-lauravanessatascon.apiary-mock.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val localitationApi: LocalitationApi = retrofit.create(LocalitationApi::class.java)
}
