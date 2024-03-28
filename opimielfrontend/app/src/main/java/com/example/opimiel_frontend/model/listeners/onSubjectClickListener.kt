import com.example.opimiel_frontend.Subject

interface OnSubjectClickListener {
    fun onSubjectClick(subject: Subject);
    fun getUserId(): String;
    fun getFragmentName(): String;
    fun addFavorite(subject: Subject);
    fun deleteFavorite(subject: Subject);
}