package com.smalaca.apartmentsapp.assertions;

import com.smalaca.apartmentsapp.apartment.Apartment;
import com.smalaca.apartmentsapp.apartment.ApartmentAssertion;
import com.smalaca.apartmentsapp.events.InvalidAddressRecognized;
import com.smalaca.apartmentsapp.events.InvalidAddressRecognizedAssertion;

public class AssertionsFacade {
    public static ApartmentAssertion assertThat(Apartment actual) {
        return new ApartmentAssertion(actual);
    }

    public static InvalidAddressRecognizedAssertion assertThat(InvalidAddressRecognized actual) {
        return new InvalidAddressRecognizedAssertion(actual);
    }
}
