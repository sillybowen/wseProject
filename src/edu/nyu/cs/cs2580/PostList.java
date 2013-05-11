package edu.nyu.cs.cs2580;

import java.util.ArrayList;


public class PostList {
	String _fileName;
	int _id;
	ArrayList<Integer> _posts = new ArrayList<Integer>();
	public  PostList(String fileName, int id) {
		_fileName = fileName;
		_id = id;
	}
	public int getPos(int i) {
		return _posts.get(i);
	}
	public int getSize() {
		return _posts.size();
	}
	public int getId() {
		return _id;
	}
	public PostList() {
	}
	public void addPost(int i) {
		_posts.add(i);
	}
	public String toString() {
		String ret = Integer.toString(_id);		
		ret+=" "+ Integer.toString(_posts.size());
		for (int i = 0; i<_posts.size();i++) {
			ret+= " "+ Integer.toString(_posts.get(i));
		}
		return ret;
	}
	public void load(String s) {
		String[] tmp = s.split("[ ]");
		_id = Integer.parseInt(tmp[0]);
		_posts.clear();
		int sz = Integer.parseInt(tmp[1]);
		for (int i = 0; i<sz;i++) {
			_posts.add(Integer.parseInt(tmp[2+i]));
		}
	}
}
