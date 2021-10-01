package com.tsp.TSPApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TspApiApplicationTests {

    @Test
    public void Main_HappyPath_FINALDISTANCEIsNonZeroAnInteger(){

        // Act
        TspApiApplication.main(new String[1]);

        // Assert
        assertNotNull(TspApiApplication.FINAL_DISTANCE);
        assertTrue(TspApiApplication.FINAL_DISTANCE > 0);
    }
}
