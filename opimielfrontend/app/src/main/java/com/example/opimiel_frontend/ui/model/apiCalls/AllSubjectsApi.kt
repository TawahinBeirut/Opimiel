import com.example.opimiel_frontend.Subject
import retrofit2.Call
import retrofit2.http.GET

interface AllSubjectsApi{
    @GET("subjects") // endpoint
    fun getSubjects(): Call<SubjectsResponse>
}
