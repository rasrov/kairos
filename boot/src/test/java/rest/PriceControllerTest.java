package rest;

import com.rasrov.kairos.PriceApiApplication;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PriceApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void handle_request_brand_id_parameter_not_found_exception() throws Exception {
        final Integer productId = 35455;
        final LocalDateTime startDate = LocalDateTime.now();

        mockMvc.perform(get("/price")
                        .param("productId", String.valueOf(productId))
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message").value("Required request parameter 'brandId' for method parameter type Integer is not present"));
    }

    @Test
    void handle_request_product_id_parameter_not_found_exception() throws Exception {
        final Integer brandId = 1;
        final LocalDateTime startDate = LocalDateTime.now();

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message").value("Required request parameter 'productId' for method parameter type Integer is not present"));
    }

    @Test
    void handle_request_start_date_parameter_not_found_exception() throws Exception {
        final Integer brandId = 1;
        final Integer productId = 35455;

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message").value("Required request parameter 'startDate' for method parameter type LocalDateTime is not present"));
    }

    @Test
    void handle_request_brand_id_miss_match_type_exception() throws Exception {
        final Integer productId = 35455;
        final LocalDateTime startDate = LocalDateTime.now();

        mockMvc.perform(get("/price")
                        .param("brandId", "a")
                        .param("productId", String.valueOf(productId))
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message", Matchers.containsString("Method parameter 'brandId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'")));
    }

    @Test
    void handle_request_product_id_miss_match_type_exception() throws Exception {
        final Integer brandId = 1;
        final LocalDateTime startDate = LocalDateTime.now();

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", "a")
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message", Matchers.containsString("Method parameter 'productId': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'")));
    }

    @Test
    void handle_request_start_date_miss_match_type_exception() throws Exception {
        final Integer brandId = 1;
        final Integer productId = 35455;

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("startDate", "a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.error.message", Matchers.containsString("Method parameter 'startDate': Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDateTime'")));
    }

    @Test
    void handle_error_response_when_data_not_found() throws Exception {
        final Integer brandId = 1;
        final Integer productId = 35455;
        final LocalDateTime startDate = LocalDateTime.now();

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.error.message").value(String.format("Not found any active price with brand id %s, product id %s and %s date", brandId, productId, startDate)));
    }

    @Test
    void should_return_found_price() throws Exception {
        final Integer brandId = 1;
        final Integer productId = 35455;
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        mockMvc.perform(get("/price")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("startDate", String.valueOf(startDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productId").value(String.valueOf(productId)))
                .andExpect(jsonPath("$.brandId").value(String.valueOf(brandId)))
                .andExpect(jsonPath("$.fee").value("10"))
                .andExpect(jsonPath("$.effectiveDates.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.effectiveDates.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.price").value("27.995"))
                .andExpect(jsonPath("$.error").isEmpty());
    }
}
