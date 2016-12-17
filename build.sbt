
lazy val scalaV = "2.11.8"
lazy val projectOrg = "cl.ingemet"
lazy val projectName = "service-export"
lazy val projectV = "1.0.0"


lazy val commonsSettings = Seq(
  organization := s"$projectOrg",
  version := projectV,
  scalaVersion := scalaV
)

lazy val root = (project in file("."))
  .settings(commonsSettings: _*)
  .settings(
    name := s"$projectName",
    libraryDependencies ++= dependencies,
    artifact in (Compile, assembly) := {
      val art = (artifact in (Compile, assembly)).value
      art.copy(`classifier` = Some("assembly"))
    }
  )
  .settings(addArtifact(artifact in(Compile, assembly), assembly).settings: _*)
  .settings(assemblyJarName in assembly := s"$projectName.jar")

/* dependencies */
resolvers += "Jasper" at "http://jaspersoft.artifactoryonline.com/jaspersoft/third-party-ce-artifacts/"
lazy val dependencies = Seq(
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.8.0",
  "net.sf.jasperreports" % "jasperreports" % "6.2.1",

  "org.scalatest" %% "scalatest" % "3.0.0" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.11" % Test
)

assemblyMergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xsd" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xjd" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xjb" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".handlers" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".fixml" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".txt" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".factory" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".map" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".xml" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "mailcap" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

test in assembly := {}