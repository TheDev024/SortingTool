fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    val newMap = userMap.toMutableMap()
    newMap.putIfAbsent(login, password)
    if (newMap == userMap) {
        println("User with this login is already registered!")
    }
    return newMap
}