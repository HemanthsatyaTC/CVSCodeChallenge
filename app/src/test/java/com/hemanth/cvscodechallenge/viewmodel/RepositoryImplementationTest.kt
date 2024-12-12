package com.hemanth.cvscodechallenge.viewmodel

import com.hemanth.cvscodechallenge.data.model.ImageItemModel
import com.hemanth.cvscodechallenge.data.model.ItemModel
import com.hemanth.cvscodechallenge.data.model.MediaModel
import com.hemanth.cvscodechallenge.data.remote.ImageDetailsInterface
import com.hemanth.cvscodechallenge.data.repository.RepositoryImplementation
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RepositoryImplementationTest{
    private lateinit var repository: RepositoryImplementation
    private val imageDetailsInterface: ImageDetailsInterface = mockk()

    @Before
    fun setup() {
        repository = RepositoryImplementation(imageDetailsInterface)
    }

    @Test
    fun `getDetails returns data from ImageDetailsInterface`(): Unit = runTest {
        val mockResponse = ImageItemModel(
            description = "Sample Description",
            generator = "Sample Generator",
            items = listOf(
                ItemModel(
                    title = "Sample Image",
                    author = "Sample Author",
                    media = MediaModel(m = "http://sample.url/image.jpg"),
                    published = "2024-12-11"
                )
            )
        )

        coEvery { imageDetailsInterface.getDetails(tags = "porcupine") } returns mockResponse

        // Call getDetails on the repository
        val result = repository.getDetails("porcupine")

        assertEquals("Sample Description", result.description)
        assertEquals("Sample Generator", result.generator)
        assertEquals(1, result.items?.size)
        assertEquals("Sample Image", result.items?.first()?.title)
        assertEquals("Sample Author", result.items?.first()?.author)
        assertEquals("http://sample.url/image.jpg", result.items?.first()?.media?.m)

        coVerify { imageDetailsInterface.getDetails(tags = "porcupine") }
    }

    @Test
    fun `getDetails handles empty data gracefully`() = runTest {
        val emptyResponse = ImageItemModel(
            description = "Empty Description",
            generator = "Empty Generator",
            items = emptyList()
        )

        coEvery { imageDetailsInterface.getDetails(tags = "unknown") } returns emptyResponse

        val result = repository.getDetails("unknown")

        assertEquals("Empty Description", result.description)
        assertEquals("Empty Generator", result.generator)
        assertEquals(0, result.items?.size)

        coVerify { imageDetailsInterface.getDetails(tags = "unknown") }
    }

    @Test(expected = RuntimeException::class)
    fun `getDetails throws exception for invalid data`() = runTest {
        coEvery { imageDetailsInterface.getDetails(tags = "invalid") } throws RuntimeException("Network Error")

        repository.getDetails("invalid")

        coVerify { imageDetailsInterface.getDetails(tags = "invalid") }
    }
}