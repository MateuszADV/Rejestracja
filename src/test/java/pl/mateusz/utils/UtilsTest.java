package pl.mateusz.utils;

import org.junit.Test;
import pl.mateusz.Constans;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testCheckpassword() {
        Pattern pattern = Pattern.compile(Constans.REGEX_PASSWORD);
        assertTrue(pattern.matcher("1hAdf@ssg").matches());
        assertTrue(pattern.matcher("9&sdeooP").matches());
        assertTrue(pattern.matcher("@werT3dfht").matches());
        assertTrue(pattern.matcher("qw8er1&T").matches());
        assertTrue(pattern.matcher("2@dysanW").matches());
        assertTrue(pattern.matcher("2$d&*ysanW").matches());
        assertTrue(pattern.matcher("2%()Erty").matches());
        assertFalse(pattern.matcher("Asde1fdw").matches());

    }

    @Test
    public void checkPassword() {
        assertEquals("1hAdf@ssg","1hAdf@ssg", Utils.checkPassword("1hAdf@ssg"));
    }

    @Test
    public void checkPhone() {
        Pattern pattern = Pattern.compile(Constans.REGEX_PHONE);
        assertTrue(pattern.matcher("123-125-256").matches());
        assertTrue(pattern.matcher("256-698-698").matches());
        assertFalse(pattern.matcher("256-698-6985").matches());
    }

    @Test
    public void checkLoginLength() {
        assertEquals("as", false, Utils.checkLoginLength("as"));
        System.out.println(Utils.checkLoginLength("as"));
        assertEquals("asa", true, Utils.checkLoginLength("asa"));
        System.out.println(Utils.checkLoginLength("asa"));
    }

    @Test
    public void checkLoginExist() {
        assertEquals("ADA", true, Utils.checkLoginExist("ADA"));
        System.out.println(Utils.checkLoginExist("ADA"));
        assertEquals("ADAs", false, Utils.checkLoginExist("ADAs"));
        System.out.println(Utils.checkLoginExist("ADAs"));
    }

    @Test
    public void testCheckEmail() {
        Pattern pattern = Pattern.compile(Constans.REGEX_MAIL);
        assertTrue(pattern.matcher("mateusz@wp.pl").matches());
        assertTrue(pattern.matcher("mateusz@interia.pl").matches());
        assertFalse(pattern.matcher("mateuęsz@interia.pl").matches());
    }
}