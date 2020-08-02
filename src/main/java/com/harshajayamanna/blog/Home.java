package com.harshajayamanna.blog;

import com.harshajayamanna.blog.entity.BlogPost;
import com.harshajayamanna.blog.entity.PaginationHelper;
import com.harshajayamanna.blog.service.BlogPostService;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class Home implements Serializable {

    @Inject
    private BlogPostService blogPostService;

    private PaginationHelper pagination;

    private int selectedItemIndex;

    private DataModel dtmdl = null;

    private BlogPost item;

    private String Next;

    private String Previous;

    public String getNext() {
        return "Next Page";
    }

    public void setNext(String next) {
        Next = next;
    }

    public String getPrevious() {
        return "Previous Page";
    }

    public void setPrevious(String previous) {
        Previous = previous;
    }

    @PostConstruct
    public void init(){

        if (this.pagination == null) {

            this.pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return blogPostService.count();
                }

                @Override
                public DataModel createPageDataModel() {

                    return new ListDataModel(blogPostService.findRange(new int[]{getPageFirstItem(), getPageSize()}));
                }
            };
        }
    }

    public PaginationHelper getPagination() {
        return pagination;
    }

    public DataModel getdtmdl() {
        if (this.dtmdl == null) {
            this.dtmdl = getPagination().createPageDataModel();
        }
        return dtmdl;
    }
    private void recreateModel() {
        dtmdl = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    private void updateCurrentItem() {
        int count = blogPostService.count();
        if (selectedItemIndex >= count) {

            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;

            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {

                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            item = blogPostService.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public void next() {
        getPagination().nextPage();
        recreateModel();
    }

    public void previous() {
        getPagination().previousPage();
        recreateModel();
    }


    public String getMessage() {
        return "Hello World!";
    }
}
