package ServiceTest;

import com.ione.entity.Driver;
import com.ione.repository.DriverRepository;
import com.ione.service.DriverServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver buildDriver(Integer id, String email) {
        Driver driver = new Driver();
        driver.setId(id);
        driver.setFullName("John Doe");
        driver.setPhoneNumber("87001234567");
        driver.setEmail(email);
        driver.setPasswordHash("hashedPassword123");
        driver.setDateOfBirth(LocalDate.of(1990, 1, 1));
        driver.setLicenseNumber("ABC123456");
        driver.setBalance(BigDecimal.ZERO);
        return driver;
    }

    @Test
    void isNotOwner_ShouldReturnFalse_WhenEmailMatches() {
        Driver driver = buildDriver(1, "driver@example.com");
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        boolean result = driverService.isNotOwner("driver@example.com", 1);
        assertFalse(result);
    }

    @Test
    void isNotOwner_ShouldReturnTrue_WhenEmailDoesNotMatch() {
        Driver driver = buildDriver(1, "other@example.com");
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        boolean result = driverService.isNotOwner("driver@example.com", 1);
        assertTrue(result);
    }

    @Test
    void isNotOwner_ShouldReturnTrue_WhenDriverNotFound() {
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        boolean result = driverService.isNotOwner("driver@example.com", 1);
        assertTrue(result);
    }
}
