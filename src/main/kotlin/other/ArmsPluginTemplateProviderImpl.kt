package other

import com.android.tools.idea.wizard.template.*
import other.armsRecipe
import java.io.File


/**
 * Created on 2021/10/22 16:53
 * @author Sebastian
 * module name is ArmsPluginTemplateProviderImpl
 */
class ArmsPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(armsTemplate)

    private val MIN_API = 16

    private val armsTemplate: Template
        get() = template {
            //revision = 2
            name = "MVVM 全家桶 插件版"
            description = "一键创建 MVVM 单个页面所需要的全部组件"
            minApi = MIN_API
            //minBuildApi = MIN_API
            category = Category.Activity
            formFactor = FormFactor.Mobile
            screens = listOf(
                WizardUiContext.ActivityGallery,
                WizardUiContext.MenuEntry,
                WizardUiContext.NewProject,
                WizardUiContext.NewModule
            )
            thumb { File("template_blank_activity.png") }

            widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(appPackageName),
                CheckBoxWidget(needActivity),
                TextFieldWidget(activityLayoutName),
                CheckBoxWidget(generateActivityLayout),
                TextFieldWidget(activityPackageName),
                CheckBoxWidget(needFragment),
                TextFieldWidget(fragmentLayoutName),
                CheckBoxWidget(generateFragmentLayout),
                TextFieldWidget(fragmentPackageName),
                CheckBoxWidget(needViewModel),
                TextFieldWidget(viewModelPackageName),
                TextFieldWidget(basePackageName),
//                CheckBoxWidget(needRepo),
//                TextFieldWidget(repoPackageName),

//                CheckBoxWidget(needPresenter),
//                TextFieldWidget(presenterPackageName),
//                CheckBoxWidget(needDagger),
//                TextFieldWidget(componentPackageName),
//                TextFieldWidget(moudlePackageName),
                LanguageWidget()
            )

            //创建所需文件
            recipe = { te ->
                //val (projectData, srcOut, resOut) = te as ModuleTemplateData
                armsRecipe(this@ArmsPluginTemplateProviderImpl, (te as ModuleTemplateData))
            }
        }


    /** 新建页面名称 */
    val pageName = stringParameter {
        name = "Page Name"
        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.STRING)
        default = "Main"
        help = "请填写页面名,如填写 Main,会自动生成 MainActivity, MainViewModel 等文件"
    }

    /** 包名 */
    val appPackageName = stringParameter {
        name = "Root Package Name"
        constraints = listOf(Constraint.PACKAGE)
        default = "com.neusoft.dc1e"
        help = "请填写你的项目包名,请认真核实此包名是否是正确的项目包名,不能包含子包,正确的格式如:com.neusoft.***"
    }

    /** 是否需要 Activity */
    val needActivity = booleanParameter {
        name = "Generate Activity"
        default = true
        help = "是否需要生成 Activity ? 不勾选则不生成"
    }

    /** layout xml 文件 */
    val activityLayoutName = stringParameter {
        name = "Activity Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { activityToLayout(pageName.value) }
        default = "activity_main"
        visible = { needActivity.value }
        help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要 layout xml 文件 */
    val generateActivityLayout = booleanParameter {
        name = "Generate Activity Layout"
        default = true
        visible = { needActivity.value }
        help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
    }

    /** Activity 路径 */
    val activityPackageName = stringParameter {
        name = "Ativity Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.ui.activity" }
        visible = { needActivity.value }
        default = "${appPackageName.value}.mvvm.ui.activity"
        help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    /** 是否需要 Fragment */
    val needFragment = booleanParameter {
        name = "Generate Fragment"
        default = false
        help = "是否需要生成 Fragment ? 不勾选则不生成"
    }

    /** Fragment xml 文件 */
    val fragmentLayoutName = stringParameter {
        name = "Fragment Layout Name"
        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
        suggest = { "fragment_${classToResource(pageName.value)}" }
        default = "fragment_main"
        visible = { needFragment.value }
        help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
    }

    /** 是否需要生成 Fragment layout 文件 */
    val generateFragmentLayout = booleanParameter {
        name = "Generate Fragment Layout"
        default = true
        visible = { needFragment.value }
        help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
    }

    /** fragment 路径 */
    val fragmentPackageName = stringParameter {
        name = "Fragment Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.ui.fragment" }
        default = "${appPackageName.value}.mvvm.ui.fragment"
        visible = { needFragment.value }
        help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    /**  mvvm 相关 */
    val needViewModel = booleanParameter {
        name = "Generate ViewModel"
        default = true
        help = "是否需要生成 ViewModel ? 不勾选则不生成"
    }
    val viewModelPackageName = stringParameter {
        name = "ViewModel Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.viewmodel" }
        default = "${appPackageName.value}.mvvm.viewmodel"
        visible = { needViewModel.value }
        help = "ViewModel 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    val needRepo = booleanParameter {
        name = "Generate Repo"
        default = true
        help = "是否需要生成 Repo ? 不勾选则不生成"
    }
    val repoPackageName = stringParameter {
        name = "Repo Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "${appPackageName.value}.mvvm.repo" }
        default = "${appPackageName.value}.mvvm.repo"
        visible = { needRepo.value }
        help = "Repo 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
    }

    val basePackageName = stringParameter {
        name = "BASE Package Name"
        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
        suggest = { "com.zeekrlife.base.ui.page.base" }
        default = "com.zeekrlife.base.ui.page.base"
        help = "请认真核实此包名是否是你所依赖的目标基础包名"
    }

//    /** dagger 相关 */
//    val needDagger = booleanParameter {
//        name = "Generate Dagger (Moudle And Component)"
//        default = true
//        help = "是否需要生成 Dagger 组件? 不勾选则不生成"
//    }
//    val componentPackageName = stringParameter {
//        name = "Component Package Name"
//        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.component" }
//        default = "${appPackageName.value}.di.component"
//        visible = { needDagger.value }
//        help = "Component 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }
//
//    val moudlePackageName = stringParameter {
//        name = "Moudle Package Name"
//        constraints = listOf(Constraint.PACKAGE, Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.module" }
//        default = "${appPackageName.value}.di.module"
//        visible = { needDagger.value }
//        help = "Moudle 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }


}