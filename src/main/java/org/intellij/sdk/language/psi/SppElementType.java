package org.intellij.sdk.language.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.SppLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SppElementType extends IElementType {
    public SppElementType(@NonNls @NotNull String debugName) {
        super(debugName, SppLanguage.INSTANCE);
    }
}
