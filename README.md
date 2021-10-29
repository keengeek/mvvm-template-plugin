# MVVM Template Plugin

<!-- Plugin description -->
**MVVM Template Plugin** is a repository that provides a plugin to make it easier to create a mvvm android project.

The main goal of this template is to speed up the learner to join the work with the better template.
<!-- Plugin description end -->
### Table of contents

In this README, we will highlight the following elements of template-project creation:

- [Getting started](#getting-started)
- [Gradle configuration](#gradle-configuration)
- [Plugin template structure](#plugin-template-structure)
- [Plugin configuration file](#plugin-configuration-file)
  - [Functional tests](#functional-tests)
  - [UI tests](#ui-tests)
- [Predefined Run/Debug configurations](#predefined-rundebug-configurations)
- [Continuous integration](#continuous-integration) based on GitHub Actions
  - [Dependencies management](#dependencies-management) with Dependabot
  - [Changelog maintenance](#changelog-maintenance) with the Gradle Changelog Plugin
  - [Release flow](#release-flow) using GitHub Releases
  - [Plugin signing](#plugin-signing) with your private certificate
  - [Publishing the plugin](#publishing-the-plugin) with the Gradle IntelliJ Plugin
- [FAQ](#faq)
- [Useful links](#useful-links)

## Getting started



## Gradle configuration


### Gradle properties



### Environment variables



## Plugin template structure

A generated IntelliJ Platform Plugin Template repository contains the following content structure:



## Plugin configuration file







### Functional tests



### UI tests


![Qodana][file:qodana.png]

## Predefined Run/Debug configurations

Within the default project structure, there is a `.run` directory provided containing predefined *Run/Debug
configurations* that expose corresponding Gradle tasks:

![Run/Debug configurations][file:run-debug-configurations.png]

| Configuration name   | Description                                                                                                                                                                   |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Run Plugin           | Runs [`:runIde`][gh:gradle-intellij-plugin-running-dsl] Gradle IntelliJ Plugin task. Use the *
Debug* icon for plugin debugging.                                               |
| Run Verifications    | Runs [`:runPluginVerifier`][gh:gradle-intellij-plugin-verifier-dsl] Gradle IntelliJ Plugin task to check the plugin compatibility against the specified IntelliJ IDEs.        |
| Run Qodana           | Runs [`:runInspections`][gh:gradle-qodana-plugin] Gradle Qodana Plugin task. Starts Qodana inspections in a Docker container and serves generated report on `localhost:8080`. |
| Run Tests            | Runs [`:test`][gradle-lifecycle-tasks] Gradle task.                                                                                                                           |
| Run IDE for UI Tests | Runs [`:runIdeForUiTests`][gh:intellij-ui-test-robot] Gradle IntelliJ Plugin task to allows for running UI tests within the IntelliJ IDE running instance.                    |

> **TIP:** You can find the logs from the running task in the `idea.log` tab.
>
> ![Run/Debug configuration logs][file:run-logs.png]

## Continuous integration

Continuous integration depends on [GitHub Actions][gh:actions], a set of workflows that make it possible to automate
your testing and release process. Thanks to such automation, you can delegate the testing and verification phases to the
Continuous Integration (CI) and instead focus on development (and writing more tests).

### Dependencies management

This Template project depends on Gradle plugins and external libraries – and during the development, you will add more
of them.

Keeping the project in good shape and having all the dependencies up-to-date requires time and effort, but it is
possible to automate that process using [Dependabot][gh:dependabot].

Dependabot is a bot provided by GitHub to check the build configuration files and review any outdated or insecure
dependencies of yours – in case if any update is available, it creates a new pull request
providing [the proper change][gh:dependabot-pr].


### Changelog maintenance

When releasing an update, it is essential to let your users know what the new version offers. The best way to do this is
to provide release notes.

The changelog is a curated list that contains information about any new features, fixes, and deprecations. When they are
provided, these lists are available in a few different places:

- the [CHANGELOG.md](./CHANGELOG.md) file,
- the [Releases page][gh:releases],
- the *What's new* section of JetBrains Marketplace Plugin page,
- and inside the Plugin Manager's item details.

There are many methods for handling the project's changelog. The one used in the current template project is
the [Keep a Changelog][keep-a-changelog] approach.

The [Gradle Changelog Plugin][gh:gradle-changelog-plugin] takes care of propagating information provided within
the [CHANGELOG.md](./CHANGELOG.md) to the [Gradle IntelliJ Plugin][gh:gradle-intellij-plugin]. You only have to take
care of writing down the actual changes in proper sections of the `[Unreleased]` section.

You start with an almost empty changelog:


Now proceed with providing more entries to the `Added` group, or any other one that suits your change the most (
see [How do I make a good changelog?][keep-a-changelog-how] for more details).

When releasing a plugin update, you don't have to care about bumping the `[Unreleased]` header to the upcoming version –
it will be handled automatically on the Continuous Integration (CI) after you publish your plugin. GitHub Actions will
swap it and provide you an empty section for the next release so that you can proceed with your development:

To configure how the Changelog plugin behaves, i.e., to create headers with the release date,
see [Gradle Changelog Plugin][gh:gradle-changelog-plugin] README file.

### Release flow



![Release draft][file:draft-release.png]

The draft release is a working copy of a release, which you can review before publishing. It includes a predefined title
and git tag, the current plugin version, for example, `v0.0.1`. The changelog is provided automatically using
the [gradle-changelog-plugin][gh:gradle-changelog-plugin]. An artifact file is also built with the plugin attached.
Every new Build overrides the previous draft to keep your *Releases* page clean.


### Plugin signing

Plugin Signing is a mechanism introduced in the 2021.2 release cycle to increase security
in [JetBrains Marketplace](https://plugins.jetbrains.com) and all of our IntelliJ-based IDEs.

JetBrains Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and
delivery pipeline.

The current project provides a predefined plugin signing configuration that lets you sign and publish your plugin from
the Continuous Integration (CI) and local environments. All the configuration related to the signing should be provided
using [environment variables](#environment-variables).

To find out how to generate signing certificates, check the [Plugin Signing][docs:plugin-signing] section in the
IntelliJ Platform Plugin SDK documentation.

### Publishing the plugin


> **TIP**: Set a suffix to the plugin version to publish it in the custom repository channel, i.e. `v1.0.0-beta` will push your plugin to the `beta` [release channel][docs:release-channel].

The authorization process relies on the `PUBLISH_TOKEN` secret environment variable, specified in the _Secrets_ section
of the repository _Settings_.

You can get that token in your JetBrains Marketplace profi le dashboard in the [My Tokens][jb:my-tokens] tab.

> **Important:**
> Before using the automated deployment process, it is necessary to manually create a new plugin in JetBrains Marketplace to specify options like the license, repository URL, etc.
> Please follow the [Publishing a Plugin][docs:publishing] instructions.

## FAQ

### How to use Java in my project?

Java language is supported by default along with Kotlin. Initially, the `/src/main/kotlin` directory is available with
minimal examples. You can still replace it or add the `/src/main/java` directory to start working with Java language
instead.

### How to disable *tests* or *build* job using the `[skip ci]` commit message?

Since February 2021, GitHub Actions [support the skip CI feature][github-actions-skip-ci]. If the message contains one
of the following strings: `[skip ci]`, `[ci skip]`, `[no ci]`, `[skip actions]`, or `[actions skip]` – workflows will
not be triggered.

## Useful links

- [IntelliJ Platform SDK DevGuide][docs]
- [IntelliJ Platform Explorer][jb:ipe]
- [Marketplace Quality Guidelines][jb:quality-guidelines]
- [IntelliJ Platform UI Guidelines][jb:ui-guidelines]
- [Marketplace Paid Plugins][jb:paid-plugins]
- [Kotlin UI DSL][docs:kotlin-ui-dsl]
- [IntelliJ SDK Code Samples][gh:code-samples]
- [JetBrains Platform Slack][jb:slack]
- [JetBrains Platform Twitter][jb:twitter]
- [IntelliJ IDEA Open API and Plugin Development Forum][jb:forum]
- [Keep a Changelog][keep-a-changelog]
- [GitHub Actions][gh:actions]

[docs]: https://plugins.jetbrains.com/docs/intellij?from=IJPluginTemplate

[docs:intro]: https://plugins.jetbrains.com/docs/intellij/intellij-platform.html?from=IJPluginTemplate

[docs:kotlin-ui-dsl]: https://plugins.jetbrains.com/docs/intellij/kotlin-ui-dsl.html?from=IJPluginTemplate

[docs:plugin.xml]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html?from=IJPluginTemplate

[docs:publishing]: https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate

[docs:release-channel]: https://plugins.jetbrains.com/docs/intellij/deployment.html?from=IJPluginTemplate#specifying-a-release-channel

[docs:using-gradle]: https://plugins.jetbrains.com/docs/intellij/gradle-build-system.html?from=IJPluginTemplate

[docs:plugin-signing]: https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate

[docs:testing-plugins]: https://plugins.jetbrains.com/docs/intellij/testing-plugins.html

[docs:qodana]: https://www.jetbrains.com/help/qodana

[docs:qodana-github-action]: https://www.jetbrains.com/help/qodana/qodana-intellij-github-action.html


[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml

[file:qodana.yml]: ./qodana.yml

[gh:actions]: https://help.github.com/en/actions

[gh:dependabot]: https://docs.github.com/en/free-pro-team@latest/github/administering-a-repository/keeping-your-dependencies-updated-automatically

[gh:code-samples]: https://github.com/JetBrains/intellij-sdk-code-samples

[gh:gradle-changelog-plugin]: https://github.com/JetBrains/gradle-changelog-plugin

[gh:gradle-qodana-plugin]: https://github.com/JetBrains/gradle-qodana-plugin

[gh:gradle-intellij-plugin]: https://github.com/JetBrains/gradle-intellij-plugin

[gh:gradle-intellij-plugin-running-dsl]: https://github.com/JetBrains/gradle-intellij-plugin#running-dsl

[gh:gradle-intellij-plugin-verifier-dsl]: https://github.com/JetBrains/gradle-intellij-plugin#plugin-verifier-dsl

[gh:releases]: https://github.com/JetBrains/intellij-platform-plugin-template/releases

[gh:build]: https://github.com/JetBrains/intellij-platform-plugin-template/actions?query=workflow%3ABuild

[gh:dependabot-pr]: https://github.com/JetBrains/intellij-platform-plugin-template/pull/73

[gh:intellij-ui-test-robot]: https://github.com/JetBrains/intellij-ui-test-robot

[gh:ui-test-example]: https://github.com/JetBrains/intellij-ui-test-robot/tree/master/ui-test-example

[jb:confluence-on-gh]: https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub

[jb:download-ij]: https://www.jetbrains.com/idea/download

[jb:forum]: https://intellij-support.jetbrains.com/hc/en-us/community/topics/200366979-IntelliJ-IDEA-Open-API-and-Plugin-Development

[jb:ipe]: https://plugins.jetbrains.com/intellij-platform-explorer

[jb:my-tokens]: https://plugins.jetbrains.com/author/me/tokens

[jb:paid-plugins]: https://plugins.jetbrains.com/docs/marketplace/paid-plugins-marketplace.html

[jb:quality-guidelines]: https://plugins.jetbrains.com/docs/marketplace/quality-guidelines.html

[jb:slack]: https://plugins.jetbrains.com/slack

[jb:twitter]: https://twitter.com/JBPlatform

[jb:ui-guidelines]: https://jetbrains.github.io/ui

[keep-a-changelog]: https://keepachangelog.com

[keep-a-changelog-how]: https://keepachangelog.com/en/1.0.0/#how

[github-actions-skip-ci]: https://github.blog/changelog/2021-02-08-github-actions-skip-pull-request-and-push-workflows-with-skip-ci/

[gradle]: https://gradle.org

[gradle-releases]: https://gradle.org/releases

[gradle-kotlin-dsl]: https://docs.gradle.org/current/userguide/kotlin_dsl.html

[gradle-lifecycle-tasks]: https://docs.gradle.org/current/userguide/java_plugin.html#lifecycle_tasks

[kotlin-for-plugin-developers]: https://plugins.jetbrains.com/docs/intellij/kotlin.html#adding-kotlin-support

[xpath]: https://www.w3.org/TR/xpath-21/
