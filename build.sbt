lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DockerPlugin)
  .settings(
    name := """To-Do-List-Api""",
    version := "1.0",
    scalaVersion := "2.13.11",
    dockerBaseImage := "openjdk:11.0.7",
    dockerExposedPorts := Seq(9443),
    libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "2.1.214",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "org.postgresql" % "postgresql" % "42.5.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "org.mindrot" % "jbcrypt" % "0.4",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "com.spotify" % "docker-client" % "8.16.0",
      "io.swagger" %% "swagger-scala-module" % "1.0.6"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
    Universal / javaOptions ++= Seq(
      "-Dpidfile.path=/dev/null"
    ),
  )
