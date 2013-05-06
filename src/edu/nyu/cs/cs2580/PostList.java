package edu.nyu.cs.cs2580;

import java.util.ArrayList;


public class PostList {
	String _fileName;
	ArrayList<Integer> _posts = new ArrayList<Integer>();
	public  PostList(String fileName) {
		_fileName = fileName;
	}
	public void addPost(int i) {
		_posts.add(i);
	}
	public String toString() {
		String ret = "\t"+_fileName;
		ret+="\t"+ Integer.toString(_posts.size());
		for (int i = 0; i<_posts.size();i++) {
			ret+= "\t"+ Integer.toString(_posts.get(i));
		}
		return ret;
	}
}
