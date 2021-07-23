package com.smalaca.apartmentsapp.apartment;

import com.smalaca.apartmentsapp.owner.OwnerId;
import org.junit.jupiter.api.Test;

class ApartmentServiceTest {
    private final ApartmentService service = null;

    @Test
    void shouldReturnIdOfExistingApartment() {
        OwnerId ownerId = givenExistingOwnerId();
        ApartmentDto apartmentDto = givenDtoForExistingApartment();

        ApartmentId actual = service.add(ownerId, apartmentDto);

        thenApartmentShouldNotBeCreated(actual);
        thenShouldReturnIdOffExistingApartment(actual);
    }

    private ApartmentDto givenDtoForExistingApartment() {
        return null;
    }

    private void thenShouldReturnIdOffExistingApartment(ApartmentId actual) {

    }

    @Test
    void shouldCreateApartment() {
        OwnerId ownerId = givenExistingOwnerId();
        ApartmentDto apartmentDto = givenValidApartmentDto();

        ApartmentId actual = service.add(ownerId, apartmentDto);

        thenApartmentShouldBeCreated(actual, apartmentDto);
    }

    private void thenApartmentShouldBeCreated(ApartmentId actual, ApartmentDto apartmentDto) {

    }

    @Test
    void shouldRecognizeAddressIsInvalid() {
        OwnerId ownerId = givenExistingOwnerId();
        ApartmentDto apartmentDto = givenInvalidValidApartmentDto();

        ApartmentId actual = service.add(ownerId, apartmentDto);

        thenApartmentShouldNotBeCreated(actual);
        thenInvalidAddressRecognized(apartmentDto);
    }

    private OwnerId givenExistingOwnerId() {
        return null;
    }

    private ApartmentDto givenInvalidValidApartmentDto() {
        return null;
    }

    private void thenInvalidAddressRecognized(ApartmentDto apartmentDto) {

    }

    @Test
    void shouldRecognizeOwnerExist() {
        OwnerId ownerId = givenNotExistingOwnerId();
        ApartmentDto apartmentDto = givenValidApartmentDto();

        ApartmentId actual = service.add(ownerId, apartmentDto);

        thenApartmentShouldNotBeCreated(actual);
        thenOwnerNotFoundRecognized(ownerId);
    }

    private OwnerId givenNotExistingOwnerId() {
        return null;
    }

    private ApartmentDto givenValidApartmentDto() {
        return null;
    }

    private void thenApartmentShouldNotBeCreated(ApartmentId actual) {

    }

    private void thenOwnerNotFoundRecognized(OwnerId ownerId) {

    }
}

