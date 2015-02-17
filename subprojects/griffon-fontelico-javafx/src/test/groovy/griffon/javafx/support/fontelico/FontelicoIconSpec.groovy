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
package griffon.javafx.support.fontelico

import griffon.plugins.fontelico.Fontelico
import javafx.embed.swing.JFXPanel
import spock.lang.Specification

/**
 * @author Andres Almiray
 */
class FontelicoIconSpec extends Specification {
    static {
        new JFXPanel()
    }

    def 'Can create a FontelicoIcon instance'() {
        given:
        Fontelico expected = Fontelico.EMO_HAPPY

        expect:
        FontelicoIcon faIcon = new FontelicoIcon(expected)
        faIcon.fontelico == expected
    }

    def 'Invalid FontelicoIcon arguments'() {
        when:
        new FontelicoIcon(arg)

        then:
        thrown(IllegalArgumentException)

        where:
        arg   | _
        ''    | _
        ' '   | _
        'foo' | _
    }
}
