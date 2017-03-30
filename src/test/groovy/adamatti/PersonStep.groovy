package adamatti

import adamatti.model.Person
import adamatti.model.PersonDAO

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Before() {

}

Person person
PersonDAO personDAO = new PersonDAO()

Given(~"a new Person") { ->
	person = new Person(name:"Adamatti")
}

Given(~"a new, empty database"){ ->
	personDAO.deleteAll()
}

When(~"save a Person"){ ->
	personDAO.save(person)
}

Then(~/^Person Count should be (\d+)$/) { int qty ->
	assert qty == personDAO.count()
}
