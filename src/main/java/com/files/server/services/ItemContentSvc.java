package com.files.server.services;


import com.files.server.model.ItemContent;
import com.files.server.repository.ItemContentRepository;
import javassist.tools.web.BadHttpRequest;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.files.server.configuration.Paging.PAGE_SIZE;

@Service
public class ItemContentSvc {

    private final ItemContentRepository itemContentRepository;

    @Autowired
    public ItemContentSvc(ItemContentRepository itemContentRepository, ConversionService conversionService) {
        this.itemContentRepository = itemContentRepository;
    }

    public Page<ItemContent> getAll(Integer page){


        return itemContentRepository.findAll( PageRequest.of( page, PAGE_SIZE, Sort.by("id") ) );

    }

    public ItemContent save(ItemContent itemContent){

        Page<ItemContent> recordsFromBase =  getAll(0);

        if (recordsFromBase.getContent().size()>0) {
            throw new RuntimeException( "Base already contain record" );
        } else {
            return itemContentRepository.save(itemContent);
        }

    }

    public void deleteAll(){
        itemContentRepository.deleteAll();
    }

}
