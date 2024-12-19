package org.apache.struts_example;

import org.apache.struts2.text.StrutsTextProviderFactory;
import org.apache.struts2.text.TextProvider;

import java.util.ResourceBundle;
/**
 * Exdending the TextProviderFactory is optional, as Factory return the TextProvider class it gets as argument.
 * See struts.xml for example configuration without extending TextProviderFactory.
 */
public class MyTextProviderFactory extends StrutsTextProviderFactory {

    @Override
    protected TextProvider getTextProvider(Class clazz) {
        return new MyTextTextProvider(clazz, localeProviderFactory.createLocaleProvider(), localizedTextProvider);
    }

    @Override
    protected TextProvider getTextProvider(ResourceBundle bundle) {
        return new MyTextTextProvider(bundle, localeProviderFactory.createLocaleProvider(), localizedTextProvider);
    }
}
