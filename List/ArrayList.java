
public class ArrayList<E> {
	private static final int DEFAULT_SIZE = 10;
	
	private Object[] list;
	private int count;
	
	public ArrayList(int size) {
		list = new Object[size];
		this.count = 0;
	}
	
	public ArrayList() {
		this(DEFAULT_SIZE);
	}
	
	public void add(E elem) {
		//array bound check
		if(list.length < count+1) {
			grow();
		}
		//add
		list[count++] = elem;
	}
	
	void grow() {
		//new list with +1 bound
		Object[] growed = new Object[count+1];
		//copy
		for(int i=0; i<count; i++) {
			growed[i] = list[i];
		}
		list = growed;
	}
	
	public boolean isExist(E elem) {
		return getIdx(elem) > 0;
	}
	
	int getIdx(E elem) {
		for(int i=0; i<count; i++) {
			if(elem.equals(list[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean remove(E elem) {
		int rmvIdx = getIdx(elem);
		if(rmvIdx < 0) {
			return false;
		}
		
		//shift
		for(int i=rmvIdx; i<count-1; i++) {
			list[i] = list[i+1];
		}
		
		count--;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for(Object o:list) {
			sb.append(o.toString()+", ");
		}
		return sb.toString();
	}
	
	public int size() {
		return count;
	}
	
}
