import sbt.Keys._
import sbt.Project.projectToRef

lazy val commonSettings = Seq(
  scalaVersion := "2.12.2",
  libraryDependencies := Seq(
    "org.scalactic" %% "scalactic" % "3.0.1",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
)

lazy val core = (crossProject.crossType(CrossType.Pure) in file("core"))
  .jsConfigure(_ enablePlugins ScalaJSPlugin)
  .settings(
    name := "core",
    commonSettings
  )

lazy val coreJs = core.js.settings(name := "corejs")
lazy val coreJvm = core.jvm.settings(name := "corejvm")

lazy val ui = project
  .enablePlugins(ScalaJSPlugin)
  .settings(
    commonSettings,
    name := "ui",
    scalaJSUseMainModuleInitializer := false
  )
  .dependsOn(coreJs)

lazy val cli = project
  .settings(
    commonSettings,
    name := "cli",
    libraryDependencies := Seq(
      "com.github.scopt" %% "scopt" % "3.7.0"
    )
  )
  .dependsOn(coreJvm)
