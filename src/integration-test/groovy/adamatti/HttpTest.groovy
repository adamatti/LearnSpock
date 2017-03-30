package adamatti

import adamatti.model.Person
import adamatti.model.PersonDAO
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class HttpTest extends Specification {
	@Shared
	private RESTClient client = new RESTClient("http://localhost:8080/person","application/json")

	def "test get"(){
		given:
			def personDAO = new PersonDAO()
			personDAO.deleteAll()
		when:
			personDAO.save(new Person(name:"Ricardo"))
			HttpResponseDecorator response = client.get([:])
			List<Person> list = response.responseData
		then:
			list.size() == 1
			list.first().name == "Ricardo"
	}

	def "test post"(){
		given:
			def personDAO = new PersonDAO()
			personDAO.deleteAll()
		when:
			client.post(body: '{"name":"Ricardo"}')
		then:
			personDAO.count() == 1
	}
}
