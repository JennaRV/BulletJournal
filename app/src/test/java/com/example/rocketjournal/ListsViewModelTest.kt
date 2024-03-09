package com.example.rocketjournal


import com.example.rocketjournal.model.Repositories.ListsRepository
import com.example.rocketjournal.model.dataModel.ListData
import com.example.rocketjournal.viewmodel.ListsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ListsViewModelTest {

    private lateinit var viewModel: ListsViewModel
    private lateinit var listsRepository: ListsRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        listsRepository = Mockito.mock(ListsRepository::class.java)
        viewModel = ListsViewModel(listsRepository)
    }

    @Test
    fun `fetch lists successfully`() = runBlockingTest {
        val dummyLists = listOf(
            ListData(1,1, "Shopping", false),
            ListData(2, 1,"Work", true)
        )
      //  `when`(listsRepository.getLists()).thenReturn(flow { emit(dummyLists) })

        viewModel.getLists()

        val listsState = mutableListOf<List<ListData>?>()
        viewModel.listFlow.collect { lists ->
            listsState.add(lists)
        }

        assertEquals(dummyLists, listsState[0])
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
