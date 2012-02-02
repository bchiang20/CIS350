package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;


public class GPXcalculatorTest {

	@Test
	//checks output equals -1 for null GPXtrk
	public void testNullGPXtrk() {
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(null),0);
	}
	
	@Test
	//checks output equals -1 for GPXtrk with no objects
	public void testNoObjects(){
		GPXtrk testTrk = new GPXtrk("Test", new ArrayList<GPXtrkseg>());
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for GPXtrk with null GPXtrkseg
	public void testNullTrkSeg(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		testArray.add(null);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for GPXtrkseg with no objects
	public void testEmptyTrkSeg(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		GPXtrkseg testTrkSeg = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for GPXtrkseg with one object
	public void testSingleTrkPt(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(1.0,1.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for GPXtrkpt that is null
	public void testNullTrkPt(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(1.0,1.0,new Date()));
		trkptArray.add(null);
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for latitude > 90
	public void testTrkPtLatGreaterThan90(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(91.0,1.0,new Date()));
		trkptArray.add(new GPXtrkpt(50.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for latitude < -90
	public void testTrkPtLatLessThanNeg90(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(-91.0,1.0,new Date()));
		trkptArray.add(new GPXtrkpt(50.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for longitude > 180
	public void testTrkPtLonGreaterThan180(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(15.0,181.0,new Date()));
		trkptArray.add(new GPXtrkpt(50.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks output equals 0 for longitude < -180
	public void testTrkPtLonLessThanNeg180(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(15.0,-181.0,new Date()));
		trkptArray.add(new GPXtrkpt(50.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(testTrk),0);
	}
	
	@Test
	//checks correct distance calculation
	public void testCorrectDistance(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(5.0,-5.0,new Date()));
		trkptArray.add(new GPXtrkpt(-20.0,20.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(35.35,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}
	
	@Test
	//checks boundary condition
	public void testTrkPtLatEqual90(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(90.0,0.0,new Date()));
		trkptArray.add(new GPXtrkpt(-10.0,0.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(100,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}
	
	@Test
	//checks boundary condition
	public void testTrkPtLatEqualNeg90(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(-90.0,0.0,new Date()));
		trkptArray.add(new GPXtrkpt(-10.0,0.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(80,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}
	
	@Test
	//checks boundary condition
	public void testTrkPtLonEqual180(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(0.0,180.0,new Date()));
		trkptArray.add(new GPXtrkpt(0.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(130,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}
	
	@Test
	//checks boundary condition
	public void testTrkPtLonEqualNeg180(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(0.0,-180.0,new Date()));
		trkptArray.add(new GPXtrkpt(0.0,50.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		testArray.add(testTrkSeg);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(230,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}
	
	@Test
	//checks correct calculation of multiple segments
	public void testMultipleSegments(){
		ArrayList<GPXtrkseg> testArray = new ArrayList<GPXtrkseg>();
		ArrayList<GPXtrkpt> trkptArray = new ArrayList<GPXtrkpt>();
		trkptArray.add(new GPXtrkpt(5.0,-5.0,new Date()));
		trkptArray.add(new GPXtrkpt(-10.0,10.0,new Date()));
		GPXtrkseg testTrkSeg = new GPXtrkseg(trkptArray);
		
		ArrayList<GPXtrkpt> trkptArray2 = new ArrayList<GPXtrkpt>();
		trkptArray2.add(new GPXtrkpt(-20.0,20.0,new Date()));
		trkptArray2.add(new GPXtrkpt(-10.0,10.0,new Date()));
		GPXtrkseg testTrkSeg2 = new GPXtrkseg(trkptArray);
		
		testArray.add(testTrkSeg);
		testArray.add(testTrkSeg2);
		GPXtrk testTrk = new GPXtrk("Test", testArray);
		assertEquals(35.35,GPXcalculator.calculateDistanceTraveled(testTrk),0.01);
	}

}
