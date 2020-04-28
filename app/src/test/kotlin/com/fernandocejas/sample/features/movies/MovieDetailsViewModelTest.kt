/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.movies

import com.fernandocejas.sample.AndroidTest
import com.fernandocejas.sample.core.functional.Either
import com.nhaarman.mockito_kotlin.any
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MovieDetailsViewModelTest : AndroidTest() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Mock private lateinit var getMovieDetails: GetMovieDetails
    @Mock private lateinit var playMovie: PlayMovie

    @Before
    fun setUp() {
        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetails, playMovie)
    }

    @Test fun `loading movie details should update live data`() =
            coroutinesTestRule.testDispatcher.runBlockingTest {
                val movieDetails = MovieDetails(0, "IronMan", "poster", "summary",
                        "cast", "director", 2018, "trailer")

                When calling getMovieDetails.run(any()) itReturns Either.Right(movieDetails)

                movieDetailsViewModel.movieDetails.observeForever {
                    with(it!!) {
                        id shouldBeEqualTo  0
                        title shouldBeEqualTo "IronMan"
                        poster shouldBeEqualTo "poster"
                        summary shouldBeEqualTo "summary"
                        cast shouldBeEqualTo "cast"
                        director shouldBeEqualTo "director"
                        year shouldBeEqualTo 2018
                        trailer shouldBeEqualTo "trailer"
                    }
                }

                movieDetailsViewModel.loadMovieDetails(0)
            }
}