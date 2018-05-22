package nz.ac.auckland.unit;

import org.junit.Before;

import nz.ac.auckland.TravisTestObject;

import org.junit.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestJUnitWorks {
    
	private TravisTestObject _tto;
	
    @Before
    public void setUp(){
    	_tto = new TravisTestObject();
    }

    @Test
    public void calculate_popularity_of_a_category(){
        assertThat(true, equalTo(true));
    }
}
