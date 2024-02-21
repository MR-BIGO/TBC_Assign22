package com.example.tbc_assign22

import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.model.Place
import com.example.tbc_assign22.domain.use_case.GetPlacesUseCase
import com.example.tbc_assign22.domain.use_case.GetPostsUseCase
import com.example.tbc_assign22.presentation.event.HomeFragmentEvents
import com.example.tbc_assign22.presentation.model.OwnerPresentation
import com.example.tbc_assign22.presentation.model.PlacePresentation
import com.example.tbc_assign22.presentation.model.PostPresentation
import com.example.tbc_assign22.presentation.screen.home.HomeFragmentViewModel
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeFragmentViewModelTest {

    @Mock
    private lateinit var getPlacesUseCase: GetPlacesUseCase

    @Mock
    private lateinit var getPostsUseCase: GetPostsUseCase

    private lateinit var viewModel: HomeFragmentViewModel

    @Before
    fun before() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = HomeFragmentViewModel(getPlacesUseCase, getPostsUseCase)
    }

    //check if places use case emits success
    @Test
    fun `getPlaces can emit success state`() = runTest {
        val placesPres =
            listOf(PlacePresentation(1, "das", "tiutle"), PlacePresentation(2, "fdsf", "tutel"))
        val placesDom = listOf(Place(1, "das", "tiutle"), Place(2, "fdsf", "tutel"))

        whenever(getPlacesUseCase()).thenReturn(flowOf(Resource.Success(placesDom)))

        viewModel.onEvent(HomeFragmentEvents.GetPlaces)

        val collectedState = viewModel.homeState.take(2).toList()

        assertTrue("True if Success gets emmited", collectedState.any { it.places == placesPres })
    }

    //check if places use case emits error
    @Test
    fun `getPlaces can emit error state`() = runTest {
        val error = "Some Error"
        whenever(getPlacesUseCase()).thenReturn(flowOf(Resource.Error(error)))

        viewModel.onEvent(HomeFragmentEvents.GetPlaces)

        val collectedState = viewModel.homeState.take(2).toList()

        assertTrue("We need a state with error message", collectedState.any { it.error == error })
    }

    //check if navigation event works properly
    @Test
    fun `pressing on post item results in passing correct id to details fragment`() = runTest {
        val post = PostPresentation(
            22,
            listOf(),
            "title",
            14,
            12,
            "content",
            OwnerPresentation("bla", "blabla", "", 43)
        )

        viewModel.onEvent(HomeFragmentEvents.PostPressed(post.id))
        val result = viewModel.uiEvent.first()
        assertTrue(
            "True if navigation event sends the same id as post clicked has",
            HomeFragmentViewModel.HomeNavigationEvents.NavigateToDetails(post.id) == result
        )
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }
}