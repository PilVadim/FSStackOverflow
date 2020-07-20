package com.files.server.controller;

import com.files.server.model.ItemContent;
import com.files.server.services.ItemContentSvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(value="ItemContent controller class")
@RestController
@RequestMapping("/rest")
public class ItemContentController {

    @Autowired
    ItemContentSvc itemContentSvc;

    @ApiOperation(value = "get all ", response = ItemContent.class)
    @GetMapping("/itemContent/all/{page}")
    public Page<ItemContent> getItemContentByPage(@PathVariable("page") Integer page) {

        return itemContentSvc.getAll( page );

    }

    @ApiOperation(value = "save all ", response = ItemContent.class)
    @PostMapping("/itemContent")
    public ItemContent saveItemContent(@RequestBody ItemContent itemContent) {

        return itemContentSvc.save( itemContent );

    }

    @ApiOperation(value = "clean all ")
    @DeleteMapping("/clean")
    public void clean() {

        itemContentSvc.deleteAll();

    }

}
