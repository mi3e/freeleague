package ie.logn.data;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public abstract class BaseModel<T> implements IdAndType<T> {
	public BaseModel(final long id) {
		this.id = id;
	}

	public BaseModel() {
		// used if obj not yet persisted to db
	}

	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	@Override
	public Class<? extends T> getType() {
		return this.getType();
	}

	protected long id;
}