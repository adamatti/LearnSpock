package adamatti.helper

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

abstract class JsonHelper {
    static String toJson(Object o){
        new JsonBuilder(o).toPrettyString()
    }

    static Object fromJson(String json){
        new JsonSlurper().parseText(json)
    }
}
