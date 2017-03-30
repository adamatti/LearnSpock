package adamatti.model

import static adamatti.helper.JsonHelper.*
import static java.util.UUID.randomUUID
import groovy.util.logging.Slf4j

@Slf4j
class PersonDAO {
    //TODO move it to a property file
    private static final String FOLDER_PATH = "C:/Temp/spock"
    private static final File FOLDER = new File(FOLDER_PATH)

    Person save(Person person){
        log.debug "${person}"

        generateIdIfNeeded(person)
        delete(person)
        getFile(person) << toJson(person)
        person
    }

    private void generateIdIfNeeded(Person person){
        if (!person.id) {
            person.id = randomUUID().toString()
        }
    }

    void delete(Person person){
        log.debug "${person}"

        File file = getFile(person)
        if (file.exists()){
            file.delete()
        }
    }

    int count(Map args = [:]){
        log.debug "args: ${args}"

        FOLDER.listFiles().size()
    }

    Person find(String id){
        log.debug "id: ${id}"

        File file = getFile(id)
        if (file.exists()){
            return fromJson(file.getText())
        }
        null
    }

    List<Person> list(Map args = [:]){
        log.debug "args: ${args}"

        FOLDER.listFiles().collect{ File f ->
            fromJson(f.getText()) as Person
        }
    }

    private File getFile(Person person){
        getFile(person.id)
    }

    private File getFile(String id){
        new File(FOLDER, "${id}.json")
    }

    // Just for test purpose
    protected void deleteAll(){
        FOLDER.eachFile {f->
            f.delete()
        }
    }
}
