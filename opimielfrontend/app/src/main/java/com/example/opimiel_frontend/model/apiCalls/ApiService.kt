import com.example.opimiel_frontend.model.apiCalls.AddUserResponse
import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import com.example.opimiel_frontend.model.apiCalls.PostAddUserRequest
import com.example.opimiel_frontend.model.apiCalls.PostGetUser
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{
    @GET("subjects") // endpoint
    fun getSubjects(): Call<SubjectsResponse>

    @Headers("Content-Type: application/json")
    @POST("postSubject")
    fun postSubject(@Body requestBody: PostSubjectRequest): Call<MessageResponse>

    @Headers("Content-Type: application/json")
    @POST("addUser")
    fun addUser(@Body requestBody: PostAddUserRequest): Call<AddUserResponse>

    @Headers("Content-Type: application/json")
    @POST("getAccount")
    fun getUser(@Body requestBody: PostGetUser): Call<AddUserResponse>

    @GET("getFavorites/{id}")
    fun getFavorites(@Path(value = "id",encoded = false) id:String): Call<SubjectsResponse>

    @GET("getOwnSubjects/{id}")
    fun getOwnSubjects(@Path(value="id",encoded = false) id:String) : Call<SubjectsResponse>

    @GET("getResponses/{id}")
    fun getResponses(@Path(value="id",encoded = false)id:String) : Call<PostResResponse>

    @Headers("Content-Type: application/json")
    @POST("addFavorite")
    fun addFavorite(@Body requestBody: PostFavoriteRequest): Call<MessageResponse>

    @Headers("Content-Type: application/json")
    @DELETE("deleteFavorite")
    fun deleteFavorite(@Body requestBody: PostFavoriteRequest): Call<MessageResponse>

    @Headers("Content-Type: application/json")
    @POST("postResponse")
    fun postResponse(@Body requestBody: PostReponseRequest): Call<MessageResponse>


}
