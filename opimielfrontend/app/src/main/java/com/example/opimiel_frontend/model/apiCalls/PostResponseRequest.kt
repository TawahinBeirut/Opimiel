import android.content.ContentValues
import android.widget.Button

data class PostReponseRequest(
    val authorId: String,
    val subjectId: String,
    val value : Boolean,
    val latitude : Float,
    val longitude : Float)
