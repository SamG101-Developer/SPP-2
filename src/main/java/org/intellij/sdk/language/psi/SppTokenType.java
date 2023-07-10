package org.intellij.sdk.language.psi;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SppTokenType extends IElementType {
    public SppTokenType(@NonNls @NotNull String debugName, @Nullable Language language) {
        super(debugName, language);
    }

    @Override
    public String toString() {
        return "SppTokenType." + super.toString();
    }
}
