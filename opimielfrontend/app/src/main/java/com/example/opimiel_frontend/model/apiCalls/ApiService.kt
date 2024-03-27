import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService{
    @GET("subjects") // endpoint
    fun getSubjects(): Call<SubjectsResponse>

    @Headers("Content-Type: application/json")
    @POST("postSubject")
    fun postSubject(@Body requestBody: PostSubjectRequest): Call<MessageResponse>
}
