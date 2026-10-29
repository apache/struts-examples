package org.demo;

import flexjson.transformer.AbstractTransformer;

public class ExcludeTransformer extends AbstractTransformer {
  @Override
  public Boolean isInline() {
    return true;
  }

  @Override
  public void transform(Object o) {}
}
