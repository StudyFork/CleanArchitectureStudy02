package yunji.cleanarchitecturestudy02.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/*
 * Created by yunji on 09/03/2020
 */
abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {
    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
    }
}