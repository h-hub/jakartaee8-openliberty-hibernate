package com.harshajayamanna.blog;

import com.harshajayamanna.blog.service.BlogPostService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
public class BlogPostController {

    @Inject
    private BlogPostService blogPostService;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String publish(){
        blogPostService.addBlogPost(this.title, this.content);
        return "index.xhtml?faces-redirect=true";

    }

}
