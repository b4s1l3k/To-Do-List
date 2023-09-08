lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """To-Do-List""",
    version := "1.1",
    scalaVersion := "2.13.11",

    dockerBaseImage := "openjdk:11.0.7",
    dockerExposedPorts := Seq(9000),

    libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "2.1.214",
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "org.postgresql" % "postgresql" % "42.5.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "com.github.tminglei" %% "slick-pg" % "0.21.1",
      "org.mindrot" % "jbcrypt" % "0.4"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
  )
