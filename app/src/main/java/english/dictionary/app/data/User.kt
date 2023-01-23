package english.dictionary.app.data

data class User(
    val firstName: String,
    val lastName: String,
    val image: String,
    val university: String,
    val education: String,
    val summary: String,
){
    fun fullName() = "$firstName $lastName"
}
