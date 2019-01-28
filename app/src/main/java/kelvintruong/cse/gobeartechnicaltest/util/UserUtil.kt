package kelvintruong.cse.gobeartechnicaltest.util

object UserUtil {
    fun valid(userName: String, pwd: String): Boolean {
        return userName.equals("GoBear", false) && pwd.equals("GoBearDemo", false)
    }
}