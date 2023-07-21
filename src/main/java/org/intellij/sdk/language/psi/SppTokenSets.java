package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.TokenSet;

public interface SppTokenSets {
    TokenSet STRING_LITERALS = TokenSet.create(SppTypes.LX_SNG_QUOTE_CHR, SppTypes.LX_DOUBLE_QUOTE_STR);
    TokenSet COMMENTS = TokenSet.create(SppTypes.BLOCK_COMMENT, SppTypes.LINE_COMMENT);
    TokenSet KEYWORDS = TokenSet.create(
            SppTypes.KW_MOD, SppTypes.KW_USE, SppTypes.KW_ENUM, SppTypes.KW_IN, SppTypes.KW_FN, SppTypes.KW_MUT, SppTypes.KW_LET, SppTypes.KW_IF,
            SppTypes.KW_ELSE, SppTypes.KW_WHILE, SppTypes.KW_FOR, SppTypes.KW_DO,
            SppTypes.KW_RET, SppTypes.KW_YIELD, SppTypes.KW_CLS, SppTypes.KW_WHERE, SppTypes.KW_TRUE,
            SppTypes.KW_FALSE, SppTypes.KW_AS, SppTypes.KW_SUP, SppTypes.KW_WITH, SppTypes.KW_BREAK, SppTypes.KW_CONT,
            SppTypes.KW_SELF);
    TokenSet OPERATORS = TokenSet.create(
            SppTypes.TK_DOUBLE_AMPERSAND, SppTypes.TK_DOUBLE_AMPERSAND_EQUALS, SppTypes.TK_DOUBLE_PIPE,
            SppTypes.TK_DOUBLE_PIPE_EQUALS, SppTypes.TK_AMPERSAND, SppTypes.TK_AMPERSAND_EQUALS,
            SppTypes.TK_PIPE, SppTypes.TK_PIPE_EQUALS, SppTypes.TK_CARET, SppTypes.TK_CARET_EQUALS, SppTypes.TK_EQ,
            SppTypes.TK_NE, SppTypes.TK_LT, SppTypes.TK_LE, SppTypes.TK_GT,
            SppTypes.TK_GE, SppTypes.TK_SS, SppTypes.TK_ADD, SppTypes.TK_ADD_EQ,
            SppTypes.TK_SUB, SppTypes.TK_SUB_EQ, SppTypes.TK_MUL, SppTypes.TK_MUL_EQ,
            SppTypes.TK_DIV, SppTypes.TK_DIV_EQ, SppTypes.TK_REM,
            SppTypes.TK_REM_EQ,
            SppTypes.TK_PAREN_L, SppTypes.TK_PAREN_R,
            SppTypes.TK_BRACK_L, SppTypes.TK_BRACK_R, SppTypes.TK_BRACE_L, SppTypes.TK_BRACE_R,
            SppTypes.TK_QST);
    TokenSet NUMBERS = TokenSet.create(SppTypes.LX_BIN_DIGITS, SppTypes.LX_DEC_DIGITS, SppTypes.LX_HEX_DIGITS);
}
