package adamatti.bo

import adamatti.model.Person
import adamatti.model.PersonDAO
import groovy.util.logging.Slf4j

@Slf4j
class PersonBO {
    @Delegate
    private PersonDAO personDAO

    Person save(Person person){
        log.debug "${person}"

        validate(person)
        personDAO.save(person)
    }

    private void validate(Person person){
        try {
            assert person
            assert person.name
        } catch (Throwable t){
            throw t
        }
    }
}
