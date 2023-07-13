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
                setHighlighting(element, holder, SppSyntaxHighlighter.TYPE_IDENTIFIER);
            }

            @Override
            public void visitClassIdentifier(@NotNull SppClassIdentifier element) {
                super.visitClassIdentifier(element);
                setHighlighting(element, holder, SppSyntaxHighlighter.TYPE_IDENTIFIER);
            }

//            @Override
//            public void visitGenericIdentifier(@NotNull SppGenericIdentifier element) {
//                super.visitGenericIdentifier(element);
//                setHighlighting(element, holder, SppSyntaxHighlighter.GENERIC_IDENTIFIER);
//            }
        });
    }

    private void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey key) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(element).textAttributes(key).create();
    }
}
