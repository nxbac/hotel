package com.shine.hotels.io.model;

import java.io.Serializable;
import java.util.List;

public class AppreciatemusicList implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2066782463893105131L;
	
	private List<Appreciatemusic> mList;
    
    public void setList(List<Appreciatemusic> list) {
        mList = list;
    }
    
    public List<Appreciatemusic> getLists() {
        return mList;
    }
}
