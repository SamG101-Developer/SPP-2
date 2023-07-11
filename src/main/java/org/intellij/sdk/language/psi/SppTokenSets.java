package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface SppTokenSets {
    TokenSet STRING_LITERALS = TokenSet.create(SppTypes.LITERAL_CHAR, SppTypes.LITERAL_STRING);
}
