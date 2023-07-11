package org.intellij.sdk.language;

import com.intellij.lexer.FlexAdapter;


public class SppLexerAdapter extends FlexAdapter {
    public SppLexerAdapter() {
        super(new _SppLexer(null));
    }
}
