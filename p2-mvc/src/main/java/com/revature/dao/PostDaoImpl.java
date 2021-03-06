package com.revature.dao;

import java.util.List;

import org.hibernate.*;

import com.revature.models.Post;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class PostDaoImpl implements PostDao {

	@Override
	public List<Post> getPosts() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post");
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByUserId(String id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post p where p.user.id = :userId");
		q.setString("userId", id);
		List<Post> posts = q.list();
		s.close();
		return posts;
		
	}

	@Override
	public int createPost(Post post) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int postId = (int) s.save(post);
		tx.commit();
		s.close();
		return postId;
	}

	@Override
	public List<Post> getPostsByPage(int page) {
		int maxPage = page * 10;
		int minPage = maxPage - 10;
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post");
		q.setFirstResult(minPage);
		q.setMaxResults(maxPage);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByGroupId(int id) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post p where p.group.id = :groupId");
		q.setInteger("groupId", id);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByGroupName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Post p where p.group.name = :groupName");
		q.setString("groupName", name);
		List<Post> posts = q.list();
		s.close();
		return posts;
	}

	@Override
	public Post getPostById(int id) {
		Session s = HibernateUtil.getSession();
		Post post = (Post) s.get(Post.class, id);
		s.close();
		return post;
	}
	
	@Override 
	public Post updatePost(Post post) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(post);
		tx.commit();
		s.close();
		return post;
	}

	@Override
	public void deletePost(Post post) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		System.out.println("Deleting");
		s.delete(post);
		tx.commit();
		s.close();
	}
	

}
