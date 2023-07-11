package org.intellij.sdk.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SppTokenSets;
import org.jetbrains.annotations.NotNull;

public class SppSyntaxHighlighter extends SyntaxHighlighterBase {
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new SppLexerAdapter();
    }

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{DefaultLanguageHighlighterColors.KEYWORD};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{DefaultLanguageHighlighterColors.OPERATION_SIGN};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{DefaultLanguageHighlighterColors.STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{DefaultLanguageHighlighterColors.LINE_COMMENT};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{DefaultLanguageHighlighterColors.NUMBER};

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (SppTokenSets.KEYWORDS.contains(tokenType)) {
            return KEYWORD_KEYS;
        } else if (SppTokenSets.OPERATORS.contains(tokenType)) {
            return OPERATOR_KEYS;
        } else if (SppTokenSets.STRING_LITERALS.contains(tokenType)) {
            return STRING_KEYS;
        } else if (SppTokenSets.COMMENTS.contains(tokenType)) {
            return COMMENT_KEYS;
        } else if (SppTokenSets.NUMBERS.contains(tokenType)) {
            return NUMBER_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
