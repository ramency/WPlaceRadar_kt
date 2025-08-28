package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() {
    val client = HttpClient(CIO)

    getMapTile(client, 100, 299)

    client.close()
}

suspend fun getMapTile(client: HttpClient, tileX: Int, tileY: Int) {
    val response: HttpResponse = client.get("https://backend.wplace.live/files/s0/tiles/$tileX/$tileY.png")
}