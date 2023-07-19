package org.intellij.sdk.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class SppColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", SppSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Operator", SppSyntaxHighlighter.OPERATION_SIGN),
            new AttributesDescriptor("String", SppSyntaxHighlighter.STRING),
            new AttributesDescriptor("Line comment", SppSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", SppSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Number", SppSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Function identifier", SppSyntaxHighlighter.FUNCTION_IDENTIFIER),
            new AttributesDescriptor("Type identifier", SppSyntaxHighlighter.TYPE_IDENTIFIER),
            new AttributesDescriptor("Decorator identifier", SppSyntaxHighlighter.DECORATOR_IDENTIFIER),
            new AttributesDescriptor("Generic parameter", SppSyntaxHighlighter.GENERIC_PARAMETER_IDENTIFIER),
            new AttributesDescriptor("Function parameter identifier", SppSyntaxHighlighter.FUNCTION_PARAMETER_IDENTIFIER),
            new AttributesDescriptor("Member identifier", SppSyntaxHighlighter.MEMBER_IDENTIFIER),
            new AttributesDescriptor("Function named argument identifier", SppSyntaxHighlighter.FUNCTION_NAMED_ARG_IDENTIFIER),
    };

    @Override
    public @Nullable Icon getIcon() {
        return SppIcons.FILE;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new SppSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return """
                @meta::public
                mod src::my_project::folder1::folder2::file1;
                
                use std::num::Num;
                use std::str::{Str, ToStr};
                
                use src::my_project::folder1::folder2::file2::my_function as func;
                use src::my_project::folder1::folder2::file3::{my_function, my_function2};
                use src::my_project::folder1::folder2::file4::*
                
                @meta::public
                cls MyClass {
                    @meta::private a: std::Num;
                    @meta::private b: std::Num;
                }
                
                @meta::public
                fn my_function<T: ToStr, U: ToStr>(a: T, b: U) -> std::Str {
                    return a.to_str() + b.to_str();
                }
               """;

    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return "S++";
    }
}
