package com.valentinerutto.myvideoplayer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.valentinerutto.myvideoplayer.data.Video
import com.valentinerutto.myvideoplayer.data.VideoRepository
import com.valentinerutto.myvideoplayer.data.network.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class VideoRepositoryTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    private lateinit var apiHelper: ApiService

    @Mock
    private lateinit var repository: VideoRepository

    @Mock
    private lateinit var apiVideoObserver: Observer<LiveData<Video>>

    fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

    private val testDispatcher = TestCoroutineDispatcher()
}