package org.apache.struts_example;

import com.opensymphony.xwork2.util.StrutsLocalizedTextProvider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyLocalizedTextProvider extends StrutsLocalizedTextProvider {

    public MyLocalizedTextProvider() {
        super();

        URL[] urls;
        try {
            String resourceFolder = "src/main/webapp/resources";
            File file = new File(resourceFolder);
            if (!file.exists() || !file.isDirectory()){
                throw new RuntimeException("External resource not found file not found");
            }
            URL url = file.toURI().toURL();
            System.out.println(url);
            urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls, this.getCurrentThreadContextClassLoader());
            this.setDelegatedClassLoader(cl);
            this.addDefaultResourceBundle("global");
        } catch (MalformedURLException e) {
            throw new RuntimeException("MalformedURLException occurred during the messageBundle initialisation", e);
        }
    }
}
