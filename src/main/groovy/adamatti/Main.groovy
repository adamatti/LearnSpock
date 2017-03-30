package adamatti

import adamatti.bo.PersonBO
import adamatti.helper.SparkHelper
import adamatti.model.PersonDAO
import adamatti.view.PersonView
import groovy.util.logging.Slf4j

@Slf4j
class Main {
    private static final int PORT = 8080

    static void main(String [] args){
		SparkHelper.start PORT
		startAppContext()
        log.info "App started [port: ${PORT}]"
    }

	private static void startAppContext(){
		def dao = new PersonDAO()
		def bo = new PersonBO(personDAO: dao)
		new PersonView(personBO: bo).registerEndpoints()
	}
}
