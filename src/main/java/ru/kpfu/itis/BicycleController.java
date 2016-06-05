package ru.kpfu.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bicycles")
public class BicycleController {
    @Autowired
    public BicycleService bikeService;
    @RequestMapping(value = "/add/{serial}")
    public Bicycle addBicycle(@PathVariable String serial) {
        Bicycle bike = new Bicycle();
        bike.setSerialNumber(serial);
        bikeService.saveBicycle(bike);
        return bike;
    }
    /*
    @RequestMapping(value = "/delete/{id}")
    public void deleteBook(@PathVariable int id) {
        Book book = new Book();
        book.setId(id);
        bookService.delete(id);
    }
    */
    @RequestMapping(value = "/")
    public List<Bicycle> getBicycles() {
        return bikeService.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Bicycle getBicycle(@PathVariable long id) {
        Bicycle bike = bikeService.findOne(id);
        return bike;
    }
    @RequestMapping(value = "/search/serial/{serial}")
    public List<Bicycle> getBookBySerialNumber(@PathVariable String serial) {
        List<Bicycle> bikes = bikeService.findBySerialNumber(serial);
        return bikes;
    }

    /*
    @RequestMapping(value = "/search/price/{price}")
    public List<Book> getBookByPrice(@PathVariable int price) {
        List<Book> books = bookService.findByPrice(price);
        return books;
    }
    @RequestMapping(value = "/search/{name}/{author}")
    public List<Book> getBookByNameAndAuthor(@PathVariable String name, @PathVariable String author) {
        List<Book> books = bookService.findByNameAndAuthor(name, author);
        return books;
    }
    */
}