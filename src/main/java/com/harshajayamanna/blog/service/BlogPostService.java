package com.harshajayamanna.blog.service;

import com.harshajayamanna.blog.entity.BlogPost;
import com.harshajayamanna.blog.entity.Meta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class BlogPostService implements Serializable {

    @PersistenceContext(name = "blogpostunit")
    EntityManager em;

    public void addBlogPost(String title, String content){
        BlogPost post = new BlogPost(title,content);
        em.persist(post);
    }

    public int count() {
        Query companyQuery = em.createQuery("SELECT count(b) FROM BlogPost b");
        return (int) (long)  companyQuery.getSingleResult();
    }


    public List<BlogPost> findRange(int[] ints) {

        Query companyQuery = em.createNativeQuery("SELECT * FROM blog_post LIMIT "+ints[0]+","+ints[1]);
        List<BlogPost> list = companyQuery.getResultList();
        return list;
    }


    public BlogPost getBlogPost(Long id){
        return em.find(BlogPost.class, id);
    }

    public void addMeta(BlogPost blogPost, String metaContent){
        Meta newMeta = new Meta();
        newMeta.setContent(metaContent);
        newMeta.setPost(blogPost);
        em.persist(newMeta);
    }
}
