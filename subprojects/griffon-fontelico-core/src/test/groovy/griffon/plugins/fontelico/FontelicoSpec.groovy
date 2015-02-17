/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.plugins.fontelico

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Andres Almiray
 */
class FontelicoSpec extends Specification {
    @Unroll("Description #description is related to #expected")
    def "Fontelico by description is found"() {
        when:
        Fontelico actual = Fontelico.findByDescription(description)

        then:
        actual == expected
        actual.description == expected.description
        actual.code == expected.code

        where:
        description || expected
        'emo-happy' || Fontelico.EMO_HAPPY
    }

    @Unroll("Description '#description' is invalid")
    def "Invalid fontelico descriptions"() {
        when:
        Fontelico.findByDescription(description)

        then:
        thrown(IllegalArgumentException)

        where:
        description | _
        null        | _
        ''          | _
        ' '         | _
        'foo'       | _
    }
}
