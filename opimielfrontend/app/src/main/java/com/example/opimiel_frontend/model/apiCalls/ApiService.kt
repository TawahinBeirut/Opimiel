import com.example.opimiel_frontend.model.apiCalls.MessageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{
    @GET("subjects") // endpoint
    fun getSubjects(): Call<SubjectsResponse>

    @POST("postSubject")
    fun postSubject(authorId:String,name:String) : Response<MessageResponse>
}
