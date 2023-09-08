lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """To-Do-List-Api""",
    version := "1.1",
    scalaVersion := "2.13.11",

    dockerBaseImage := "openjdk:11.0.7",
    dockerExposedPorts := Seq(9000),

    libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "2.1.214",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "org.postgresql" % "postgresql" % "42.5.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "org.mindrot" % "jbcrypt" % "0.4",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "org.webjars" % "swagger-ui" % "4.18.1",
      "com.github.dwickern" %% "swagger-play2.8" % "3.1.0",
      "io.swagger" % "swagger-core" % "1.6.2",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.11.1"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
  )
