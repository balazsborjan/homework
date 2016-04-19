package group.jpa.homework;

import group.jpa.homework.enity.CarFactory;
import group.jpa.homework.enity.People;
import group.jpa.homework.enity.Car;
import group.jpa.homework.enity.Customer;
import group.jpa.homework.enity.LicensePlate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {
        
        Calendar calendar = Calendar.getInstance();
        Date purchasedDate;
        
        CarFactory f1 = new CarFactory("AudiFactory", new FactoryAddress(30, "AudiStreet", "AudiCity", "AudiCountry"));
        CarFactory f2 = new CarFactory("FerrariFactory", new FactoryAddress(12, "FerrariStreet", "FerrariCity", "FerrariCountry"));
        CarFactory f3 = new CarFactory("VolkswagenFactory", new FactoryAddress(12, "VolkswagenStreet", "VolkswagenCity", "VolkswagenCountry"));
        
        LicensePlate lp1 = new LicensePlate("JAZ-094");
        LicensePlate lp2 = new LicensePlate("KPD-541");
        LicensePlate lp3 = new LicensePlate("NKJ-004");
        LicensePlate lp4 = new LicensePlate("NIK-012");
        LicensePlate lp5 = new LicensePlate("BME-769");
        LicensePlate lp6 = new LicensePlate("KFB-982");
        LicensePlate lp7 = new LicensePlate("FLK-618");
        LicensePlate lp8 = new LicensePlate("GRN-420");
        
        calendar.set(2015, 10, 10);
        purchasedDate = calendar.getTime();
        Car c1 = new Car(CarType.AUDI, 5, 10000, purchasedDate, lp1);
        c1.getAvailableColors().add("Yellow");
        c1.getAvailableColors().add("Red");
        
        calendar.set(2015, 10, 10);
        purchasedDate = calendar.getTime();
        Car c2 = new Car(CarType.FERRARI, 2, 15000, purchasedDate, lp2);
        c2.getAvailableColors().add("Blue");
        c2.getAvailableColors().add("Green");
        c2.getAvailableColors().add("White");
        
        calendar.set(2016, 5, 10);
        purchasedDate = calendar.getTime();
        Car c3 = new Car(CarType.AUDI, 5, 8000, purchasedDate, lp3);
        c3.getAvailableColors().add("Gray");
        c3.getAvailableColors().add("Black");
        
        calendar.set(2014, 12, 1);
        purchasedDate = calendar.getTime();
        Car c4 = new Car(CarType.VOLKSWAGEN, 2, 20000, purchasedDate, lp4);
        c4.getAvailableColors().add("Red");
        
        calendar.set(2015, 1, 10);
        purchasedDate = calendar.getTime();
        Car c5 = new Car(CarType.FERRARI, 2, 25000, purchasedDate, lp5);
        c5.getAvailableColors().add("Red");
        
        calendar.set(2015, 11, 10);
        purchasedDate = calendar.getTime();
        Car c6 = new Car(CarType.AUDI, 5, 10000, purchasedDate, lp6);
        c6.getAvailableColors().add("Gray");
        c6.getAvailableColors().add("Black");
        c6.getAvailableColors().add("Purple");
        
        calendar.set(2013, 10, 8);
        purchasedDate = calendar.getTime();
        Car c7 = new Car(CarType.AUDI, 5, 7000, purchasedDate, lp7);
        c7.getAvailableColors().add("Black");
        c7.getAvailableColors().add("White");
        
        calendar.set(2012, 12, 12);
        purchasedDate = calendar.getTime();
        Car c8 = new Car(CarType.VOLKSWAGEN, 9, 6000, purchasedDate, lp8);
        c8.getAvailableColors().add("White");
        c8.getAvailableColors().add("Blue");
        
        People p1 = new People("John", 14);
        People p2 = new People("Kevin", 17);
        People p3 = new People("Kate", 18);
        
        Customer customer1 = new Customer("Jack", 23);
        Customer customer2 = new Customer("Flint", 32);
        Customer customer3 = new Customer("George", 41);
        Customer customer4 = new Customer("Marthin", 27);
        Customer customer5 = new Customer("Peter", 29);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-homework-carService");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
        em.persist(customer4);
        em.persist(customer5);
        
        em.persist(f1);
        em.persist(f2);
        em.persist(f3);
        
        em.persist(lp1);
        em.persist(lp2);
        em.persist(lp3);
        em.persist(lp4);
        em.persist(lp5);
        em.persist(lp6);
        em.persist(lp7);
        em.persist(lp8);
        
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        em.persist(c5);
        em.persist(c6);
        em.persist(c7);
        em.persist(c8);
        
        f1.getCars().add(c1);
        f1.getCars().add(c2);
        
        f2.getCars().add(c3);
        f2.getCars().add(c4);
        
        f3.getCars().add(c5);
        f3.getCars().add(c6);
        f3.getCars().add(c7);
        f3.getCars().add(c8);
        
        customer1.getCars().add(c1);
        customer1.getCars().add(c2);
        
        customer2.getCars().add(c3);
        
        customer3.getCars().add(c4);
        customer3.getCars().add(c5);
        
        customer4.getCars().add(c6);
        customer4.getCars().add(c7);
        
        customer5.getCars().add(c8);
        
        tx.commit();
        
        tx.begin();
        
        Query query = em.createNamedQuery("findCarWithPlateParam");
        query.setParameter("plate", "JAZ-094");
        Car searchCar = (Car)query.getSingleResult();
        System.out.println("Search for car with plate: JAZ-094\nResult: ");
        System.out.println(searchCar.toString());
        System.out.println("-----------------------------------");
        
        calendar.set(2014, 1, 1);
        purchasedDate = calendar.getTime();
        query = em.createNamedQuery("listCarsPurchasedLessThenParameterAgoById");
        query.setParameter("date", purchasedDate);
        List<Long> queryCars = query.getResultList();
        System.out.println("Search for cars purchased after 2014.01.01\nResult: ");
        
        queryCars.stream().forEach((queryCar) -> {
            System.out.println(queryCar.toString());
        });
        System.out.println("-----------------------------------");
        
        query = em.createNamedQuery("countCarsByType");
        query.setParameter("type", CarType.AUDI);
        Long typedCars = (Long)query.getSingleResult();
        System.out.println("Count of AUDI typed cars: ");
        System.out.println(typedCars);
        System.out.println("-----------------------------------");
        
        System.out.println("Car type more than 3 times: ");
        query = em.createNamedQuery("groupByCarType");
        List<CarType> cars = query.getResultList();
        cars.stream().forEach(car -> {
            System.out.println(car.toString());
        });
        System.out.println("-----------------------------------");
        
        System.out.println("Sum Price: ");
        query = em.createNamedQuery("getSumPrice", Object[].class);
        List<Object[]> listSumByPrice = query.getResultList();
        listSumByPrice.stream().forEach((objects) -> {
            System.out.println(objects[0].toString() + " - " + objects[1].toString());
        });
        System.out.println("-----------------------------------");
        
        tx.commit();
        
        em.close();
        emf.close();
        
        System.out.println("Status OK");
    }
}
