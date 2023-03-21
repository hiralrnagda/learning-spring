package com.hiral.learningspring.webservice;

import com.hiral.learningspring.business.ReservationService;
import com.hiral.learningspring.business.RoomReservation;
import com.hiral.learningspring.data.Guest;
import com.hiral.learningspring.data.Room;
import com.hiral.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false)String dateString){
        Date date = this.dateUtils.createDateFromString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @RequestMapping(value = "/guests", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest){
        if(guest == null) throw new RuntimeException("Guest cannot be null");
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }
}
