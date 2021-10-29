package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.app.Activity
import android.os.Bundle
import android.view.View
import ${provider.basePackageName.value}.BaseActivity
import com.zeekrlife.base.utils.getVm
import com.zeekrlife.base.utils.inflate

${
    if (provider.needViewModel.value)
        """
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel
"""
    else
        "import com.zeekrlife.base.viewmodel.StubViewModel"
}
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;

import ${provider.appPackageName.value}.R


${commonAnnotation(provider)}
${
    if (provider.needViewModel.value)
        """
class ${provider.pageName.value}Activity : BaseActivity<${provider.pageName.value}ViewModel>() {
"""
    else
        """
class ${provider.pageName.value}Activity : BaseActivity<StubViewModel>() {
"""
}

    val binding: Activity${provider.pageName.value}Binding by inflate()
    
    override fun getLayout(): View = binding.root

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

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};

import android.view.View;
import ${provider.basePackageName.value}.BaseActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
${
    if (provider.needViewModel.value)
        """
import ${provider.viewModelPackageName.value}.${provider.pageName.value}ViewModel;
"""
    else
        "import com.zeekrlife.base.viewmodel.StubViewModel;"
}
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;

import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}

${
    if (provider.needViewModel.value)
        """
public class ${provider.pageName.value}Activity extends BaseActivity<${provider.pageName.value}ViewModel>  {
"""
    else
        "public class ${provider.pageName.value}Activity extends BaseActivity<StubViewModel>  {"
}

 private Activity${provider.pageName.value}Binding binding;
 
    @Override
    public void initView() {
    //TODO
    }
    
    @Nullable
    @Override
    public View getLayout() {
        binding = Activity${provider.pageName.value}Binding.inflate(LayoutInflater.from(this));
        return binding.getRoot();
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

    @Override
    public void initData() {

    }

    

}
"""