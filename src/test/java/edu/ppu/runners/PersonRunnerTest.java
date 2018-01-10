package edu.ppu.runners;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import edu.ppu.domain.Person;

public class PersonRunnerTest {

	@Test
	public void testGetAgeCategory() {
		PersonRunner cut = new PersonRunner(null);
		int ageCategory = cut.getAgeCategory(70);
		assertEquals("wrong age category", 3, ageCategory);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAgeCategoryInvalidAge() {
		PersonRunner cut = new PersonRunner(null);
		cut.getAgeCategory(1000);
	}

	@Test
	public void testGetAverage() {

		PersonService mockPersonServiceImpl = mock(PersonService.class);
		when(mockPersonServiceImpl.getPersons(anyInt()))
				.thenReturn(Arrays.asList(new Person("child", 10), new Person("old", 70)));
		PersonRunner cut = new PersonRunner(mockPersonServiceImpl);
		double avg = cut.getAvg();
		assertEquals("invalid avg age", 40, avg, 0);
	}
	@Test
	public void testGetAgeCategoryOlder() {
		PersonRunner cut = new PersonRunner(null);
		int ageCategory = cut.getAgeCategory(50);
		assertEquals("wrong age category", 2, ageCategory);
	}
	@Test
	public void testGetAgeCategory123() {
		PersonRunner cut = new PersonRunner(null);
		int ageCategory = cut.getAgeCategory(10);
		assertEquals("wrong age category", 1, ageCategory);
	}
	@Test
	public void testGetAverages() {

		PersonService mockPersonServiceImpl = mock(PersonService.class);
		when(mockPersonServiceImpl.getPersons(anyInt()))
				.thenReturn(Arrays.asList(new Person("child", 100), new Person("old", 60)));
		PersonRunner cut = new PersonRunner(mockPersonServiceImpl);
		Person  max = cut.getOldestGuy();
		assertEquals("invalid avg age", new Person("child", 100), max);
	}
}
