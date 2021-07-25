package com.smalaca.apartmentsapp.events;

import org.assertj.core.api.Assertions;

public class InvalidAddressRecognizedAssertion {
    private final InvalidAddressRecognized actual;

    public InvalidAddressRecognizedAssertion(InvalidAddressRecognized actual) {
        this.actual = actual;
    }

    public static InvalidAddressRecognizedAssertion assertThat(InvalidAddressRecognized actual) {
        return new InvalidAddressRecognizedAssertion(actual);
    }

    public InvalidAddressRecognizedAssertion hasStreetEqualTo(String expected) {
        Assertions.assertThat(actual.getStreet()).isEqualTo(expected);
        return this;
    }

    public InvalidAddressRecognizedAssertion hasHouseNumberEqualTo(String expected) {
        Assertions.assertThat(actual.getHouseNumber()).isEqualTo(expected);
        return this;
    }

    public InvalidAddressRecognizedAssertion hasApartmentNumberEqualTo(String expected) {
        Assertions.assertThat(actual.getApartmentNumber()).isEqualTo(expected);
        return this;
    }

    public InvalidAddressRecognizedAssertion hasCityEqualTo(String expected) {
        Assertions.assertThat(actual.getCity()).isEqualTo(expected);
        return this;
    }

    public InvalidAddressRecognizedAssertion hasCountryEqualTo(String expected) {
        Assertions.assertThat(actual.getCountry()).isEqualTo(expected);
        return this;
    }
}