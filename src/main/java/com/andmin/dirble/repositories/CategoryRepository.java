package com.andmin.dirble.repositories;

import com.andmin.dirble.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.*;

@Transactional
@Repository
public class CategoryRepository {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public void save(Category entity) {
        hibernateTemplate.merge(entity);
        hibernateTemplate.flush();
    }

    public List<Category> findAll() {
        return (List<Category>) hibernateTemplate.find("SELECT c FROM Category AS c");
    }
}
