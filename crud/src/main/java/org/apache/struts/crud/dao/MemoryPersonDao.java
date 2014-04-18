package org.apache.struts.crud.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.crud.model.Person;

/**
 *In memory data repository for Person objects.
 * @author antonio sanchez
 */
public class MemoryPersonDao implements PersonDao {

    private final static List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person(1, "Bruce", "Phillips", "basketball", "male", "US", true, new String[]{"Ford", "Nissan"}, "bphillips@ku.edu", "123-456-9999"));
        persons.add(new Person(2, "Antonio", "Sanchez", "mtb", "male", "ES", true, new String[]{"Toyota", "Seat"}, "asanchez@correoe.es", "555-999-8888"));
    }

    @Override
    public Person getPerson(Integer id) {
        for (Person p : persons) {
            if (p.getPersonId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Person[] getAllPersons() {
        return persons.toArray(new Person[persons.size()]);
    }

    @Override
    public void updatePerson(Person person) {
        Integer id = person.getPersonId();
        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            if (p.getPersonId().equals(id)) {
//                person.setDepartment(departmentsMap.get(person.getDepartment().getDepartmentId()));
                persons.set(i, person);
                break;
            }
        }
    }

    @Override
    public void insertPerson(Person person) {
        int lastId = 0;
        for (Person p : persons) {
            if (p.getPersonId() > lastId) {
                lastId = p.getPersonId();
            }
        }
//        person.setDepartment(departmentsMap.get(person.getDepartment().getDepartmentId()));
        person.setPersonId(lastId + 1);
        persons.add(person);
    }

    @Override
    public void deletePerson(Integer id) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getPersonId().equals(id)) {
                persons.remove(i);
                break;
            }
        }
    }
}
