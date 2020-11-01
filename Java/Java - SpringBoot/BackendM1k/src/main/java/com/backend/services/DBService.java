package com.backend.services;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

import com.backend.entity.users.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.entity.address.Address;
import com.backend.entity.address.City;
import com.backend.entity.address.Country;
import com.backend.entity.address.State;
import com.backend.entity.address.enums.TypeAddress;
import com.backend.entity.contact.enums.TypePhone;
import com.backend.entity.contacts.Contact;
import com.backend.entity.contacts.Email;
import com.backend.entity.contacts.Phone;
import com.backend.entity.users.Admin;
import com.backend.entity.users.HapvidaUser;
import com.backend.entity.users.Instructor;
import com.backend.entity.users.enums.TypeUser;
import com.backend.repositories.AddressRepositorie;
import com.backend.repositories.ApplicationUserRepositorie;
import com.backend.repositories.CityRepositorie;
import com.backend.repositories.ContactRepositorie;
import com.backend.repositories.CountryRepositorie;
import com.backend.repositories.EmailRepositorie;
import com.backend.repositories.PhoneRepositorie;
import com.backend.repositories.StateRepositorie;

@Service
public class DBService {

	@Autowired
	private ContactRepositorie contactRepositorie;

	@Autowired
	private EmailRepositorie emailRepositorie;

	@Autowired
	private PhoneRepositorie phoneRepositorie;

	@Autowired
	private ApplicationUserRepositorie applicationUserRepositorie;

	@Autowired
	private CountryRepositorie countryRepositories;

	@Autowired
	private CityRepositorie cityRepositorie;

	@Autowired
	private StateRepositorie stateRepositorie;

	@Autowired 
	private AddressRepositorie addressRepositorie;

	@Autowired
	private BCryptPasswordEncoder bp;

	public void instantiateTestDatabase() throws ParseException {
		Country countryA = new Country("Brasil");
		Country countryB = new Country("Canadá");
		Country countryC = new Country("França");

		State brasilA = new State("Ceará", countryA);
		State brasilB = new State("Rio Grande do Norte", countryA);
		State canadaA = new State("Manitoba", countryB);
		State canadaB = new State("Ontário", countryB);

		City cearaA = new City("Fortaleza", brasilA);
		City cearaB = new City("Caucaia", brasilA);
		City riograndedonorteA = new City("Natal", brasilB);
		City riograndedonorteB = new City("Mossoró", brasilB);

		Address addressA = new Address("Nome da Rua 001", "123", "Complemento 001", "Bairro 001", "123454",
				"References 001", TypeAddress.RESIDENTIAL, cearaA, brasilA, countryA);

		Address addressB = new Address("Nome da Rua 002", "123", "Complemento 002", "Bairro 002", "741852",
				"References 002", TypeAddress.BUSINNESS, riograndedonorteA, brasilA, countryA);

		Address addressC = new Address("Nome da Rua 003", "123", "Complemento 003", "Bairro 003", "852963",
				"References 003", TypeAddress.UNDEFINED, cearaB, brasilA, countryA);

		countryRepositories.saveAll(Arrays.asList(countryA, countryB, countryC));
		stateRepositorie.saveAll(Arrays.asList(brasilA, brasilB, canadaA, canadaB));
		cityRepositorie.saveAll(Arrays.asList(cearaA, cearaB, riograndedonorteA, riograndedonorteB));
		addressRepositorie.saveAll(Arrays.asList(addressA, addressB, addressC));

		Contact contactA = new Contact();
		Contact contactB = new Contact();
		Contact contactC = new Contact();

		Phone phoneA = new Phone(TypePhone.CELULAR, "000", "000", "000000000", new Date(System.currentTimeMillis()),
				contactA);

		Phone phoneB = new Phone(TypePhone.HOME, "111", "111", "111111111", new Date(System.currentTimeMillis()),
				contactB);

		Phone phoneC = new Phone(TypePhone.HOME, "333", "333", "333333333", new Date(System.currentTimeMillis()),
				contactC);

		Phone phoneD = new Phone(TypePhone.CELULAR, "444", "444", "444444444", new Date(System.currentTimeMillis()),
				contactC);

		Email emailA = new Email("emailA@email.com", new Date(System.currentTimeMillis()), contactA,true);
		Email emailB = new Email("emailB@email.com", new Date(System.currentTimeMillis()), contactB,true);
		Email emailC = new Email("emailC@email.com", new Date(System.currentTimeMillis()), contactC,true);
		Email emailD = new Email("emailD@email.com", new Date(System.currentTimeMillis()), contactC,true);

		ApplicationUser hapvidaUser = new HapvidaUser("Hapvida User 001", "1234567899",
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "1234567891478", "SITE",
				1.88, 1.88,bp.encode("123456"));

		ApplicationUser admin = new Admin("Admin User001", "98765432111", TypeUser.INSTRUCTOR,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),bp.encode("741852"));

		ApplicationUser instructor = new Instructor("Instructor 003", "14725836988", TypeUser.INSTRUCTOR,
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),bp.encode("123456"));

		contactA.getPhones().add(phoneA);
		contactA.getEmails().add(emailA);
		hapvidaUser.setContact(contactA);
		hapvidaUser.setAddress(addressA);

		contactB.getPhones().add(phoneB);
		contactB.getEmails().add(emailB);
		admin.setContact(contactB);
		admin.setAddress(addressB);

		contactC.getPhones().addAll(Arrays.asList(phoneC, phoneD));
		contactC.getEmails().addAll(Arrays.asList(emailC, emailD));
		instructor.setContact(contactC);
		instructor.setAddress(addressC);

		contactRepositorie.saveAll(Arrays.asList(contactA, contactB, contactC));

		phoneRepositorie.saveAll(Arrays.asList(phoneA, phoneB, phoneC, phoneD));
		emailRepositorie.saveAll(Arrays.asList(emailA, emailB, emailC, emailD));

		applicationUserRepositorie.saveAll(Arrays.asList(hapvidaUser, admin, instructor));

	}

}
