package org.intellij.sdk.language;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;


public class SppAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        element.accept(new SppVisitor() {
            @Override
            public void visitFunctionIdentifier(@NotNull SppFunctionIdentifier element) {
                super.visitFunctionIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.FUNCTION_IDENTIFIER);
            }

            @Override
            public void visitTypeIdentifier(@NotNull SppTypeIdentifier element) {
                super.visitTypeIdentifier(element);
                if (!element.getParent().getNode().getElementType().equals(SppTypes.DECORATOR_IDENTIFIER))
                    setHighlighting(element, holder, SppSyntaxHighlighter.TYPE_IDENTIFIER);
            }

            @Override
            public void visitClassIdentifier(@NotNull SppClassIdentifier element) {
                super.visitClassIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.TYPE_IDENTIFIER);
            }

            @Override
            public void visitDecoratorIdentifier(@NotNull SppDecoratorIdentifier element) {
                super.visitDecoratorIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.DECORATOR_IDENTIFIER);
            }

            @Override
            public void visitTypeGenericParameterIdentifier(@NotNull SppTypeGenericParameterIdentifier element) {
                super.visitTypeGenericParameterIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.GENERIC_PARAMETER_IDENTIFIER);
            }

            @Override
            public void visitFunctionParameterIdentifier(@NotNull SppFunctionParameterIdentifier element) {
                super.visitFunctionParameterIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.FUNCTION_PARAMETER_IDENTIFIER);
            }

            @Override
            public void visitPostfixOperatorMemberAccess(@NotNull SppPostfixOperatorMemberAccess element) {
                super.visitPostfixOperatorMemberAccess(element);
                setHighlighting(element.getLastChild(), holder, SppSyntaxHighlighter.MEMBER_IDENTIFIER);
            }
        });
    }

    private void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey key) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(key).create();
    }
}
