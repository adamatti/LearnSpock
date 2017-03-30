package adamatti.model

import groovy.transform.ToString

@ToString(
    ignoreNulls = true,
    includePackage = false,
    includeNames = true
)
class Person implements Serializable {
    String id
    String name

	Address address
}
