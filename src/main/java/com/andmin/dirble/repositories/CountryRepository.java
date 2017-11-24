package com.andmin.dirble.repositories;

import com.andmin.dirble.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

@Transactional
@Repository
public class CountryRepository {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public void save(Country entity) {
        hibernateTemplate.merge(entity);
        hibernateTemplate.flush();
    }
    
    public List<Country> findAll() {
        return (List<Country>) hibernateTemplate.find("SELECT c FROM Country AS c ORDER BY c.name");
    }
}
