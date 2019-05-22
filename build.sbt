


lazy val root = project
  .in(file("."))
  .settings(
    name := "com.tournament",
    version := "0.1",
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.7.1",
      
      "org.scalatest" %% "scalatest" % "3.0.5" % "test"
    )
  )