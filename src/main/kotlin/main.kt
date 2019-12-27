import com.google.gson.JsonObject
import com.sun.net.httpserver.HttpServer
import java.io.PrintWriter
import java.net.InetSocketAddress
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {
    HttpServer.create(InetSocketAddress(80), 0).apply {
        createContext("/") { http ->
            val params = queryToMap(http.requestURI.query)
            http.responseHeaders.add("Content-type", "text/json")
            http.sendResponseHeaders(200, 0)
            val year = params.getValue("year").toInt()
            val response = JsonObject()
            if (year > 0) {
                val date = if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                    LocalDate.of(year, 9, 12).format(DateTimeFormatter.ofPattern("dd/MM/yy"))
                else
                    LocalDate.of(year, 9, 13).format(DateTimeFormatter.ofPattern("dd/MM/yy"))
                response.addProperty("errorCode", 200)
                response.addProperty("dataMessage", date)
            }
            else {
                response.addProperty("errorCode", 1)
                response.addProperty("dataMessage", "Wrong year")
            }
            PrintWriter(http.responseBody).use { out ->
                out.println(response)
            }
        }
        start()
    }
}

fun queryToMap(query: String): Map<String, String> {
    val result = HashMap<String, String>()
    for (param in query.split("&").toTypedArray()) {
        val pair = param.split("=").toTypedArray()
        if (pair.size > 1) {
            result[pair[0]] = pair[1]
        } else {
            result[pair[0]] = ""
        }
    }
    return result
}