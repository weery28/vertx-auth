/*
 * Copyright 2015 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */
package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * Options describing how an JWT KeyStore should behave.
 * This is an extended version core's {@link io.vertx.core.net.KeyStoreOptions}.
 * <p>
 * This extension sets the default type to the runtime keystore type (for compatibility, reasons)
 * plus it allows the configuration of password per key using {@link #setPasswordProtection(Map)}.
 *
 * @author <a href="mailto:plopes@redhat.com">Paulo Lopes</a>
 */
@DataObject(generateConverter = true)
public class KeyStoreOptions extends io.vertx.core.net.KeyStoreOptions {

  // Defaults
  private static final String DEFAULT_TYPE = KeyStore.getDefaultType();

  private Map<String, String> passwordProtection;

  /**
   * Default constructor
   */
  public KeyStoreOptions() {
    super();
    setType(DEFAULT_TYPE);
  }

  /**
   * Copy constructor
   *
   * @param other the options to copy
   */
  public KeyStoreOptions(KeyStoreOptions other) {
    super(other);
    if (getType() == null) {
      setType(DEFAULT_TYPE);
    }
    passwordProtection = other.getPasswordProtection();
  }

  /**
   * Constructor to create an options from JSON
   *
   * @param json the JSON
   */
  public KeyStoreOptions(JsonObject json) {
    KeyStoreOptionsConverter.fromJson(json, this);
  }

  @Override
  public String getType() {
    return super.getType();
  }

  @Fluent
  @Override
  public KeyStoreOptions setType(String type) {
    super.setType(type);
    return this;
  }

  @Fluent
  @Override
  public KeyStoreOptions setPassword(String password) {
    super.setPassword(password);
    return this;
  }

  @Fluent
  @Override
  public KeyStoreOptions setPath(String path) {
    super.setPath(path);
    return this;
  }

  @Fluent
  @Override
  public KeyStoreOptions setValue(Buffer value) {
    super.setValue(value);
    return this;
  }

  public Map<String, String> getPasswordProtection() {
    return passwordProtection;
  }

  @Fluent
  public KeyStoreOptions setPasswordProtection(Map<String, String> passwordProtection) {
    this.passwordProtection = passwordProtection;
    return this;
  }

  public KeyStoreOptions putPasswordProtection(String alias, String password) {
    if (passwordProtection == null) {
      passwordProtection = new HashMap<>();
    }

    this.passwordProtection.put(alias, password);
    return this;
  }
}
