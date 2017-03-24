package org.apache.struts_example;

import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.LocalizedTextProvider;
import com.opensymphony.xwork2.TextProviderSupport;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.List;
import java.util.ResourceBundle;

public class MyTextTextProvider extends TextProviderSupport {

    public MyTextTextProvider(Class clazz, LocaleProvider provider, LocalizedTextProvider localizedTextProvider) {
        super(clazz, provider, localizedTextProvider);
    }

    public MyTextTextProvider(ResourceBundle bundle, LocaleProvider provider, LocalizedTextProvider localizedTextProvider) {
        super(bundle, provider, localizedTextProvider);
    }

    @Override
    public String getText(String key) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale());
    }

    @Override
    public String getText(String key, String defaultValue) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale());
    }

    @Override
    public boolean hasKey(String key) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale()) != null;
    }

    @Override
    public String getText(String key, String defaultValue, String arg) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), new Object[] { arg });
    }

    @Override
    public String getText(String key, List<?> args) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args.toArray());
    }

    @Override
    public String getText(String key, String[] args) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args);
    }

    @Override
    public String getText(String key, String defaultValue, List<?> args) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args.toArray());
    }

    @Override
    public String getText(String key, String defaultValue, String[] args) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args);
    }

    @Override
    public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args.toArray());
    }

    @Override
    public String getText(String key, String defaultValue, String[] args, ValueStack stack) {
        return localizedTextProvider.findDefaultText(key, localeProvider.getLocale(), args);
    }

    @Override
    public ResourceBundle getTexts(String aBundleName) {
        return localizedTextProvider.findResourceBundle(aBundleName, localeProvider.getLocale());
    }

    @Override
    public ResourceBundle getTexts() {
        return super.getTexts();
    }
}
