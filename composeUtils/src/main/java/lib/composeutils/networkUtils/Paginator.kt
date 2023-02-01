package lib.composeutils.networkUtils

import androidx.annotation.Keep
import lib.composeutils.ui.UiText


interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}

@Keep
data class PaginationScreenState<T>(
    val isLoading: Boolean = false,
    var items: MutableList<T> = mutableListOf(),
    val error: UiText? = null,
    val endReached: Boolean = false,
    val page: Int = 0,
    val isInitialLoad: Boolean = true,
    val isEmpty: Boolean = false,
    val isFilterApplied: Boolean = false
)

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> ApiResult<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (UiText?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false


    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        result.apply {
            ifSuccess { items ->
                currentKey = getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
            ifFailed { code, message ->
                onError(UiText.DynamicString(message))
                onLoadUpdated(false)

            }
        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}