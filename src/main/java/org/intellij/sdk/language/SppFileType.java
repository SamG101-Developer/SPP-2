package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class SppFileType extends LanguageFileType {
    public static final SppFileType INSTANCE = new SppFileType();

    public SppFileType() {
        super(SppLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "Spp File";
    }

    @Override
    public @NotNull String getDescription() {
        return "Spp language file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "spp";
    }

    @Override
    public @Nullable Icon getIcon() {
        return SppIcons.FILE;
    }
}
