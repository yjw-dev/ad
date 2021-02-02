package com.yshyerp.vehicle;

import com.yshyerp.vehicle.entity.VehiWa;
import com.yshyerp.vehicle.service.TrustService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VehicleApplicationTests {

    @Test
    public void contextLoads() {
        List<VehiWa> vehicleNo = new ArrayList<>();
        VehiWa vehiWa=new VehiWa();
        System.out.println(vehicleNo+"0000000000");

        BigDecimal a = new BigDecimal(4);

        vehiWa.setVehiW(a);
        vehicleNo.add(vehiWa);
        System.out.println(vehicleNo);

    }
}