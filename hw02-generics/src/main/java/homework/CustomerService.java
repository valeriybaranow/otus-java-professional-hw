package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Map<Customer, String> map;


    public CustomerService() {
        map = new TreeMap<>() {};
    }

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
//        List<Customer> keyList = new ArrayList<>(map.keySet());
//        Collections.sort(keyList);
//        map.get(keyList.get(0));
//        // тут дальше на смог реализовать
//        return map.get(keyList.get(0));
        return map.entrySet().iterator().next();
//        long scopes = firstItem.getKey().getScores();
//        for(Map.Entry<Customer, String> item : map.entrySet()) {
//            if(item.getKey().getScores() < scopes) {
//                scopes = item.getKey().getScores();
//                firstItem = item;
//            }
//        }
//        System.out.println(firstItem.toString());
//        return firstItem;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        // перебор элементов
        for(Map.Entry<Customer, String> item : map.entrySet()) {
            if(item.getKey().getScores() > customer.getScores()) {
                return  item;
            }
        }
        return null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
