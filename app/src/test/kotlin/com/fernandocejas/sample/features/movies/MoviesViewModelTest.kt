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
import com.fernandocejas.sample.core.functional.Either.Right
import com.nhaarman.mockito_kotlin.any
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.itReturns
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: MoviesViewModel

    @Mock private lateinit var getMovies: GetMovies

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel(getMovies)
    }

    @Test fun `loading movies should update live data`() =
            coroutinesTestRule.testDispatcher.runBlockingTest {
                val moviesList = listOf(Movie(0, "IronMan"), Movie(1, "Batman"))

                When calling getMovies.run(any()) itReturns Right(moviesList)

                moviesViewModel.movies.observeForever {
                    it!!.size shouldBeEqualTo 2
                    it[0].id shouldBeEqualTo 0
                    it[0].poster shouldBeEqualTo "IronMan"
                    it[1].id shouldBeEqualTo 1
                    it[1].poster shouldBeEqualTo "Batman"
                }

                moviesViewModel.loadMovies()
            }
}