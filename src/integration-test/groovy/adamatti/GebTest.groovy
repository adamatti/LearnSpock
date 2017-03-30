package adamatti

import adamatti.model.PersonDAO
import geb.spock.GebReportingSpec

class GebTest extends GebReportingSpec {
	def "test insert"(){
		given:
			def personDAO = new PersonDAO()
			personDAO.deleteAll()

			go "http://localhost:8080/"
		when:
			$("#newName").value("Adamatti")
			$("#btnSave").click()
		then:
			personDAO.count() == 1
	}
}
