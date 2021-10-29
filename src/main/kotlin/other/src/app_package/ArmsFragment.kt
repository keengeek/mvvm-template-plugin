package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}

import android.view.View

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModelProvider
import com.zeekrlife.base.utils.getVm
import com.zeekrlife.base.utils.inflate
import ${provider.basePackageName.value}.BaseFragment
${
    if (provider.needViewModel.value)
        """
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel
"""
    else
        "import com.zeekrlife.base.viewmodel.StubViewModel"
}

import ${provider.appPackageName.value}.databinding.Fragment${provider.pageName.value}Binding
import ${provider.appPackageName.value}.R

${commonAnnotation(provider)}
${
    if (provider.needViewModel.value)
        """
class ${provider.pageName.value}Fragment : BaseFragment<${provider.pageName.value}ViewModel>() {
"""
    else
        """
class ${provider.pageName.value}Fragment : BaseFragment<StubViewModel>() {
"""
}
    companion object {
    fun newInstance():${provider.pageName.value}Fragment {
        val fragment = ${provider.pageName.value}Fragment()
        return fragment
    }
    const val TAG="${provider.pageName.value}Log"
    }
    
    private val binding: Fragment${provider.pageName.value}Binding by inflate()
    
${
    if (provider.needViewModel.value)
        """
    override fun initViewModel(): ${provider.pageName.value}ViewModel = getVm()
"""
    else
        """
    override fun initViewModel(): StubViewModel = getVm()
"""
}
    override fun getLayout(): View = binding.root
    
    override fun initView(view: View) {
        TODO("Not yet implemented")
        getTitleView()?.apply {
            setVisibility(View.VISIBLE)
            setNavIconVisibility(true)
            //TODO
            setTitleText("")
        }
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.zeekrlife.base.utils.getVm;
import com.zeekrlife.base.utils.inflate;
import ${provider.basePackageName.value}.BaseFragment;
${
    if (provider.needViewModel.value)
        """
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel;
"""
    else
        """
import com.zeekrlife.base.viewmodel.StubViewModel;
        """
}

import ${provider.appPackageName.value}.databinding.Fragment${provider.pageName.value}Binding;
import ${provider.appPackageName.value}.R;


${commonAnnotation(provider)}
${
    if (provider.needViewModel.value)
        """
public class ${provider.pageName.value}Fragment extends BaseFragment<${provider.pageName.value}ViewModel> {
"""
    else
        """
public class ${provider.pageName.value}Fragment extends BaseFragment<StubViewModel> {
        """
}

    
    public static ${provider.pageName.value}Fragment newInstance() {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        return fragment;
    }
    
    private static final String TAG="${provider.pageName.value}Log";
    
    private Fragment${provider.pageName.value}Binding binding;
    
    @Override
    public void initView(@NonNull View view) {

    }

    @Override
    public void initData() {
         
    }

    ${
    if (provider.needViewModel.value)
        """
    @NonNull
    @Override
    public ${provider.pageName.value}ViewModel initViewModel() {
        return new ViewModelProvider(this).get(${provider.pageName.value}ViewModel.class);
    }
"""
    else

        """
    @NonNull
    @Override
    public StubViewModel initViewModel() {
        return new ViewModelProvider(this).get(StubViewModel.class);
    }
        """
}
 
    @Nullable
    @Override
    public View getLayout() {
        binding = Fragment${provider.pageName.value}Binding.inflate(LayoutInflater.from(this));
        return binding.getRoot();
    }

}
    
"""