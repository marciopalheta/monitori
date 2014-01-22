package br.fucapi.fapeam.monitori.model.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AbstractEntityBean implements Serializable{

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object outro) {
		if ((outro == null) || !(outro instanceof AbstractEntityBean)) {
			return false;
		}
		return this.id.longValue() == ((AbstractEntityBean) outro).id
				.longValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
