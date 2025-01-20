import com.rasrov.kairos.adapter.PriceServiceAdapter;
import com.rasrov.kairos.domain.PriceResponse;
import com.rasrov.kairos.entity.BrandEntity;
import com.rasrov.kairos.entity.PriceEntity;
import com.rasrov.kairos.entity.PriceListEntity;
import com.rasrov.kairos.entity.ProductEntity;
import com.rasrov.kairos.serviceport.PriceServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        PriceServiceAdapter.class
})
public class PriceServiceAdapterTest {

    @Autowired
    private PriceServiceAdapter priceServiceAdapter;

    @MockitoBean
    private PriceServicePort priceServicePort;

    @Test
    void should_be_create_service_with_not_null_dependencies() {
        assertAll(
                "All dependencies are injected correctly",
                () -> assertThat(priceServiceAdapter).isNotNull(),
                () -> assertThat(priceServicePort).isNotNull()
        );
    }

    @Test
    void should_return_empty_prices_but_with_a_warning_message() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime dateTime = LocalDateTime.now();

        given(priceServicePort.findPrices(eq(brandId), eq(productId), eq(dateTime))).willReturn(Set.of());

        final PriceResponse price = priceServiceAdapter.findPrice(brandId, productId, dateTime);

        assertAll(
                "Assert error message not empty",
                () -> assertThat(price.productId()).isNull(),
                () -> assertThat(price.brandId()).isNull(),
                () -> assertThat(price.fee()).isNull(),
                () -> assertThat(price.effectiveDates()).isNull(),
                () -> assertThat(price.price()).isNull(),
                () -> assertThat(price.error()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(price.error()).message()).isNotEmpty(),
                () -> assertThat(Objects.requireNonNull(price.error()).message()).contains("Not found any active price with")
        );
    }

    @Test
    void should_return_the_highest_priority_active_price() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = startDate.plusDays(1);

        final Integer fee = 5;
        final Double priceAmount = 10.0;

        final BrandEntity brandEntity = new BrandEntity(1, "ZARA");
        final ProductEntity productEntity = new ProductEntity(333, "ABRIGO");

        final PriceEntity firstPriceEntity = new PriceEntity(1, brandEntity, startDate, endDate, new PriceListEntity(1, fee), 0, priceAmount, productEntity, "EUR");
        final PriceEntity secondPriceEntity = new PriceEntity(1, brandEntity, startDate, startDate.plusDays(2), new PriceListEntity(1, 10), 1, 20.0, productEntity, "EUR");
        final PriceEntity thirdPriceEntity = new PriceEntity(1, brandEntity, startDate, startDate.plusDays(3), new PriceListEntity(1, 15), 2, 30.0, productEntity, "EUR");

        given(priceServicePort.findPrices(eq(brandId), eq(productId), eq(startDate))).willReturn(Set.of(firstPriceEntity, secondPriceEntity, thirdPriceEntity));

        final PriceResponse price = priceServiceAdapter.findPrice(brandId, productId, startDate);

        assertAll(
                "Assert highest price data",
                () -> assertThat(price.productId()).isEqualTo(productId),
                () -> assertThat(price.brandId()).isEqualTo(brandId),
                () -> assertThat(price.fee()).isEqualTo(15),
                () -> assertThat(price.effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(price.effectiveDates()).startDate()).isEqualTo(startDate),
                () -> assertThat(Objects.requireNonNull(price.effectiveDates()).endDate()).isEqualTo(startDate.plusDays(3)),
                () -> assertThat(price.price()).isEqualTo(34.5),
                () -> assertThat(price.error()).isNull()
        );
    }

}
