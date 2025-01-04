package plugins.tools

import dsl.ktlint
import dsl.libs
import dsl.plugin
import dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class KtlintPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugin("ktlint").pluginId)

        ktlint {
            version.set(libs.version("ktlint"))
            verbose.set(true)
            android.set(true)
            enableExperimentalRules.set(true)
            outputToConsole.set(true)
            reporters {
                reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
                reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            }

            // 自動生成されたファイルをチェックしない
            filter {
                exclude("**/generated/**")
            }
        }
    }
}
