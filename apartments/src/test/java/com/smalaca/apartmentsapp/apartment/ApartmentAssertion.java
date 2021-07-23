package com.smalaca.apartmentsapp.apartment;

import com.smalaca.apartmentsapp.owner.OwnerId;
import org.assertj.core.api.Assertions;

public class ApartmentAssertion {
    private final Apartment actual;

    public ApartmentAssertion(Apartment actual) {
        this.actual = actual;
    }

    public static ApartmentAssertion assertThat(Apartment actual) {
        return new ApartmentAssertion(actual);
    }

    public ApartmentAssertion hasIdEqualTo(ApartmentId expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasOwnerIdEqualTo(OwnerId expected) {
        Assertions.assertThat(actual.getOwnerId()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasAddressStreetEqualTo(String expected) {
        Assertions.assertThat(actual.getAddress().getStreet()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasAddressHouseNumberEqualTo(String expected) {
        Assertions.assertThat(actual.getAddress().getHouseNumber()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasAddressApartmentNumberEqualTo(String expected) {
        Assertions.assertThat(actual.getAddress().getApartmentNumber()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasAddressCityEqualTo(String expected) {
        Assertions.assertThat(actual.getAddress().getCity()).isEqualTo(expected);
        return this;
    }

    public ApartmentAssertion hasAddressCountryEqualTo(String expected) {
        Assertions.assertThat(actual.getAddress().getCountry()).isEqualTo(expected);
        return this;
    }
}
