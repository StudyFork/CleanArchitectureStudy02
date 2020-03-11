package yunji.cleanarchitecturestudy02.listener

/*
 * Created by yunji on 09/03/2020
 */
abstract class OnSingleClickListener<T>(
    private val clickDelayMilliSeconds: Long = CLICK_DELAY_TIME
) : OnItemClickListener<T> {
    private var lastClickTime = 0L

    override fun onClick(item: T) {
        val now = System.currentTimeMillis()
        if ((now - lastClickTime) > clickDelayMilliSeconds) {
            onSingleClick(item)
            lastClickTime = now
        }
    }

    abstract fun onSingleClick(item: T)

    companion object {
        private const val CLICK_DELAY_TIME = 500L

        fun <T> wrap(
            listener: OnItemClickListener<T>,
            clickDelayMilliSeconds: Long = CLICK_DELAY_TIME
        ): OnItemClickListener<T> {
            return object : OnSingleClickListener<T>(clickDelayMilliSeconds) {
                override fun onSingleClick(item: T) {
                    listener.onClick(item)
                }
            }
        }
    }
}