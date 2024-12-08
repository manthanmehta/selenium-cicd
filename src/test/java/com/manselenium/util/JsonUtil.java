//package com.manselenium.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.manselenium.tests.vendorportal.model.VendorPortalTestData;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class JsonUtil {
//    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
//    private static final ObjectMapper mapper = new ObjectMapper();
//
//    public static VendorPortalTestData getTestData(String path) throws IOException {
//        try (InputStream stream = ResourceLoader.getResource(path)) {
//            return mapper.readValue(stream, VendorPortalTestData.class);
//        } catch (Exception e) {
//            log.error("Unable to read test data: {}", path);
//
//        }
//        return null;
//    }
//}
//
//
//
//

package com.manselenium.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manselenium.tests.flightreservation.model.FlightReservationTestData;
import com.manselenium.tests.vendorportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

//    public static VendorPortalTestData getTestData(String path) throws IOException {
//        try (InputStream stream = ResourceLoader.getResource(path)) {
//            log.info("Successfully loaded test data from path: {}", path);
//            return mapper.readValue(stream, VendorPortalTestData.class);
//        } catch (IOException e) {
//            log.error("Unable to read or parse test data from path: {}", path, e);
//            throw e; // Re-throwing ensures calling code knows the method failed.
//        } catch (Exception e) {
//            log.error("Unexpected error while reading test data from path: {}", path, e);
//            throw new RuntimeException("Failed to load test data", e); // Wrap in a runtime exception for unexpected errors.
//        }
//    }

    public static <T>T getTestData(String path,Class<T> type) throws IOException {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            log.info("Successfully loaded test data from path: {}", path);
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            log.error("Unable to read or parse test data from path: {}", path, e);
            throw e; // Re-throwing ensures calling code knows the method failed.
        } catch (Exception e) {
            log.error("Unexpected error while reading test data from path: {}", path, e);
            throw new RuntimeException("Failed to load test data", e); // Wrap in a runtime exception for unexpected errors.
        }
    }
}

