organization := "com.sassunt"

name := "unfiltered-upload-sample"

scalaVersion := "2.9.1"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
   "net.databinder" %% "unfiltered-filter" % "0.5.1",
   "net.databinder" %% "unfiltered-jetty" % "0.5.1",
   "net.databinder" %% "unfiltered-netty" % "0.5.1",
   "net.databinder" %% "unfiltered-netty-server" % "0.5.1",
   "net.databinder" %% "unfiltered-uploads" % "0.5.1",
   "net.databinder" %% "dispatch-nio" % "0.8.5",
   "net.databinder" %% "dispatch-mime" % "0.8.5",
   "javax.servlet" % "servlet-api" % "2.5"
)

resolvers ++= Nil
