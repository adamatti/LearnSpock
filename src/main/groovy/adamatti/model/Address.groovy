package adamatti.model

import groovy.transform.ToString

@ToString(
	ignoreNulls = true,
	includePackage = false,
	includeNames = true
)
class Address implements Serializable {
	String city
	String state
	String country
}
