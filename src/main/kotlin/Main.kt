package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpMethod
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.copyAndClose
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import java.io.File


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() {
    val path = System.getProperty("user.dir")
    val client = HttpClient(CIO)


    SystemFileSystem.createDirectories(Path("$path/mapTiles"), false) // create new folder for the map pngs

    getMapTile(client, 100, 299) // example to test function

    client.close()
}

suspend fun getMapTile(client: HttpClient, tileX: Int, tileY: Int) {
    val timeAndDate = java.time.LocalDateTime.now().toString()

    val mapTilePNG = File("MapTile-$tileX-$tileY---$timeAndDate")
    val out: ByteReadChannel = client.get {
        url("https://backend.wplace.live/files/s0/tiles/$tileX/$tileY.png")
        method = HttpMethod.Get
        bodyAsChannel(). // Something Sommething as Channels

   out.copyAndClose(mapTilePNG.writeChannel())
}