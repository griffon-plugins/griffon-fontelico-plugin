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
package griffon.plugins.fontelico;

import javax.annotation.Nonnull;

import static griffon.util.GriffonNameUtils.requireNonBlank;

/**
 * @author Andres Almiray
 */
public enum Fontelico {
    EMO_HAPPY("emo-happy", '\ue800'),
    EMO_WINK("emo-wink", '\ue801'),
    EMO_WINK2("emo-wink2", '\ue813'),
    EMO_UNHAPPY("emo-unhappy", '\ue802'),
    EMO_SLEEP("emo-sleep", '\ue803'),
    EMO_THUMBSUP("emo-thumbsup", '\ue804'),
    EMO_DEVIL("emo-devil", '\ue805'),
    EMO_SURPRISED("emo-surprised", '\ue806'),
    EMO_TONGUE("emo-tongue", '\ue807'),
    EMO_COFFEE("emo-coffee", '\ue808'),
    EMO_SUNGLASSES("emo-sunglasses", '\ue809'),
    EMO_DISPLEASED("emo-displeased", '\ue80a'),
    EMO_BEER("emo-beer", '\ue80b'),
    EMO_GRIN("emo-grin", '\ue80c'),
    EMO_ANGRY("emo-angry", '\ue80d'),
    EMO_SAINT("emo-saint", '\ue80e'),
    EMO_CRY("emo-cry", '\ue80f'),
    EMO_SHOOT("emo-shoot", '\ue810'),
    EMO_SQUINT("emo-squint", '\ue811'),
    EMO_LAUGH("emo-laugh", '\ue812'),
    SPIN1("spin1", '\ue830'),
    SPIN2("spin2", '\ue831'),
    SPIN3("spin3", '\ue832'),
    SPIN4("spin4", '\ue834'),
    SPIN5("spin5", '\ue838'),
    SPIN6("spin6", '\ue839'),
    FIREFOX("firefox", '\ue840'),
    CHROME("chrome", '\ue841'),
    OPERA("opera", '\ue842'),
    IE("ie", '\ue843'),
    CROWN("crown", '\ue844'),
    CROWN_PLUS("crown-plus", '\ue845'),
    CROWN_MINUS("crown-minus", '\ue846'),
    MARQUEE("marquee", '\ue847');

    private static final String ERROR_DESCRIPTION_BLANK = "Argument 'description' must not be blank";

    public static Fontelico findByDescription(@Nonnull String description) {
        requireNonBlank(description, "Icon description must not be blank.");
        String[] parts = description.split(":");
        for (Fontelico font : values()) {
            if (font.getDescription().equals(parts[0])) {
                return font;
            }
        }
        throw new IllegalArgumentException("Icon description '" + description + "' is invalid!");
    }

    private String description;
    private char code;

    Fontelico(@Nonnull String description, char code) {
        this.description = description;
        this.code = code;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    public char getCode() {
        return code;
    }

    @Nonnull
    public static IllegalArgumentException invalidDescription(@Nonnull String description) {
        requireNonBlank(description, ERROR_DESCRIPTION_BLANK);
        throw new IllegalArgumentException("Description " + description + " is not a valid Fontelico icon description");
    }

    @Nonnull
    public static IllegalArgumentException invalidDescription(@Nonnull String description, Exception e) {
        requireNonBlank(description, ERROR_DESCRIPTION_BLANK);
        throw new IllegalArgumentException("Description " + description + " is not a valid Fontelico icon description", e);
    }
}