package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.dto.ConcertBaseDto;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.ConcertUpdateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.QueryParam;

import java.util.List;

public class ConcertService {
    private final static ConcertDao concertDao = new ConcertDao();

    private  Concert fromDtoToConcertMapper(ConcertBaseDto concertCreateDto,Concert concert)
    {
        concert.setName(concertCreateDto.getName());
        concert.setDescription(concertCreateDto.getDescription());
        concert.setLocation(concertCreateDto.getLocation());
        concert.setPrice(concertCreateDto.getPrice());
        concert.setPopularity(concertCreateDto.getPopularity());
        concert.setPlaceNumber(concertCreateDto.getPlaceNumber());
        concert.setMusicalGenre(concertCreateDto.getMusicalGenre());
        return concert;
    }

    public List<Concert> getConcerts() {

        return concertDao.findAll();
    }
    public Concert getConcert(Long id) {
        return concertDao.findOne(id);
    }

    public Concert createConcert(ConcertCreateDto concertCreateDto) {
        Concert concert = fromDtoToConcertMapper(concertCreateDto, new Concert());
        concertDao.save(concert);
        return concert;
    }

    public Concert updateConcert( long id, ConcertUpdateDto concertUpdateDto) {
        if (!(id == concertUpdateDto.getId())) {
            throw new BadRequestException("Concert non accessible");
        }
        Concert concert = fromDtoToConcertMapper(concertUpdateDto,getConcert(id));
        concertDao.update(concert);
        return concert;
    }

    public List<Concert> searchConcerts(String searchQ) {
//        System.out.println(searchQ);
        return  concertDao.searchConcerts(searchQ);
    }

    public void deleteConcert(Long id) {
        concertDao.deleteById(id);
    }

    public List<Concert> findByLocation(String location) {
        return concertDao.findByLocation(location);
    }

    public List<Concert> findValidated() {
        return concertDao.findValidated();
    }

    public List<Concert> findByMaxPrice(Long maxPrice) {
        return concertDao.findByMaxPrice(maxPrice);
    }
}
