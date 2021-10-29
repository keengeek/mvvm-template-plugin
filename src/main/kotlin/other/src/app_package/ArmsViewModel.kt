package other.src.app_package

import other.ArmsPluginTemplateProviderImpl

fun armsViewModel(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsViewModelKt(provider) else armsViewModelJava(provider)

private fun armsViewModelKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.viewModelPackageName.value}
import com.zeekrlife.base.viewmodel.BaseViewModel

class ${provider.pageName.value}ViewModel : BaseViewModel() {


}
"""

private fun armsViewModelJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.viewModelPackageName.value};
import com.zeekrlife.base.viewmodel.BaseViewModel;

public class ${provider.pageName.value}ViewModel extends BaseViewModel {


}
"""
























