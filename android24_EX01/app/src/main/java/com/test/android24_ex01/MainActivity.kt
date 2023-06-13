package com.test.android24_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.test.android24_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val movieList = mutableListOf<Movie>()

        activityMainBinding.run {
            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }
            etMovieName.requestFocus()

            seekBarPrice.run {
                setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
                    override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                        tvPrice.text = "${progress * 100 + 5000} 원"
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                })
            }

            ratingBar.run {
                setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    tvRating.text = "$rating / 5.0"
                }
            }

            etDirector.run {
                setOnEditorActionListener { textView, i, keyEvent ->


                    val movieName = etMovieName.text.toString()
                    val price = seekBarPrice.progress
                    var grade = ""
                    when(chipGroup.checkedChipId){
                        R.id.chip_allUsers -> {
                             grade = "전체"
                        }
                        R.id.chip_12 -> {
                             grade = "12세"
                        }
                        R.id.chip_15 -> {
                             grade = "15세"
                        }
                        R.id.chip_adult -> {
                             grade = "성인"
                        }
                    }
                    val rating = ratingBar.rating
                    val director = etDirector.text.toString()

                    val movie = Movie(movieName,price,grade,rating, director)
                    movieList.add(movie)

                    etMovieName.setText("")
                    etDirector.setText("")
                    seekBarPrice.progress = 5000
                    ratingBar.rating = 2.5F

                    etMovieName.requestFocus()
                }
            }

            button.run {
                setOnClickListener {
                    textView.text = ""
                    for(movie in movieList){
                        movie.run {
                            textView.append("영화 제목 : $name \n")
                            textView.append("영화 가격 : $price 원\n")
                            textView.append("관람등급 : $grade \n")
                            textView.append("영화 별점 : $rating \n")
                            textView.append("영화 감독 : $director \n")
                            textView.append("----------------------------\n")
                        }
                    }
                }
            }
        }

    }
}

data class Movie(var name:String, var price:Int, var grade:String, var rating:Float, var director:String )