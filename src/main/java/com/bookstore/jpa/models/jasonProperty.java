package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * jasonProperty
 */
public @interface jasonProperty {

    public JsonProperty.Access access();

}
