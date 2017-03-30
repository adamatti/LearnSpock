package adamatti.view

import adamatti.model.Person

import static adamatti.helper.JsonHelper.*
import adamatti.bo.PersonBO
import groovy.util.logging.Slf4j
import spark.Request
import spark.Response
import spark.Spark

import javax.annotation.PostConstruct

@Slf4j
class PersonView {
    @Delegate
    private PersonBO personBO

    @PostConstruct
    void registerEndpoints(){
        Spark.get("/person"){ Request req, Response res ->
            res.header "Content-Type", "Application/json"

            toJson personBO.list()
        }

        Spark.get("/person/:id") { Request req, Response res ->
            res.header "Content-Type", "Application/json"

            toJson personBO.find(req.params("id")) ?: [:]
        }

		Spark.post("/person") { Request req, Response res ->
			Person person = fromJson(req.body())
			toJson personBO.save(person)
		}

		Spark.delete("/person") { Request req, Response res ->
			Person person = new Person(id: req.queryParams("id"))
			personBO.delete(person)
			toJson [:]
		}
    }
}
