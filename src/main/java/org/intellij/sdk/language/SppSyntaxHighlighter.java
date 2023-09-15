package org.intellij.sdk.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SppTokenSets;
import org.intellij.sdk.language.psi.SppTypes;
import org.jetbrains.annotations.NotNull;

public class SppSyntaxHighlighter extends SyntaxHighlighterBase {
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new SppLexerAdapter();
    }

    public static TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("SPP_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static TextAttributesKey OPERATION_SIGN = TextAttributesKey.createTextAttributesKey("SPP_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("SPP_STRING", DefaultLanguageHighlighterColors.STRING);
    public static TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey("SPP_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("SPP_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("SPP_NUMBER", DefaultLanguageHighlighterColors.NUMBER);

    public static TextAttributesKey FUNCTION_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_FUNCTION_IDENTIFIER", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static TextAttributesKey TYPE_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_TYPE_IDENTIFIER", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static TextAttributesKey DECORATOR_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_DECORATOR_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static TextAttributesKey GENERIC_PARAMETER_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_GENERIC_PARAMETER_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static TextAttributesKey FUNCTION_PARAMETER_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_FUNCTION_PARAMETER_IDENTIFIER", DefaultLanguageHighlighterColors.PARAMETER);
    public static TextAttributesKey MEMBER_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_MEMBER_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static TextAttributesKey FUNCTION_NAMED_ARG_IDENTIFIER = TextAttributesKey.createTextAttributesKey("SPP_FUNCTION_NAMED_ARG_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);


    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATION_SIGN};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (SppTokenSets.KEYWORDS.contains(tokenType)) {
            return KEYWORD_KEYS;
        } else if (SppTokenSets.OPERATORS.contains(tokenType)) {
            return OPERATOR_KEYS;
        } else if (SppTokenSets.STRING_LITERALS.contains(tokenType)) {
            return STRING_KEYS;
        } else if (tokenType.equals(SppTypes.LINE_COMMENT)) {
            return LINE_COMMENT_KEYS;
        } else if (tokenType.equals(SppTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        } else if (SppTokenSets.NUMBERS.contains(tokenType)) {
            return NUMBER_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
