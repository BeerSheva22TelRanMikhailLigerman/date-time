package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	@Disabled
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM, YYYY, d", Locale.forLanguageTag("en"));
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.WEEKS;
		System.out.printf("Number of %s between %s and %s is %d", 
				unit, birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));		
	}
	
	@Test
	@Disabled
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		System.out.println(current.with(new BarMizvaAdjuster()));
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}
	
	//HW31
	//display current local date and time for all Canada time zones with time zone name
	@Test	
	@Disabled
	void displayCurrentDateTimeCanadaTimeZone() {		
		ZoneId.getAvailableZoneIds().stream().filter(x -> x.contains("Canada"))
		.forEach(x -> System.out.println(x + " " + LocalDateTime.now(ZoneId.of(x)).format(DateTimeFormatter.ofPattern("YYYY-M-d h:mm a"))));
		
	}
	@Test
	@Disabled
	void nextFriday13Test() {
		TemporalAdjuster nextFriday13 = new NextFriday13();
		assertEquals(LocalDate.of(2023, 1, 13), LocalDate.of(2023, 1, 12).with(new NextFriday13()));
		assertEquals(LocalDate.of(2023, 10, 13), LocalDate.of(2023, 1, 14).with(nextFriday13));
	}
	@Test
	void WorkingDaysTest() {		
		assertEquals(LocalDate.of(2023, 3, 15), LocalDate.of(2023, 3, 12).with(new WorkingDays(new DayOfWeek[] {DayOfWeek.SATURDAY}, 3)));
		assertEquals(LocalDate.of(2023, 3, 19), LocalDate.of(2023, 3, 12).with(new WorkingDays(new DayOfWeek[] {DayOfWeek.SATURDAY}, 6)));
		}

}
