package org.intellij.sdk.language;
import com.intellij.lang.Language;

public class SppLanguage extends Language {
    public static final SppLanguage INSTANCE = new SppLanguage();

    private SppLanguage() {
        super("Spp");
    }
}
