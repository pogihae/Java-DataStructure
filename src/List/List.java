package List;

public interface List<E> {
	
	public void add(E e);
	
	public E get(int index);
	
	public boolean remove(E e);
	
	public boolean isExist(E e);
	
	public int size();
}
