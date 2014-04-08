package ie.logn.data;

public interface IdAndType<T> {

	long getId();
	
	void setId(long id);  
	
	Class<? extends T> getType();
}
