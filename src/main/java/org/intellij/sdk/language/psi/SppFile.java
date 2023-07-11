package org.intellij.sdk.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.intellij.sdk.language.SppFileType;
import org.intellij.sdk.language.SppLanguage;
import org.jetbrains.annotations.NotNull;


public class SppFile extends PsiFileBase {
    public SppFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SppLanguage.INSTANCE);
    }


    @Override
    public @NotNull FileType getFileType() {
        return SppFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Spp File";
    }
}
