//package com.example.rocketjournal
//
//
//import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
//import com.example.rocketjournal.model.Repositories.RepeImplementation.ListsRepositoryImpl
//import com.example.rocketjournal.model.dataModel.ListData
//import io.github.jan.supabase.postgrest.Postgrest
//import io.github.jan.supabase.storage.Storage
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertTrue
//import org.junit.Before
//import org.junit.Test
//import org.mockito.ArgumentMatchers.any
//import org.mockito.ArgumentMatchers.anyString
//import org.mockito.Mock
//import org.mockito.Mockito.doNothing
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.`when`
//import org.mockito.MockitoAnnotations
//
//@ExperimentalCoroutinesApi
//class ListsRepositoryImplTest {
//
//    @Mock
//    private lateinit var postgrest: Postgrest
//    private lateinit var storage: Storage
//    private lateinit var listsRepository: ListsRepositoryImpl
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        storage = mock(Storage::class.java)
//        postgrest = mock(Postgrest::class.java)
//        listsRepository = ListsRepositoryImpl(postgrest, storage)
//    }
//
//    @Test
//    fun `createList returns true on success`() = runBlockingTest {
//        val listData = ListData(user_id = 1, name = "Test List", is_complete = false)
//        // Mock the successful operation, assuming the insert function doesn't return a value you need to capture
//        // Adjust this mock to match your actual operation with Postgrest
//        doNothing().`when`(postgrest).from(anyString()).insert(any(ListsDTO::class.java))
//
//        val result = listsRepository.createList(listData)
//
//        assertTrue(result)
//    }
//
//    @Test
//    fun `getLists returns list of ListsDTO`() = runBlocking {
//        val expectedLists = listOf(
//            ListsDTO(list_id = 1, user_id = 1, name = "Test List 1", is_complete = false),
//            ListsDTO(list_id = 2, user_id = 1, name = "Test List 2", is_complete = true)
//        )
//
//        // Assume your repository's getLists() internally calls a method to convert Postgrest's response
//        // to a List<ListsDTO> and handles errors. We mock this behavior to return expectedLists.
//        `when`(listsRepository.getLists()).thenReturn(expectedLists)
//
//        val result = listsRepository.getLists()
//
//        assertEquals(expectedLists, result)
//    }
//}