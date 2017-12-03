package org.demo;

import flexjson.transformer.AbstractTransformer;

public class PasswordTransformer extends AbstractTransformer {
    public void transform(Object o) {
        getContext().writeQuoted("******");
    }
}
