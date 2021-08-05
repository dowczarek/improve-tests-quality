package com.smalaca.apartmentsapp.integrationtest.address;

import com.smalaca.apartmentsapp.address.Address;

import java.util.Optional;

public class AddressContractScenario {
    private final AddressContractGiven given;
    private final Optional<Address> expected;

    AddressContractScenario(AddressContractGiven given, Optional<Address> expected) {
        this.given = given;
        this.expected = expected;
    }

    AddressContractGiven given() {
        return given;
    }

    Optional<Address> expected() {
        return expected;
    }
}
