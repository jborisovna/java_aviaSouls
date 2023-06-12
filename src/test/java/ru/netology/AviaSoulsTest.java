package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {

    AviaSouls aviaSouls = new AviaSouls();

    Ticket t1 = new Ticket("Houston", "Seattle", 50_000, 17, 23);
    Ticket t2 = new Ticket("Chicago", "Dallas", 28_000, 7, 10);
    Ticket t3 = new Ticket("New York", "Boston", 38_000, 5, 7);
    Ticket t4 = new Ticket("New York", "Boston", 18_000, 8, 9);
    Ticket t5 = new Ticket("New York", "Boston", 28_000, 23, 2);

    @BeforeEach
    public void setup() {
        aviaSouls.add(t1);
        aviaSouls.add(t2);
        aviaSouls.add(t3);
        aviaSouls.add(t4);
        aviaSouls.add(t5);
    }

    @Test
    public void shouldFindAllAndSortByPrice() {
        Ticket[] tickets = aviaSouls.findAll();
        Arrays.sort(tickets);

        Ticket[] expected = {t4, t2, t5, t3, t1};
        Ticket[] actual = aviaSouls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByCityAndSortByPrice() {
        Ticket[] expected = {t4, t5, t3};
        Ticket[] actual = aviaSouls.search("New York", "Boston");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicket() {
        Ticket[] expected = {t2};
        Ticket[] actual = aviaSouls.search("Chicago", "Dallas");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicket() {
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Moscow", "Rome");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareByPrice() {
        Assertions.assertEquals(1, t1.compareTo(t2));

        Assertions.assertEquals(-1, t4.compareTo(t5));

        Assertions.assertEquals(0, t2.compareTo(t5));
    }

    @Test
    public void shouldCompareByTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Assertions.assertEquals(-1, timeComparator.compare(t4, t1));

        Assertions.assertEquals(1, timeComparator.compare(t1, t3));

        Assertions.assertEquals(0,timeComparator.compare(t2, t5));
    }

    @Test
    public void shouldFindByCityAndSortByTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Ticket[] expected = {t4, t3, t5};
        Ticket[] actual = aviaSouls.searchAndSortBy("New York", "Boston", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
