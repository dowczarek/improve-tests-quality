package com.smalaca.apartmentsapp.services.apartment;

import com.smalaca.apartmentsapp.address.AddressCatalogue;
import com.smalaca.apartmentsapp.apartment.*;
import com.smalaca.apartmentsapp.events.EventRegistry;
import com.smalaca.apartmentsapp.events.InvalidAddressRecognized;
import com.smalaca.apartmentsapp.events.InvalidAddressRecognizedAssertion;
import com.smalaca.apartmentsapp.events.OwnerNotFound;
import com.smalaca.apartmentsapp.owner.GivenOwner;
import com.smalaca.apartmentsapp.owner.OwnerId;
import com.smalaca.apartmentsapp.owner.OwnerRepository;
import com.smalaca.apartmentsapp.assertions.AssertionsFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.smalaca.apartmentsapp.address.AddressTestFactory.APARTMENT_NUMBER;
import static com.smalaca.apartmentsapp.address.AddressTestFactory.CITY;
import static com.smalaca.apartmentsapp.address.AddressTestFactory.COUNTRY;
import static com.smalaca.apartmentsapp.address.AddressTestFactory.HOUSE_NUMBER;
import static com.smalaca.apartmentsapp.address.AddressTestFactory.STREET;
import static com.smalaca.apartmentsapp.integrationtest.address.AddressContract.INVALID_APARTMENT_NUMBER;
import static com.smalaca.apartmentsapp.integrationtest.address.AddressContract.INVALID_HOUSE_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class ApartmentServiceTest {
    private final OwnerRepository ownerRepository = mock(OwnerRepository.class);
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final AddressCatalogue addressCatalogue = mock(AddressCatalogue.class);
    private final EventRegistry eventRegistry = mock(EventRegistry.class);
    private final ApartmentService service = new ApartmentService(ownerRepository, apartmentRepository, addressCatalogue, eventRegistry);

    private final GivenOwner givenOwner = new GivenOwner(ownerRepository);
    private final GivenApartment givenApartment = new GivenApartment(addressCatalogue, apartmentRepository);

    @Test
    void shouldNotAddApartmentWhenOwnerDoesNotExist() {
        OwnerId ownerId = givenOwner.notExisting();
        ApartmentDto apartmentDto = givenApartment.validDtoForNotExisting();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        thenNoIdReturn(apartmentId);
        thenApartmentWasNotCreated();
        thenOwnerNotFoundRecognized(ownerId);
    }

    @Test
    void shouldRecognizeInvalidAddress() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.invalidDto();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        thenNoIdReturn(apartmentId);
        thenApartmentWasNotCreated();
        InvalidAddressRecognized actual = thenInvalidAddressRecognized();
        AssertionsFacade.assertThat(actual)
                .hasStreetEqualTo(STREET)
                .hasHouseNumberEqualTo(INVALID_HOUSE_NUMBER)
                .hasApartmentNumberEqualTo(INVALID_APARTMENT_NUMBER)
                .hasCityEqualTo(CITY)
                .hasCountryEqualTo(COUNTRY);
    }

    @Test
    void shouldReturnIdOfExistingApartment() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.dtoForExisting(ownerId);

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        thenApartmentWasNotCreated();
        assertThat(apartmentId)
                .isNotEqualTo(ApartmentId.nullObject())
                .isNotNull();
    }

    @Test
    void shouldCreateNewApartment() {
        OwnerId ownerId = givenOwner.existing();
        ApartmentDto apartmentDto = givenApartment.validDtoForNotExisting();

        ApartmentId apartmentId = service.add(ownerId, apartmentDto);

        assertThat(apartmentId).isNotEqualTo(ApartmentId.nullObject());
        Apartment actual = thenApartmentCreated();
        AssertionsFacade.assertThat(actual)
                .hasIdEqualTo(apartmentId)
                .hasOwnerIdEqualTo(ownerId)
                .hasAddressStreetEqualTo(STREET)
                .hasAddressHouseNumberEqualTo(HOUSE_NUMBER)
                .hasAddressApartmentNumberEqualTo(APARTMENT_NUMBER)
                .hasAddressCityEqualTo(CITY)
                .hasAddressCountryEqualTo(COUNTRY);
    }

    private void thenNoIdReturn(ApartmentId apartmentId) {
        assertThat(apartmentId).isEqualTo(ApartmentId.nullObject());
    }

    private void thenApartmentWasNotCreated() {
        then(apartmentRepository).should(never()).save(any());
    }

    private void thenOwnerNotFoundRecognized(OwnerId ownerId) {
        ArgumentCaptor<OwnerNotFound> captor = ArgumentCaptor.forClass(OwnerNotFound.class);
        then(eventRegistry).should().publish(captor.capture());
        assertThat(captor.getValue().getOwnerId()).isEqualTo(ownerId);
    }

    private InvalidAddressRecognized thenInvalidAddressRecognized() {
        ArgumentCaptor<InvalidAddressRecognized> captor = ArgumentCaptor.forClass(InvalidAddressRecognized.class);
        then(eventRegistry).should().publish(captor.capture());

        return captor.getValue();
    }

    private Apartment thenApartmentCreated() {
        ArgumentCaptor<Apartment> captor = ArgumentCaptor.forClass(Apartment.class);
        then(apartmentRepository).should().save(captor.capture());

        return captor.getValue();
    }
}