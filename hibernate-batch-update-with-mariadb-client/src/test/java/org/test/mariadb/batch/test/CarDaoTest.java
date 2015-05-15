package org.test.mariadb.batch.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.LinkedList;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-applicationcontext.xml")
public class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Test
    public void testBatchInsert() throws Exception {
        carDao.deleteAll();
        Collection<Car> cars = new LinkedList<Car>();
        for (int i = 0; i < 20; i++) {
            cars.add(new Car(i, "Audi-A" + i));
        }
        carDao.save(cars);
    }
}
