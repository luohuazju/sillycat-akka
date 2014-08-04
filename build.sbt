import AssemblyKeys._

name := "sillycat-akka" 

organization := "com.sillycat.akka" 

version := "1.0" 

scalaVersion := "2.10.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8") 

resolvers ++= Seq(
	  "sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/",
  	"sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  	"typesafe repo"      at "http://repo.typesafe.com/typesafe/releases/",
  	"spray repo"         at "http://repo.spray.io/",
  	"maven repo"         at "http://repo1.maven.org/maven2/"
)

libraryDependencies ++= Seq(
    "org.slf4j"           % "slf4j-simple"                  % "1.7.7",
    "org.slf4j"           % "slf4j-api"                     % "1.7.7",
    "org.scalatest"       % "scalatest_2.10"                % "1.9.1",
    "org.joda"                  %   "joda-convert"          % "1.3.1",
    "joda-time"                 %   "joda-time"             % "2.2",
    "com.typesafe"		    %%	"scalalogging-slf4j"	        % "1.0.1",
    "com.typesafe.akka"   %% "akka-actor"                   % "2.3.4",
    "com.typesafe.akka"   %% "akka-testkit"                 % "2.3.4",
    "com.typesafe.akka"   %% "akka-transactor"              % "2.3.4"
)

seq(Revolver.settings: _*)

publishArtifact in Test := true

assemblySettings

mainClass in assembly := Some("com.sillycat.akka.Boot")

artifact in (Compile, assembly) ~= { art =>
  art.copy(`classifier` = Some("assembly"))
}

excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {_.data.getName == "parboiled-scala_2.10.0-RC1-1.1.3.jar"}
}

addArtifact(artifact in (Compile, assembly), assembly)

scalariformSettings

