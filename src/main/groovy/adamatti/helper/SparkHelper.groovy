package adamatti.helper

import spark.Spark

abstract class SparkHelper {
	static void start(int port){
		Spark.port(port)
		File webapp = new File("src/main/webapp")
		Spark.externalStaticFileLocation(webapp.absolutePath)
	}
}
