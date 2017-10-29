package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
@RequestMapping("/api")
class ReservationController {

	public static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	@Autowired
	ReservationService reservationService;

	// ------------------Retrieve all reservations----------------//
	@RequestMapping("/reservation/")
	public ResponseEntity<List<Reservation>> listAllReservations() {
		logger.debug("Fetching all the reservations");
		List<Reservation> listReserv = reservationService.findAllReservations();

		if (listReserv.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Reservation>>(listReserv, HttpStatus.OK);
	}

	// -----------Retrieve single reservation ---------------------//

	@SuppressWarnings("unchecked")
	@RequestMapping("/reservation/{id}")
	public ResponseEntity<?> getReservation(@PathVariable("id") long id) {
		logger.debug("Fetching reservations with {id}:" + id);
		Reservation reservation = reservationService.findById(id);

		if (reservation == null) {
			return new ResponseEntity(new CustomErrorType("Reservation with id " + id + " not found"),
					HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);

	}

	// -------------------Create reservation-----------------------

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reservation/", method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody Reservation reservation, UriComponentsBuilder ucBuilder) {
		logger.debug("creating reservation");
		if (reservationService.isReservationExist(reservation)) {
			return new ResponseEntity(new CustomErrorType("Reservation with the name already exists"),
					HttpStatus.CONFLICT);
		}
		reservationService.saveUser(reservation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/reservation/{id}").buildAndExpand(reservation.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Reservation reservation) {
		logger.debug("Updating reservations with {id}:" + id);
		Reservation currReservation = reservationService.findById(id);

		if (currReservation == null) {
			return new ResponseEntity(new CustomErrorType("Reservation with id " + id + "does not exists"),
					HttpStatus.NOT_FOUND);
		}

		currReservation.setReservationName(reservation.getReservationName());

		reservationService.updateReservation(currReservation);

		return new ResponseEntity<Reservation>(currReservation, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reservation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteReservation(@PathVariable("id") long id) {
		logger.debug("Deleting reservations with {id}:" + id);
		Reservation reservation = reservationService.findById(id);

		if (reservation == null) {
			return new ResponseEntity(new CustomErrorType("Reservation with id " + id + "does not exists"),
					HttpStatus.NO_CONTENT);
		}

		reservationService.deleteReservationById(id);

		return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(value = "/reservation/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllReservation() {

		logger.debug("Delete all the reservations");
		reservationService.deleteAllUsers();
		return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
	}
}

class CustomErrorType {

	private String errorMessage;

	public CustomErrorType(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

interface ReservationService {

	Reservation findByReservationName(String reservationName);

	Reservation findById(Long id);

	void saveUser(Reservation reservation);

	void updateReservation(Reservation reservation);

	void deleteReservationById(Long id);

	void deleteAllUsers();

	List<Reservation> findAllReservations();

	boolean isReservationExist(Reservation reservation);
}

@Service("reservationService")
@Transactional
class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation findById(Long Id) {
		return reservationRepository.findOne(Id);
	}

	@Override
	public void saveUser(Reservation reservation) {
		reservationRepository.save(reservation);
	}

	@Override
	public void updateReservation(Reservation reservation) {
		saveUser(reservation);
	}

	@Override
	public void deleteReservationById(Long id) {
		reservationRepository.delete(id);
	}

	@Override
	public void deleteAllUsers() {
		reservationRepository.deleteAll();
	}

	@Override
	public List<Reservation> findAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public boolean isReservationExist(Reservation reservation) {
		return findByReservationName(reservation.getReservationName()) != null;
	}

	@Override
	public Reservation findByReservationName(String reservationName) {
		return reservationRepository.findByReservationName(reservationName);
	}

}

@Repository("reservationRepository")
interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Reservation findByReservationName(String reservationName);
}

@Entity
class Reservation {
	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;

	public Reservation() {
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
}