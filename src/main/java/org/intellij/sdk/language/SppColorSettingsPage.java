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
                pub mod a::c::c
                
                use a::d::c;
                use a::d::{f, g, h};
                use a::c::f::*;
                
                pub cls class_1 {
                    a: std::Num;
                    b: std::Vec<str::Num>;
                }
                
                sup class_1 {
                    pub fn function_1<T, U=T, ...V>(a: T, f: std::Vec<U>, ...other: V) -> std::Str {
                        let a = 1;
                        let b, c = (1, 2);
                        
                        let x = if a > 100 {
                            let v = do_something();
                            v + a;
                        } elif b = some_val(), a < b.attr {
                            let v = b.other_attr / y;
                            v * 100;
                        } else {
                            x;
                        };
                        
                        let y = match x {
                            1 => 2,
                            2 => 3,
                            _ => 4,
                        };
                        
                        let z = while some_condition() as 'outer_loop {
                            while some_other_condition() as 'inner_loop {
                                if some_condition() {
                                    break 'outer_loop;
                                } elif some_other_condition() {
                                    break 'inner_loop;
                                } else {
                                    continue 'inner_loop;
                                }
                            }
                        };
                    }
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
