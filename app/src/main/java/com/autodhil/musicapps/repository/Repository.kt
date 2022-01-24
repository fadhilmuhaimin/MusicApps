package com.autodhil.musicapps.repository

import android.content.ContentValues.TAG
import android.content.Context
import java.io.IOException
import android.util.Log
import com.autodhil.musicapps.models.Album
import com.autodhil.musicapps.models.Song
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Repository {
    fun getDataTopChartsFromAssets(contex : Context?): List<Song>? {
        val json : String?
        return try{
            val inputstream = contex?.assets?.open("json/topCharts.json")
            json  = inputstream?.bufferedReader().use { it?.readText() }
            Log.d("Repository","getDataTopChartsFromAssets : $json")
            val  grouplisType = object : TypeToken<List<Song>>(){}.type
            Gson().fromJson(json,grouplisType)


        }catch (e : IOException){
            e.printStackTrace()
            Log.e("Repository","error_getDataTopChartsFromAssets : ${e.message}")
            null
        }
    }

    fun getDataTopAlbumsFromAssets(contex : Context?): List<Album>? {
        val json : String?
        return try{
            val inputstream = contex?.assets?.open("json/topalbums.json")
            json  = inputstream?.bufferedReader().use { it?.readText() }
            Log.d("Repository","getDataTopChartsFromAssets : $json")
            val  grouplisType = object : TypeToken<List<Album>>(){}.type
            Gson().fromJson(json,grouplisType)


        }catch (e : IOException){
            e.printStackTrace()
            Log.e("Repository","error_getDataTopChartsFromAssets : ${e.message}")
            null
        }
    }

    fun addDataToTopCharts(){
        val databaseTopCharts = FirebaseDatabase.getInstance().getReference("top_charts")
        val songs = mutableListOf<Song?>()

        //get data from firebase storage
        FirebaseStorage
            .getInstance("gs://musicapps-88164.appspot.com")
            .reference
            .child("musics")
            .listAll()
            .addOnSuccessListener { listResult ->
                listResult.items.forEach { item ->
                    item.downloadUrl
                        .addOnSuccessListener { uri ->
                            val names = item.name.split("_")
                            val artistName = names[0].trim()
                            val albumName = names[1].trim()
                            val songName = names[2].trim()
                            val yearAlbum = names[3].trim().replace(".mp3","")

                            val keySong = databaseTopCharts.push().key
                            songs.add(Song(
                                keySong = keySong,
                                nameSong = songName,
                                uriSong = uri.toString(),
                                artistSong = artistName,
                                albumNameSong = albumName,
                                yearSong = yearAlbum.toInt()

                            ))
                            databaseTopCharts.setValue(songs)

                        }
                }

            }
            .addOnFailureListener{ e ->
                Log.e(TAG, "addDataToTopCharts:  ${e.message} ")

            }

    }
}