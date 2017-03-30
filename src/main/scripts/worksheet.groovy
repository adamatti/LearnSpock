// HOW TO RUN IT: "gradle console" . Have fun X-)

import adamatti.bo.*
import adamatti.helper.*
import adamatti.model.*

def dao = new PersonDAO()
println "Current Count is ${dao.count()}"

def person = new Person(id: "12bca87d-1ec6-4519-9b63-bf18c83aa11a", name:"adamatti")
dao.save(person)