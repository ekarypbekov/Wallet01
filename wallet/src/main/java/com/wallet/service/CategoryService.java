package com.wallet.service;

import com.wallet.dao.CategoryDao;
import com.wallet.model.Category;
import com.wallet.model.User;
import com.wallet.model.Wallet;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category createCategory(Category category){
        return categoryDao.createCategory(category);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

//    @GET
//    @Path("/{categoryId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Category getAllCategoryByParentCategoryId(@PathParam("categoryId") Long categoryId){
//        return categoryDao.getAllCategoryByParentCategoryId();
//    }

}
