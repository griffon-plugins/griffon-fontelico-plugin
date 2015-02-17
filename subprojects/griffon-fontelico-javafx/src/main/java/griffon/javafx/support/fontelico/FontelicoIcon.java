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
package griffon.javafx.support.fontelico;

import griffon.core.editors.PropertyEditorResolver;
import griffon.plugins.fontelico.Fontelico;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.annotation.Nonnull;
import java.beans.PropertyEditor;

import static griffon.plugins.fontelico.Fontelico.invalidDescription;
import static griffon.util.GriffonClassUtils.requireState;
import static griffon.util.GriffonNameUtils.requireNonBlank;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class FontelicoIcon extends Label {
    private static final String FONTELICO_SET = "META-INF/resources/fontelico/3.0/fonts/fontelico.ttf";
    private static final String ERROR_FONT_FONTELICO_NULL = "Argument 'fontelico' must not be null";

    static {
        Font.loadFont(Thread.currentThread().getContextClassLoader().getResource(FONTELICO_SET).toExternalForm(), 16);
    }

    private Fontelico fontelico;
    private int iconSize;
    private Color iconColor;

    public FontelicoIcon() {
        this(Fontelico.EMO_HAPPY);
    }

    public FontelicoIcon(@Nonnull Fontelico fontelico) {
        this.fontelico = requireNonNull(fontelico, ERROR_FONT_FONTELICO_NULL);
        getStyleClass().add("fontelico-icon");
        setText(String.valueOf(fontelico.getCode()));
        setStyle("-fx-font-family: fontelico;");
        setIconSize(16);
        setIconColor(Color.BLACK);
    }

    public FontelicoIcon(@Nonnull String description) {
        this(Fontelico.findByDescription(description));
        resolveSize(description);
        resolveColor(description);
    }

    @Nonnull
    public Fontelico getFontelico() {
        return fontelico;
    }

    public void setFontelico(@Nonnull Fontelico fontelico) {
        this.fontelico = requireNonNull(fontelico, ERROR_FONT_FONTELICO_NULL);
        setText(String.valueOf(fontelico.getCode()));
    }

    public void setFontelico(@Nonnull String description) {
        requireNonBlank(description, "Argument 'description' must not be blank");
        setFontelico(Fontelico.findByDescription(description));
        resolveSize(description);
        resolveColor(description);
    }

    public void setIconSize(int size) {
        requireState(size > 0, "Argument 'size' must be greater than zero");
        setStyle(getStyle() + " -fx-font-size: " + size + "px;");
        this.iconSize = size;
    }

    public void setIconColor(@Nonnull Color color) {
        requireNonNull(color, "Argument 'color' must not be null");
        setTextFill(color);
        this.iconColor = color;
    }

    @Nonnull
    public Color getIconColor() {
        return iconColor;
    }

    public int getIconSize() {
        return iconSize;
    }

    private void resolveSize(String description) {
        String[] parts = description.split(":");
        if (parts.length > 1) {
            try {
                setIconSize(Integer.parseInt(parts[1]));
            } catch (NumberFormatException e) {
                throw invalidDescription(description, e);
            }
        } else {
            setIconSize(16);
        }
    }

    private void resolveColor(String description) {
        String[] parts = description.split(":");
        if (parts.length > 2) {
            PropertyEditor editor = PropertyEditorResolver.findEditor(Color.class);
            editor.setValue(parts[2]);
            Color color = (Color) editor.getValue();
            if (color != null) {
                setIconColor(color);
            }
        }
    }
}
