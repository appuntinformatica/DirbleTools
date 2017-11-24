package com.andmin.dirble.repositories;

import com.andmin.dirble.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

@Transactional
@Repository
public class StationRepository {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public void save(Station entity) {
        hibernateTemplate.merge(entity);
        hibernateTemplate.flush();
    }

    public List<Station> findAll() {
        return (List<Station>) hibernateTemplate.find("SELECT s FROM Station AS s");
    }
    
    public List<Station> findAllImages() {
        return (List<Station>) hibernateTemplate.find("SELECT s FROM Station AS s WHERE s.image IS NOT NULL");
    }
}
