import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class JDBCTest {
    Airport airport;
    @Before
    public void setup(){
        airport = new Airport("Palangos oro uostas", "Naujininkai", "Vilnius");
    }
    @Test
    public void createEntryTest(){
        //AirportDAO.create(airport);
        ArrayList<Airport> airports = AirportDAO.searchByName("Palangos oro uostas");
        compareObjects(airport, airports.get(0));
    }
    @Test
    public void deleteEntryByIdTest(){
        AirportDAO.deleteById(8);
        Airport airportActual = AirportDAO.searchByID(8);
        Assert.assertEquals(null, airportActual);

    }

    private void compareObjects(Airport expected, Airport actual){
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertEquals(expected.getAddress(),actual.getAddress());
        Assert.assertEquals(expected.getCity(),actual.getCity());
    }
}
