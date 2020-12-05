package com.example.desafioandroidapis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic
import com.example.desafioandroidapis.services.comicService
import java.lang.Exception


class ComicDisplayActivity : AppCompatActivity() {

    private val comicDisplayViewModel by viewModels<ComicDisplayViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicDisplayViewModel(comicService) as T
            }
        }
    }

    private val comicDisplayAdapter = ComicDisplayAdapter(this, ::callComicDetails)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_display)

        val self = this

        findViewById<RecyclerView>(R.id.rvComics).apply {
            val gridLayoutManager = GridLayoutManager(self, 3)
            adapter = comicDisplayAdapter
            layoutManager = gridLayoutManager
            setHasFixedSize(false)
            setUpScroller(this, gridLayoutManager)
        }

        comicDisplayViewModel.listComics.observe(self) {
            comicDisplayAdapter.setComicList(it)
        }

        try {
            comicDisplayViewModel.populateComicList()
        } catch (e: Exception) {
            Toast.makeText(self, resources.getString(R.string.error_loading_comics), Toast.LENGTH_LONG).show()
        }
    }

    private fun callComicDetails(comic: Comic) {
        val intent = Intent(this, ComicDetailsActivity::class.java)
        intent.putExtra("comic", comic)
        startActivity(intent)
    }

    private fun setUpScroller(recyclerView: RecyclerView, gridLayoutManager: GridLayoutManager) {
        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy <= 0) return

                    val lItem = gridLayoutManager.itemCount
                    val vItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
                    val itens = comicDisplayAdapter.itemCount

                    if (lItem + vItem < itens) return

                    Log.i("MainActivity", "Hora de carregar mais dados")
                }
            }
        )
    }
}