package adamatti.bo

import adamatti.model.Address
import adamatti.model.Person
import adamatti.model.PersonDAO
import org.codehaus.groovy.runtime.powerassert.PowerAssertionError
import spock.lang.Specification
import spock.lang.Unroll

class PersonBOTest extends Specification{
    def "test save without a name"(){
        given:
            def dao = Mock(PersonDAO)
            def bo = new PersonBO(personDAO: dao)
        when:
            bo.save(new Person())
        then:
            thrown(PowerAssertionError)
    }

	@Unroll
    def "test save #label"(){
        given:
            def dao = Mock(PersonDAO)
            def bo = new PersonBO(personDAO: dao)
        when:
            bo.save(new Person(name:name, address: new Address(city:city)))
        then:
            1 * dao.save( _ as Person) >> {Person p -> println "Saved called: ${p.name} "}
		where:
			label          | name       | city
			"adamatti"     | "Adamatti" | "cachota"
			"marcelo"      | "marcelo"  | "gravataí"
    }
}
