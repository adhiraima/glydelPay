// The Play plugin

logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "sbt-releases-repo" at  "http://repo.typesafe.com/typesafe/ivy-releases/"

resolvers += "sbt-plugins-repo" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

resolvers += "maven-central" at "http://repo1.maven.org/maven2/"

resolvers += "Typesafe Maven Repository" at "http://repo.typesafe.com/typesafe/maven-releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.0")

// Web plugins
addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.6")
addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.7")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0-RC2")
// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// Play Ebean support, to enable, uncomment this line, and enable in your build.sbt using
// enablePlugins(SbtEbean). Note, uncommenting this line will automatically bring in
// Play enhancer, regardless of whether the line above is commented out or not.
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "1.0.0")
