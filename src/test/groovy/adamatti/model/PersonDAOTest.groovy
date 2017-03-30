package adamatti.model

import spock.lang.Specification

class PersonDAOTest extends Specification{
    def "test crud"(){
        given:
            def dao = new PersonDAO()
            dao.deleteAll()
            def vo = new Person(name:"adamatti")
        when:
            def result = dao.save(vo)
        then: "Check Insert"
            result.id != null
            dao.count() == 1
        when:
            dao.save(result)
        then: "Check Update"
            dao.count() == 1
        when: "Check find"
            result = dao.find(result.id)
        then:
            result !=null
        when: "Do delete"
            dao.delete(result)
        then:
            dao.count() == 0
    }

    def "test list"(){
        given:
            def dao = new PersonDAO()
            dao.deleteAll()
            ["Marcelo","Adamatti"].each {
                dao.save(new Person(name: it, address: new Address(city:"cachota")))
            }
        when:
            def result = dao.list()
        then:
            result.size() == 2
    }
}
