package com.revature.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="voteSequence")
	@SequenceGenerator(name="voteSequence", allocationSize=1, sequenceName="SQ_VOTE_PK")
	@Column
	@JsonProperty
	private int id;
	
	@ManyToOne
	@JoinColumn
	@JsonProperty
	private User user;
	
	
	@ManyToOne
	@JoinColumn
	@JsonProperty
	private Post post;
	
	public Vote() {
		super();
	}

	public Vote(User user, Post post) {
		super();
		this.user = user;
		this.post = post;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (id != other.id)
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", user=" + user + ", post=" + post + "]";
	}
}
