package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface SppTokenSets {
    TokenSet STRING_LITERALS = TokenSet.create(SppTypes.LX_SNG_QUOTE_CHR, SppTypes.LX_DBL_QUOTE_STR);
    TokenSet COMMENTS = TokenSet.create();
    TokenSet KEYWORDS = TokenSet.create(
            SppTypes.KW_MOD, SppTypes.KW_USE, SppTypes.KW_ENUM, SppTypes.KW_IN, SppTypes.KW_PUB, SppTypes.KW_PROT,
            SppTypes.KW_PRIV, SppTypes.KW_ASYNC, SppTypes.KW_FN, SppTypes.KW_MUT, SppTypes.KW_LET, SppTypes.KW_IF,
            SppTypes.KW_ELIF, SppTypes.KW_ELSE, SppTypes.KW_WHILE, SppTypes.KW_FOR, SppTypes.KW_DO, SppTypes.KW_MATCH,
            SppTypes.KW_RET, SppTypes.KW_YIELD, SppTypes.KW_CLS, SppTypes.KW_AWAIT, SppTypes.KW_WHERE, SppTypes.KW_TRUE,
            SppTypes.KW_FALSE, SppTypes.KW_AS, SppTypes.KW_SUP, SppTypes.KW_WITH, SppTypes.KW_BREAK, SppTypes.KW_CONT,
            SppTypes.KW_SELF);
    TokenSet OPERATORS = TokenSet.create(
            SppTypes.TK_DBL_AMPERSAND, SppTypes.TK_DBL_AMPERSAND_EQUALS, SppTypes.TK_DBL_PIPE,
            SppTypes.TK_DBL_PIPE_EQUALS, SppTypes.TK_EXCLAMATION, SppTypes.TK_AMPERSAND, SppTypes.TK_AMPERSAND_EQUALS,
            SppTypes.TK_PIPE, SppTypes.TK_PIPE_EQUALS, SppTypes.TK_CARET, SppTypes.TK_CARET_EQUALS, SppTypes.TK_TILDE,
            SppTypes.TK_DBL_ANGLE_L, SppTypes.TK_DBL_ANGLE_L_EQUALS, SppTypes.TK_DBL_ANGLE_R,
            SppTypes.TK_DBL_ANGLE_R_EQUALS, SppTypes.TK_TRIP_ANGLE_L, SppTypes.TK_TRIP_ANGLE_L_EQUALS,
            SppTypes.TK_TRIP_ANGLE_R, SppTypes.TK_TRIP_ANGLE_R_EQUALS, SppTypes.TK_DOUBLE_EQUALS,
            SppTypes.TK_EXCLAMATION_EQUALS, SppTypes.TK_ANGLE_L, SppTypes.TK_ANGLE_L_EQUALS, SppTypes.TK_ANGLE_R,
            SppTypes.TK_ANGLE_R_EQUALS, SppTypes.TK_ARROW_FAT_DBL, SppTypes.TK_PLUS, SppTypes.TK_PLUS_EQUALS,
            SppTypes.TK_HYPHEN, SppTypes.TK_HYPHEN_EQUALS, SppTypes.TK_ASTERISK, SppTypes.TK_ASTERISK_EQUALS,
            SppTypes.TK_FORWARD_SLASH, SppTypes.TK_FORWARD_SLASH_EQUALS, SppTypes.TK_PERCENT,
            SppTypes.TK_PERCENT_EQUALS, SppTypes.TK_DBL_FORWARD_SLASH, SppTypes.TK_DBL_FORWARD_SLASH_EQUALS,
            SppTypes.TK_DBL_ASTRIX, SppTypes.TK_DBL_ASTRIX_EQUALS, SppTypes.TK_PAREN_L, SppTypes.TK_PAREN_R,
            SppTypes.TK_BRACKET_L, SppTypes.TK_BRACKET_R, SppTypes.TK_BRACE_L, SppTypes.TK_BRACE_R,
            SppTypes.TK_QUESTION, SppTypes.TK_DBL_QUESTION, SppTypes.TK_DBL_QUESTION_EQUALS, SppTypes.TK_PIPE_ARROW);
    TokenSet NUMBERS = TokenSet.create(SppTypes.LX_BIN_DIGITS, SppTypes.LX_DEC_DIGITS, SppTypes.LX_HEX_DIGITS);
}
