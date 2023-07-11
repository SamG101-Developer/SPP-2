package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.parser.SppParser;
import org.intellij.sdk.language.psi.SppFile;
import org.intellij.sdk.language.psi.SppTokenSets;
import org.intellij.sdk.language.psi.SppTypes;
import org.jetbrains.annotations.NotNull;


public class SppParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(SppLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new SppLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new SppParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return SppTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return SppTokenSets.STRING_LITERALS;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return SppTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new SppFile(viewProvider);
    }
}
