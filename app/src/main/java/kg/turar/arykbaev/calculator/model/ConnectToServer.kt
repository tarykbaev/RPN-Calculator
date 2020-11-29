package kg.turar.arykbaev.calculator.model

import java.io.BufferedOutputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ConnectToServer(private val message: String) : Thread() {
    fun go() {
        start()
    }

    override fun run() {
        send(message)
    }

    private fun send(message: String?) {
        try {
            val echoSocket = Socket("localhost", 8080)
            val outputStream =
                ObjectOutputStream(BufferedOutputStream(echoSocket.getOutputStream()))

            outputStream.writeChars(message)
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}