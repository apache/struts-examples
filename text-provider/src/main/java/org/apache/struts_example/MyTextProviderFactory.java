package org.apache.struts_example;

import com.opensymphony.xwork2.StrutsTextProviderFactory;
import com.opensymphony.xwork2.TextProvider;

import java.util.ResourceBundle;

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
